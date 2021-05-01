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
import beans.SoldTicketBean;
import beans.UserBean;
import jdbc.ConnectionPool;
import sql.UserDAO;

/**
 * Servlet implementation class LoginAdminServlet
 *
 * setup session
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		doGet(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");
		
		String error = "Not logged in";
		HttpSession session = request.getSession(false);
		
		// if we have no session or it lacks the user property we know we aren't logged in
		if(session != null && session.getAttribute("user") != null) {
			UserBean user = (UserBean) session.getAttribute("user");
			if(user.isAdmin()) {
				Connection conn = null;
				try {
					conn = connectionPool.getConnection();
					
					// put both soldtickets list and number of soldtickets into session
					int nSoldTickets = 0;
					int totalSold = 0;
					List<SoldTicketBean> tickets = SoldTicketBean.getSoldTickets(conn);
					if( tickets != null ) {
						nSoldTickets = tickets.size();
						for( SoldTicketBean stb : tickets ) {
							totalSold += stb.getPrice();
						}
					}
					SoldTicketBean totTk = new SoldTicketBean();
					totTk.setPrice(totalSold);
					totTk.setCustomer("Total Sold:");
					totTk.setTicketId(nSoldTickets);
					totTk.setTime("");
					Date dt = new Date(2021-1900, 0, 15);
					totTk.setDate(dt);
					tickets.add(totTk);
					
					request.setAttribute("nSoldTickts", nSoldTickets);
					request.setAttribute("soldTickets", tickets);
					
					// put both showings list and number of showings into session
					int nShowings = 0;
					List<ShowingBean> showings = ShowingBean.loadAllFromDatabase(conn);
					if( showings != null ) {
						nShowings = showings.size();
					}
					request.setAttribute("nShowings", nShowings);
					request.setAttribute("showings", showings);
					
					// user.loginOK does this already
					//request.setAttribute("notLoggedIn", false);
					
					servletContext.getRequestDispatcher("/AdminMain.jsp").forward(request, response);
					return;
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException(e);
				} finally {
					if(conn != null) {
						connectionPool.free(conn);
					}
				}
			} else {
				error = "Not logged in as an admin";
			}
		}
		
		// we didn't hit the path that returns the page result.
		//request.setAttribute("message", error);
		//request.setAttribute("notLoggedIn", true);
		//servletContext.getRequestDispatcher("/AdminMain.jsp").forward(request, response);
		servletContext.getRequestDispatcher("/LoginCustomerJSP").forward(request, response);
		
	}

}
