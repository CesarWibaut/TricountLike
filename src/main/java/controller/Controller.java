package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

import model.Event;
import model.Participate;
import model.User;

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
		HttpServletResponse resp =(HttpServletResponse) response;
		RequestDispatcher rs = null;

		switch (req.getRequestURI()) {
			case "/Projet/register" : {
				User me = createNewUser(request);
				req.getSession().setAttribute("user", me);
				resp.sendRedirect("Menu.jsp");
				break;
			}

			case "/Projet/Menu.jsp" : {
				if(!isLoggedIn(req)){
					resp.sendRedirect("index.jsp");
				}else {
					rs = request.getRequestDispatcher("Menu.jsp");
				}
				break;
			}

			case "/Projet/login" : {
				User user = login(req);
				if(user != null) {
					req.getSession().setAttribute("user", user);
					resp.sendRedirect("Menu.jsp");
				}else {
					resp.sendRedirect("index.jsp?error=1");
				}
				break;
			}

			case "/Projet/disconnect" : {
				req.getSession().invalidate();
				rs = req.getRequestDispatcher("index.jsp");
				break;
			}

			case "/Projet/createEvent" : {
				Integer num = createEvent(req);
				resp.sendRedirect("oui.html?eno=" + num);
				break;
			}

			default: {
				chain.doFilter(request, response);
			}
		}

		if(rs != null ) rs.forward(request, response);
	}

	private Integer createEvent(HttpServletRequest req) {
		Event event = new Event();
		User user = (User)req.getSession().getAttribute("user");
		Integer uno = user.getUno();
		Participate p = new Participate();
		p.setUno(uno);
		event.setDescr(req.getParameter("desc"));
		event.setTitle(req.getParameter("name"));
		em.getTransaction().begin();
		em.persist(event);
		em.flush();
		p.setEno(event.getEno());
		em.persist(p);
		em.getTransaction().commit();
		return event.getEno();
	}

	private User login(HttpServletRequest req) {
		try {
			return (User) em.createNamedQuery("User.login")
				.setParameter("email", req.getParameter("email"))
				.setParameter("password", req.getParameter("password"))
				.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	private boolean isLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null;
	}

	private User createNewUser(ServletRequest request) {
		User u = new User();
		u.setLogin(request.getParameter("email"));
		u.setPwd(request.getParameter("password"));
		u.setFirstname(request.getParameter("firstname"));
		u.setLastname(request.getParameter("lastname"));
		
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
	}

}
