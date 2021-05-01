package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import beans.SeatBean;
import beans.ShowingBean;
import beans.ShowingListBean;
import beans.TicketBean;
import beans.UserBean;
import jdbc.ConnectionPool;

/**
 * Servlet implementation class MovieServlet
 * 
 * update cartBean after seat selection or changes
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String Prog = "ShowCartServlet:";
		
		ServletContext servletContext = getServletContext();
		ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");
		
		// these do not need to be inside try / catch
		HttpSession session = request.getSession(false);
		
		if( session == null ) {
			// then we shouldn't be here.
			System.out.println(Prog+"no session!");
			
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
			dispatcher.forward(request, response);		
			
			return;
		}
		
		
		UserBean userBean = (UserBean) session.getAttribute("user");	
		CartBean userCart = (CartBean) session.getAttribute("cart");
		if( userBean == null || userCart == null ) {
			// then we shouldn't be here.
			System.out.println(Prog+"have session, but no user or cart!");
						
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
			dispatcher.forward(request, response);		
					
			return;
		}
		
		if( !userBean.isLoginOK() )	{
			// need to login first
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/LoginCustomerJSP");
			dispatcher.forward(request, response);		
		}
		
		if( userBean.isAdmin() ) {
			// can't be here
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/AdminServlet");
			dispatcher.forward(request, response);
		}
		
		
		
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/Checkout.jsp");
		dispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
