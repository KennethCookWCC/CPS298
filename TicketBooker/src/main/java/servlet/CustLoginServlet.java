package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CartBean;
import beans.MovieBean;
import beans.MovieListBean;
import beans.ShowingBean;
import beans.ShowingListBean;
import beans.UserBean;
import jdbc.ConnectionPool;
import sql.UserDAO;

/**
 * Servlet implementation class LoginCustServlet
 *
 * setup session
 */
//@WebServlet("/CustLoginServlet")
public class CustLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void invalidateUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
			session.removeAttribute("cart");
		}
	}

		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext servletContext = getServletContext();
		ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");

		// assume login fails
		String destPage = "/loginCustomer.jsp";
		String message = "Please enter a valid login/password";

		String loginStr = request.getParameter("login");
		String password = request.getParameter("password");

		loginStr = loginStr.trim();
		password = password.trim();

		// validate fields
		if (loginStr.isEmpty() || password.isEmpty()) {
			// one is missing
			invalidateUser(request);

			request.setAttribute("message", message);

			RequestDispatcher dispatcher = servletContext.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);

			return;
		}

		// validate customer login
		UserDAO userDao = new UserDAO();
		UserBean userBean = new UserBean();

		/*
		 * MovieListBean movieListBean = new MovieListBean(); ShowingBean showingBean =
		 * new ShowingBean(); ShowingListBean sblist = new ShowingListBean();
		 * java.sql.Date date = new java.sql.Date(121,0,16); String dateParam; dateParam
		 * = request.getParameter("date");
		 * 
		 * if(dateParam != null) { // set sql date = to dateParam //
		 * System.out.println("dateParam: "+dateParam); String testDate ="2021-01-15";
		 * String[] dateExplode = dateParam.split("-"); //
		 * System.out.println("date explode: "+Arrays.asList(dateExplode)); Integer year
		 * = Integer.parseInt(dateExplode[0])-1900; Integer month =
		 * Integer.parseInt(dateExplode[1])-1; Integer day =
		 * Integer.parseInt(dateExplode[2]);
		 * System.out.println("year: "+Integer.toString(year)+"\n");
		 * System.out.println("month: "+Integer.toString(month)+"\n");
		 * System.out.println("day: "+Integer.toString(day)+"\n");
		 * 
		 * date = new java.sql.Date(year, month, day); }
		 */

		Connection conn = null;
		try {
			conn = connectionPool.getConnection();

			// validate customer login
			userBean = userDao.checkCustomerLogin(conn, loginStr, password);

			if (userBean == null) {
				// failed login
				System.out.println("CustLoginServlet:LOGIN FAILED");
				invalidateUser(request);
				request.setAttribute("message", message);
				
				RequestDispatcher dispatcher = servletContext.getRequestDispatcher(destPage);
				dispatcher.forward(request, response);
				
				return;
			}

			// good login
			request.setAttribute("message", "");

			// begin session
			HttpSession session = request.getSession(false);
			if (session == null) {
				System.out.println("CustLoginServlet:Create New session");
				CartBean cartBean = new CartBean();
				cartBean.setValidated(false);

				// Not created yet. Now do so yourself.
				session = request.getSession();
				session.setAttribute("cart", cartBean);
				session.setAttribute("user", userBean);
			} else {
				// session exists
				System.out.println("CustLoginServlet:Have existing session");
				// check for cart
				CartBean cart = (CartBean) session.getAttribute("cart");

				if (cart == null) {
					System.out.println("CustLoginServlet:Existing Session, no cart?");
					
					// nope, make a new one
					cart = new CartBean();
					cart.setValidated(false);
					session.setAttribute("cart", cart);
				} else {
					System.out.println("CustLoginServlet:Existing Session, existing cart");
					
				}

				session.setAttribute("user", userBean);

				// session.setAttribute("cart", cart);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if (conn != null) {
				connectionPool.free(conn);
			}
		}
		
		// successful login if we get here
		System.out.println("CustLoginServlet:Successful Login:" + userBean.getLogin() 
			+ " uid:" + userBean.getId() 
			+ " name:" + userBean.getName()
			+ " Admin:" + userBean.isAdmin()
			);

		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
		dispatcher.forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * do not honor a GET to login - only a POST method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("LoginCustServlet:ERROR:got at GET method!");
		ServletContext servletContext = getServletContext();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/loginCustomer.jsp");
		dispatcher.forward(request, response);
	}

}
