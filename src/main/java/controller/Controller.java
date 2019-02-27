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
			case "/Projet/register" : {
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

			default: {
				if(req.getRequestURI().contains("event")) {
					initEvent(req);
				}
				chain.doFilter(request, response);
			}
		}
		if(rs != null) rs.forward(request, response);
	}

	private void initEvent(HttpServletRequest req) {
		Event e = em.find(Event.class, Integer.valueOf(req.getParameter("eno")));
		req.setAttribute("event", e);
	}

	private void updateSession(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		em.refresh(user);
		System.out.println(user);
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

		System.out.println(em);
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
