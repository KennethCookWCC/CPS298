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
@WebServlet("/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartServlet() {
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
		if( userBean == null ) {
			// then we shouldn't be here.
			System.out.println(Prog+"have session, but no user!");
						
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
			dispatcher.forward(request, response);		
					
			return;
		}
		
		CartBean userCart = (CartBean) session.getAttribute("cart");
		if( userCart == null ) {
			// then we shouldn't be here.
			System.out.println(Prog+"have session, but no cart!");
			
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/MovieServlet");
			dispatcher.forward(request, response);		
			
			return;
		}
		
		// process request parameters
		String[] seatIds = request.getParameterValues("id");
		String showingId = request.getParameter("showingId");
		
		// build temp cart of posted seats
		CartBean postCart = new CartBean();
		
		int postshowid = 0;
		
		if( showingId != null ) {
			postshowid = Integer.parseInt(showingId);
			System.out.println(Prog+"ShowingId: "+showingId);
			
		} else {
			System.out.println(Prog+"No ShowingId");
		}
		
		// if we have posted tickets 
		// build temp list of them
		if(showingId != null && seatIds != null) { 
			
			// all these seats are for one showing
			
			// run the list of seats
			for(int i=0; i< seatIds.length; i++) {
				int seatid = Integer.parseInt(seatIds[i]);
				System.out.println("SeatID: "+seatIds[i]);
				
				TicketBean ticket = new TicketBean();
				ticket.setSeatId(seatid);
				ticket.setShowing_id(postshowid);
				if (!postCart.containsTicket(postshowid,seatid)) {
					postCart.addTicket(ticket);
				}
			}
		}
		
		// is this an update or just a showing of the cart?
		if( showingId != null ) {
			// need to update the cart
			
			// eliminate any tickets for this showing
			userCart.deleteShowing(postshowid);
			
			// add the ones from this post
			ArrayList<TicketBean> pclist = postCart.getCartAL() ;
			if( pclist != null ) {
				for( TicketBean tk : pclist ) {
					userCart.addTicket(tk);
				}
			}
			
			// update the session user cart 
			session.setAttribute("cart", userCart);
		}
		
		// build list of unique showing IDs from the userCart
		HashSet<Integer> showingIds = new HashSet<Integer>(); 

		ArrayList<TicketBean> uclist = userCart.getCartAL() ;
		if( uclist != null ) {
			for( TicketBean tk : userCart.getCartAL() ) {
				showingIds.add(tk.getShowing_id());
			}
		}
		
		System.out.print(Prog+"Showing Ids in cart:[");
		for( Integer id : showingIds ) {
			System.out.print( id + " " );
		}
		System.out.println("]");
		
		// Check session stuff
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			
			
			// look up stuff for showing the cart
			// need showingID -> MovieTitle Date Time
			// need seatID -> seatstring (RowCol)
			ShowingBean showBean = new ShowingBean();
			SeatBean seatBean = new SeatBean();
			
			for( int idx=0; idx < userCart.count(); idx++ ) {
				TicketBean tb = userCart.getCartAL().get(idx);
				// popuate UI fields for each Ticket
				int showid = tb.getShowing_id();
				// ShowingBean showBean = new ShowingBean();
				// try to get the showing
				if( ! showBean.loadOneFromDatabase(conn, showid ) ) {
					// no showing
					System.out.println(Prog+"ERROR no showing for cart ticket:showid:" + showid );
				}
				
				int seatid = tb.getSeatId();
				// get the seat
				if( ! seatBean.loadOneFromDatabase(conn, seatid ) ) {
					// no seat
					System.out.println(Prog+"ERROR no seat for cart ticket:seatid:" + seatid );
				}
				
				// determine price based on time
				Time movieTime = showBean.getTime();
				int hour = movieTime.getHours();
				int price = 0;	// free if we screw up calculation
				// after 3 PM?
				if( hour > 15 ) {
					// regular price
					price = 1000;
				} else {
					// matinee
					price = 500;
				}
				
				// fill in cart ticket
				tb.setCustomerId(userBean.getId());
				tb.setDate(showBean.getDate());
				tb.setTime(movieTime);
				tb.setTitle(showBean.getMovie().getTitle());
				
				tb.setRow(seatBean.getRow());
				tb.setNumber(seatBean.getNumber());
				tb.setPrice(price);
			}
			
			// cart is validated
			userCart.setValidated(true);
			
			// update the session user cart 
			session.setAttribute("cart", userCart);
			
			/*
			if (session == null) {
			    // Not created yet. Now do so yourself.
				
			    session = request.getSession();
			    CartBean cartBean = new CartBean();
			    
			    for(int i=0; i< seatIds.length; i++) {
					TicketBean ticket = new TicketBean();
					ticket.setId(Integer.parseInt(seatIds[i]));
					cartBean.addTicket(ticket);
				}
			    
			    session.setAttribute("cart", cartBean);
			} else {
			    // Already created.
				CartBean cb = (CartBean) session.getAttribute("cart");
				
				if(showingId != null && seatIds != null) { // if we have tickets to add, add them
					for(int i=0; i< seatIds.length; i++) {
						System.out.println("SeatID: "+seatIds[i]);
						System.out.println("ShowingId: "+showingId);
						TicketBean ticket = new TicketBean();
						ticket.setSeatId(Integer.parseInt(seatIds[i]));
						ticket.setShowing_id(Integer.parseInt(showingId));
						if (!cb.containsTicket(Integer.parseInt(showingId), Integer.parseInt(seatIds[i]))) {
							cb.addTicket(ticket);
						}
					}
				}

				session.setAttribute("cart", cb);
			}
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if(conn != null) {
				connectionPool.free(conn);
			}
		}
		
		
		
// THIS IS NEW CODE TO GET/SET THE UI ELEMENTS FOR TICKETS IN THE CART
		
//		try {
//			Connection conn = connectionPool.getConnection();
//			ArrayList<TicketBean> cartListUI = postCart.getCartUI(conn);
//			userCart.setCart(cartListUI);
//			// TODO Auto-generated catch block
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}


		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/ShowCart.jsp");
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
