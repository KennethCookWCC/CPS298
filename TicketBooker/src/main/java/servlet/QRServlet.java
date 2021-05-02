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
import beans.CustTicketBean;
import beans.MovieBean;
import beans.MovieListBean;
import beans.ShowingBean;
import beans.ShowingListBean;
import beans.SoldTicketBean;
import beans.TicketBean;
import beans.UserBean;
import jdbc.ConnectionPool;
import sql.UserDAO;

/**
 * Servlet implementation class qrURL generator in javascript
 *
 * setup session
 */
@WebServlet("/qrURL")
public class QRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QRServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		
		final String Prog = "QRServlet:";
		
		String qrValue = "NoEntry";
		String mesg = "Ticket Not Found";
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			// then we shouldn't be here.
			System.out.println(Prog + "no session!");

			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
			dispatcher.forward(request, response);

			return;
		}

		String tkIdstr = request.getParameter("TktId");
		
		UserBean userBean = (UserBean) session.getAttribute("user");
		CartBean userCart = (CartBean) session.getAttribute("cart");
		if (userBean == null || userCart == null || tkIdstr == null ) {
			// then we shouldn't be here.
			System.out.println(Prog + "have session, but no user or cart or ticket!");

			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
			dispatcher.forward(request, response);

			return;
		}

		if (!userBean.isLoginOK()) {
			// need to login first
			System.out.println(Prog + "not logged in");
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/LoginCustomerJSP");
			dispatcher.forward(request, response);

			return;
		}

		if (userBean.isAdmin()) {
			// can't be here
			System.out.println(Prog + "admin user");
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/AdminServlet");
			dispatcher.forward(request, response);

			return;
		}

		int tkId = Integer.parseInt(tkIdstr);
		
		CustTicketBean tk = new CustTicketBean();
		
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			
			
			if( !tk.loadOne(conn, tkId) ) {
				// can't find ticket
				mesg = "Internal Errror: TK=" + tkId ;
			} else {
			
				// got it, build QR string
				
				// check if it belongs
				if( tk.getCustomerId() != userBean.getId() ) {
					// not their ticket
					mesg = "Sorry, not your ticket (" + tkId + ")";
				} else {
				
					mesg = "";
					
					qrValue = "TKT=" + tk.getTicketId();
					qrValue += "&CUST=" + tk.getCustomerId();
					qrValue += "&TTL=" + tk.getTitle();
					qrValue += "&DT=" + tk.getDate();
					qrValue += "&TM=" + tk.getTime();
					qrValue += "&SEAT=" + tk.getSeat();
				}
				
			} 
		
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if(conn != null) {
				connectionPool.free(conn);
			}
		}
	
		request.setAttribute("ticket", tk);
		request.setAttribute("QRvalue", qrValue);
		request.setAttribute("message", mesg);
		servletContext.getRequestDispatcher("/MyTicketQR.jsp").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// we didn't hit the path that returns the page result.
		//request.setAttribute("message", error);
		//request.setAttribute("notLoggedIn", true);
		//servletContext.getRequestDispatcher("/AdminMain.jsp").forward(request, response);
		// servletContext.getRequestDispatcher("/LoginCustomerJSP").forward(request, response);
		
	}

}
