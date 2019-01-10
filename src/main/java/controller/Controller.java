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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		RequestDispatcher rs = null;

		switch (req.getRequestURI()) {
			case "/Projet/register" : {
				User me = createNewUser(request);
				req.getSession().setAttribute("user", me);
				HttpServletResponse resp =(HttpServletResponse) response;
				resp.sendRedirect("Menu.jsp");
				break;
			}
			case "/Projet/Menu.jsp" : {
				if(isLoggedIn(req)){
					rs = request.getRequestDispatcher("/Menu.jsp");
				}else {
					rs = request.getRequestDispatcher("/index.jsp");
				}
				break;
			}
			default: chain.doFilter(request, response);
		}

		if(rs != null ) rs.forward(request, response);
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
