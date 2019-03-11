package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet Filter implementation class Controller
 */
@WebFilter(urlPatterns = "/*")
public class Controller implements Filter {

	EntityManager em;
    /**
     * Default constructor. 
     */
    public Controller() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
						throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getUserPrincipal() != null && req.getSession().getAttribute("user") == null){
			User u = login(req.getUserPrincipal().getName());
			req.getSession().setAttribute("user", u);
		}

		HttpServletResponse resp =(HttpServletResponse) response;
		RequestDispatcher rs = null;

		switch (req.getRequestURI()) {
			case "/Projet/" : {
				createNewUser(request);

				resp.sendRedirect("Menu.jsp");
				break;
			}

			case "/Projet/Menu.jsp" : {
				updateSession(req);
				rs = request.getRequestDispatcher("Menu.jsp");
				break;
			}

			case "/Projet/createEvent" : {
				int eventId = createEvent(req);
				resp.sendRedirect("event.jsp?eno=" + eventId);
				break;
			}

			case "/Projet/addSpent" : {
				System.out.println(req.getParameter("for"));
				System.out.println(req.getParameter("ammount"));
				System.out.println(req.getParameter("eno"));
				manageSpent(req);
				resp.sendRedirect("event.jsp?eno=" + req.getParameter("eno"));
				break;
			}

			case "/Projet/disconnect" : {
				req.getSession().invalidate();
				resp.sendRedirect("Menu.jsp");
				break;
			}

			case "/Projet/register" : {
				createNewUser(req);
				resp.sendRedirect("Menu.jsp");
				break;
			}

			case "/Projet/addUser" : {
				addUserToEvent(request.getParameter("eno"), req.getParameter("email"));
				resp.sendRedirect("event.jsp?eno=" + request.getParameter("eno"));
				break;
			}

			default: {
				if(req.getRequestURI().contains("event")) {
					initEvent(req);
				}
				chain.doFilter(request, response);
			}
		}
		if(rs != null) rs.forward(request, response);
	}

	private void addUserToEvent(String eno, String email) {
		try {
			Event event = em.find(Event.class, Integer.parseInt(eno));
			User user = (User) em.createNamedQuery("User.findByMail").setParameter("email", email).getSingleResult();
			Participate p = new Participate();
			p.setEno(event.getEno());
			p.setUno(user.getUno());
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		} catch (Exception e) {
		}
	}

	private void manageSpent(HttpServletRequest req) {
		String[] unoFor = req.getParameterValues("for");
		User user = (User) req.getSession().getAttribute("user");
		Integer eno = Integer.parseInt(req.getParameter("eno"));
		Integer uno = user.getUno();
		float ammount = Float.parseFloat(req.getParameter("ammount"));
		Spent spent = new Spent();
		spent.setAmmount((int)ammount);
		spent.setUser(user);
		Event event = em.find(Event.class, eno);
		spent.setEvent(event);
		em.getTransaction().begin();
		em.persist(spent);
		em.getTransaction().commit();
		ammount /= (float)(unoFor.length +1);

		for(String s : unoFor) {
			try {
			Owes owe = (Owes) em.createNamedQuery("Owes.findIfExist")
								.setParameter("eno", eno)
								.setParameter("uno", uno)
								.setParameter("unoFor", s)
								.getSingleResult();

			owe.setAmmount(owe.getAmmount() + ammount);
			} catch (Exception e) {
				Owes owe = new Owes();
				owe.setEno(eno);
				owe.setAmmount(ammount);
				owe.setUno(uno);
				owe.setUnoFor(Integer.parseInt(s));
				
				em.getTransaction().begin();
				System.out.println(owe);
				em.persist(owe);

				em.getTransaction().commit();
			}	
		}


	}

	private void initEvent(HttpServletRequest req) {
		Event e = em.find(Event.class, Integer.valueOf(req.getParameter("eno")));
		em.refresh(e);
		req.setAttribute("event", e);
	}

	private void updateSession(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		em.refresh(user);
	}

	private Integer createEvent(HttpServletRequest req) {
		Event event = new Event();
		User user = (User)req.getSession().getAttribute("user");
		Participate p = new Participate();
		p.setUno(user.getUno());
		event.setDescr(req.getParameter("desc"));
		event.setName(req.getParameter("name"));
		em.getTransaction().begin();
		em.persist(event);
		em.flush();
		p.setEno(event.getEno());
		em.persist(p);
		em.getTransaction().commit();
		em.refresh(event);
		return event.getEno();
	}

	private User login(String email) {
		try {
			return (User) em.createNamedQuery("User.findByMail")
				.setParameter("email", email)
				.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private User createNewUser(ServletRequest request) {
		User u = new User();
		u.setEmail(request.getParameter("email"));
		u.setPassword(request.getParameter("password"));
		u.setFirstname(request.getParameter("firstname"));
		u.setLastname(request.getParameter("lastname"));
		u.setRole("user");

		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();

		User ret = (User) em.createNamedQuery("User.findByMail").setParameter("email", request.getParameter("email")).getSingleResult();
		return ret;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet");
		em = emf.createEntityManager();
		em.setFlushMode(FlushModeType.AUTO);
	}

}
