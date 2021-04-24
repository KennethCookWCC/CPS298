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
import jdbc.ConnectionPool;

/**
 * Servlet implementation class LoginCustServlet
 *
 * setup session
 */
//@WebServlet("/LoginCustServlet")
public class LoginCustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCustServlet() {
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
		
		
		MovieListBean movieListBean = new MovieListBean();
		ShowingBean showingBean = new ShowingBean();
		ShowingListBean sblist = new ShowingListBean();
		java.sql.Date date = new java.sql.Date(121,0,16);
		String dateParam;
		dateParam = request.getParameter("date");

		if(dateParam != null) {
			// set sql date = to dateParam
//			System.out.println("dateParam: "+dateParam);
			String testDate ="2021-01-15";
			String[] dateExplode = dateParam.split("-");
//			System.out.println("date explode: "+Arrays.asList(dateExplode));
			Integer year = Integer.parseInt(dateExplode[0])-1900;
			Integer month = Integer.parseInt(dateExplode[1])-1;
			Integer day = Integer.parseInt(dateExplode[2]);
			System.out.println("year: "+Integer.toString(year)+"\n");
			System.out.println("month: "+Integer.toString(month)+"\n");
			System.out.println("day: "+Integer.toString(day)+"\n");
			
			date = new java.sql.Date(year, month, day);
		}
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			List<MovieBean> ml = movieListBean.loadMovies(conn,date);
			List<ShowingBean> sl;
		
			//get date from request attribute and parse to make javaSql date,
			
//			String showingDate = (String) request.getAttribute("showingDate");
			//get movie dates
			ArrayList<String> showingDates = showingBean.getShowingDates(conn);
			
			//query db for showings on that day
			sl = movieListBean.loadFromDBAfterTime(conn, date);
			
//			for(int i=0; i<ml.size(); i++) {
//				System.out.println(ml.get(i).toString());
//			}
//			for(int i=0; i<ml.size(); i++) {
//				if (showingBean.getMovie() == ml.get(i)) {
//					
//				}
//			}
//			request.setAttribute("movieList", movieListBean);
			request.setAttribute("showings", sl);
			request.setAttribute("ml",ml);
			
			request.setAttribute("showingDates",showingDates);
			System.out.println("showingDates: "+showingDates.toString());
			
//			request.setAttribute("message", "this is my message. u have received it.");
			request.setAttribute("msg", "this is my message. u have received it.");
			
			
			HttpSession session = request.getSession(false);
			if (session == null) {
				CartBean cartBean = new CartBean();
				
			    // Not created yet. Now do so yourself.
			    session = request.getSession();
			    session.setAttribute("cart", cartBean);
			} else {
				
				CartBean cart = (CartBean) session.getAttribute("cart");
				if( cart == null ) {
					cart = new CartBean();
				}
			
				String test = cart.getTest();
				cart.setTest(test);
			
				session.setAttribute("cart", cart);
				
			    // Already created.
//				CartBean cart = (CartBean) session.getAttribute("cart");
//				String test = cart.getTest();
//				request.setAttribute("test", test);
			}
			


		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if(conn != null) {
				connectionPool.free(conn);
			}
		}
		
		
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/moviePage.jsp");
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
