package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import beans.SeatBean;
import beans.ShowingBean;
import jdbc.ConnectionPool;

/**
 * Servlet implementation class ShowingServlet
 */
@WebServlet("/ShowingServlet")
public class ShowingServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext servletContext = getServletContext();
		ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");
		String showingParam = request.getParameter("showingId");
		int showingParamInt = Integer.parseInt(showingParam);
		
		ShowingBean bean = new ShowingBean();
		SeatBean seatBean = new SeatBean();
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			
			if(!bean.loadOneFromDatabase(conn, showingParamInt)) {
				// TODO handling for non-existent showings
				System.out.println("no showing");
			}
			
			List<SeatBean> seatList = seatBean.getAllSeats(conn,showingParamInt);
			ArrayList<Integer> taken = seatBean.getTakenSeats(conn, showingParamInt);
			
			for(int i=0; i < seatList.size(); i++) {
				int checkId = seatList.get(i).getSeatId();
				if (taken.contains(checkId)) {
					seatList.get(i).setTaken(true);
				}
			}
			
			request.setAttribute("showing", bean);
			request.setAttribute("seatList", seatList);
			
			
//			HttpSession session = request.getSession(false);
//			if (session == null) {
//				CartBean cartBean = new CartBean();
//				
//			    // Not created yet. Now do so yourself.
//			    session = request.getSession();
//			    session.setAttribute("cart", cartBean);
//			} else {
//			    // Already created.
//				CartBean cart = (CartBean) session.getAttribute("cart");
//				String test = cart.getTest();
//				cart.setTest(test+" added");
//				session.setAttribute("cart", cart);
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if(conn != null) {
				connectionPool.free(conn);
			}
		}
		
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/SeatSelection7.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
