package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
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
import beans.TicketBean;
import jdbc.ConnectionPool;

/**
 * Servlet implementation class MovieServlet
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
		ServletContext servletContext = getServletContext();
		ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");
		
		
		
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			
			HttpSession session = request.getSession(false);
			String[] seatIds = request.getParameterValues("id");
			String showingId = request.getParameter("showingId");
			
			if (session == null) {
				CartBean cartBean = new CartBean();
				
			    // Not created yet. Now do so yourself.
			    session = request.getSession();
			    
			    for(int i=0; i< seatIds.length; i++) {
					TicketBean ticket = new TicketBean();
					ticket.setId(Integer.parseInt(seatIds[i]));
					cartBean.addTicket(ticket);
				}
			    
			    session.setAttribute("cart", cartBean);
			} else {
			    // Already created.
				CartBean cb = (CartBean) session.getAttribute("cart");
				
				for(int i=0; i< seatIds.length; i++) {
					System.out.println("SeatID: "+seatIds[i]);
					System.out.println("ShowingId: "+showingId);
					TicketBean ticket = new TicketBean();
					ticket.setId(Integer.parseInt(seatIds[i]));
					ticket.setShowing_id(Integer.parseInt(showingId));
					cb.addTicket(ticket);
				}

				session.setAttribute("cart", cb);
			}
			


		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if(conn != null) {
				connectionPool.free(conn);
			}
		}
		
		String testAttr = request.getParameter("testAttr");
		request.setAttribute("testAttr", testAttr);
		
		
		
		
		
		
//		String showingId = request.getParameter("showingId");
//		session.setAttribute("cart", cart);
		
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
