package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
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
import beans.PurchaseBean;
import beans.SeatBean;
import beans.ShowingBean;
import beans.ShowingListBean;
import beans.TicketBean;
import beans.UserBean;
import jdbc.ConnectionPool;

/**
 * Servlet implementation class checkout cart
 * 
 * 
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String Prog = "CheckoutServlet:";

		ServletContext servletContext = getServletContext();
		ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");

		// these do not need to be inside try / catch
		HttpSession session = request.getSession(false);

		if (session == null) {
			// then we shouldn't be here.
			System.out.println(Prog + "no session!");

			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
			dispatcher.forward(request, response);

			return;
		}

		UserBean userBean = (UserBean) session.getAttribute("user");
		CartBean userCart = (CartBean) session.getAttribute("cart");
		if (userBean == null || userCart == null) {
			// then we shouldn't be here.
			System.out.println(Prog + "have session, but no user or cart!");

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

		if (userCart.count() == 0) {
			// customer ID is not in the tickets
			System.out.println(Prog + "empty cart");
			// circle back on our self
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/Checkout.jsp");
			dispatcher.forward(request, response);

			return;
		}

		if (!userCart.isValidated()) {
			// customer ID is not in the tickets
			System.out.println(Prog + "cart not validated");
			// circle back on our self
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/Checkout.jsp");
			dispatcher.forward(request, response);

			return;
		}

		// ok try to purchase the tickets in the cart
		// Check session stuff
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();

			// write the purchase
			PurchaseBean purch = new PurchaseBean();
			purch.setTotal(0);
			purch.setCustomerId(userBean.getId());
			Date dt = new Date(2021 - 1900, 0, 15);
			purch.setDate(dt);

			Calendar calendar = Calendar.getInstance();
			int secs = calendar.get(Calendar.SECOND);
			int mins = calendar.get(Calendar.MINUTE);
			int hours = calendar.get(Calendar.HOUR_OF_DAY);

			Time tm = new Time(hours, mins, secs);
			purch.setTime(tm);

			purch.setApproval("PENDING");

			// write the purchase
			if (purch.insertIntoDatabase(conn)) {
				// got it!
				System.out.println(Prog + "inserted PENDING purchase");
			} else {
				// failed
				System.out.println(Prog + "ERROR insert pending purchase FAILED");
			}

			int nbought = 0;
			int nmissed = 0;
			int totalpurch = 0;

			for (TicketBean tk : userCart.getCartAL()) {
				//
				tk.setPurchase_id(purch.getId());
				if (tk.insertIntoDatabase(conn)) {
					nbought++;
					totalpurch += tk.getPrice();
				} else {
					nmissed++;
				}
			}

			System.out.println(Prog + "Tickets inserted:" + nbought + " missed:" + nmissed + " totpurch:" + totalpurch);

			// udpate the purchase
			
			purch.setTotal(totalpurch);
			purch.setApproval("YES:" + nbought + " Tickets");

			// write the purchase
			if (purch.updateDatabase(conn)) {
				// got it!
				System.out.println(Prog + "updated purchase");
			} else {
				// failed
				System.out.println(Prog + "ERROR update purchase FAILED");
			}
			
			// move purchased seats to temp cart
			CartBean purchCart = new CartBean();
			
			for( TicketBean tk: userCart.getCartAL() ) {
				if( tk.isPurchased() ) {
					purchCart.addTicket(tk);
				}
			}
			
			ArrayList<TicketBean> tklist = userCart.getCartAL();
			for( int i=0; i<tklist.size(); i++  ) {
				if( tklist.get(i).isPurchased() ) {
					tklist.remove(i);
				}
			}
			userCart.setCart(tklist);
			
			
			// setup what we need for the checkout page
			session.setAttribute("purchase", purch);
			session.setAttribute("nbought", nbought);
			session.setAttribute("nmissed", nmissed);
			session.setAttribute("cart", userCart);
			session.setAttribute("purchCart", purchCart);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if (conn != null) {
				connectionPool.free(conn);
			}
		}

		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/Checkout.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
