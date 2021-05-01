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

/**
 * Servlet implementation class MovieServlet
 *
 * change showing list to string instead of Date
 */
@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Prog = "MovieServlet:";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// setup guest user info
	private void mkguest(UserBean user) {
		user.setAdmin(false);
		user.setLoginOK(false);
		user.setName("Guest");
		user.setLogin("Guest");
		user.setId(0);
	}
	
	@SuppressWarnings("deprecation")
	private String mknicedatestr( java.sql.Date dt ) {
		String out = "";
		
		String months[] = { "January", "February", "March", "April", "May", "June", 
				"July", "August", "September", "October", "November", "December" };
		
		int mo = dt.getMonth();
		int dy = dt.getDate();
		int yr = dt.getYear();
		
		out = months[mo] + " " + dy + ", " + (yr+1900) ;
		
		return( out );
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get context and check for session
		ServletContext servletContext = getServletContext();
		ConnectionPool connectionPool = (ConnectionPool) servletContext.getAttribute("connectionPool");

		CartBean cartBean;
		UserBean userBean;
		java.sql.Date viewdate = new java.sql.Date(2021 - 1900, 1 - 1, 15);
		String viewdatestr = viewdate.toString();
		String viewdatenice = mknicedatestr( viewdate );
		
		Boolean isnewSession = false;
		Boolean isnewCart = false;
		Boolean isnewUser = false;

		HttpSession session = request.getSession(false);
		if (session == null) {
			// Not created yet. Now do so yourself.
			session = request.getSession();
			isnewSession = true;

			// setup application state

			// need an empty cart
			cartBean = new CartBean();
			session.setAttribute("cart", cartBean);
			isnewCart = true;

			// need a guest user
			userBean = new UserBean();
			mkguest(userBean);

			session.setAttribute("user", userBean);
			isnewUser = true;

			// Not created yet. Now do so yourself.
			// set default viewDate
			session.setAttribute("viewDate", viewdatestr );
			session.setAttribute("viewDateStr", viewdatenice );

		} else {
			// have session, validate some objects

			// do we have a cart?
			cartBean = (CartBean) session.getAttribute("cart");
			if (cartBean == null) {
				// no
				System.out.println(Prog + "Existing Session:No Cart?");
				cartBean = new CartBean();
				cartBean.setValidated(false);
				session.setAttribute("cart", cartBean);
				isnewCart = true;
			} else {
				// have cart!
				System.out.println(Prog + "Existing Session:Existing Cart has:" + cartBean.count() + " seats");
			}

			// do we have a user?
			userBean = (UserBean) session.getAttribute("user");
			if (userBean == null) {
				// no user
				System.out.println(Prog + "Existing Session:No User? mkguest");
				userBean = new UserBean();
				mkguest(userBean);
				session.setAttribute("user", userBean);
				isnewUser = true;
			} else {
				// have user!
				System.out.println(Prog + "Existing Session:Existing User name:" + userBean.getName() + " login:" + userBean.getLogin() );
			}
			
			// do we have a viewDate?
			String ckviewdate = (String) session.getAttribute("viewDate");
			if( ckviewdate == null ) {
				// no viewdate set
				System.out.println(Prog + "Existing Session:No viewDate, use default:" + viewdatestr + " nice:" + viewdatenice );
				session.setAttribute("viewDate", viewdatestr );
				session.setAttribute("viewDateStr", viewdatenice );
			} else {
				System.out.println(Prog + "Existing Session:Cur viewDate is:" + ckviewdate );
			}

		}
		
		// at this point we have a cartBean, userBean, default viewDate

		MovieListBean movieListBean = new MovieListBean();
		ShowingBean showingBean = new ShowingBean();

		// ShowingListBean sblist = new ShowingListBean();
		// viewdate = new java.sql.Date(2021 - 1900, 1 - 1, 15);
		String dateParam;
		dateParam = request.getParameter("date");

		// change the date?
		if (dateParam != null) {
			// yes
			System.out.println(Prog + "Setting Date:" + dateParam );
			viewdate = java.sql.Date.valueOf(dateParam);
			viewdatestr = viewdate.toString();
			viewdatenice = mknicedatestr( viewdate );
			
			System.out.println(Prog + "sqlDate:" + viewdatestr + " nice:" + viewdatenice );
			session.setAttribute("viewDate", viewdatestr );
			session.setAttribute("viewDateStr", viewdatenice );
			// set sql date = to dateParam
			// System.out.println("dateParam: "+dateParam);
			// String testDate ="2021-01-15";
			//String[] dateExplode = dateParam.split("-");
			// System.out.println("date explode: "+Arrays.asList(dateExplode));
			//Integer year = Integer.parseInt(dateExplode[0]) - 1900;
			//Integer month = Integer.parseInt(dateExplode[1]) - 1;
			//Integer day = Integer.parseInt(dateExplode[2]);
			//System.out.print("year(-1900):" + Integer.toString(year));
			//System.out.print(" month(-1):" + Integer.toString(month));
			//System.out.println(" day:" + Integer.toString(day));

			//viewdate = new java.sql.Date(year, month, day);
		} else {
			// request.putParameter()
			System.out.println(Prog + "Not changing date, viewdate:" + viewdatestr );
		}

		Connection conn = null;

		try {
			conn = connectionPool.getConnection();
			List<MovieBean> ml = movieListBean.loadMovies(conn, viewdate);
			List<ShowingBean> sl;

			// get date from request attribute and parse to make javaSql date,

//			String showingDate = (String) request.getAttribute("showingDate");

			// get movie dates
			ArrayList<String> showingDates = showingBean.getShowingDates(conn);

			// query db for showings on that day
			sl = movieListBean.loadFromDBAfterTime(conn, viewdate);


		
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

			request.setAttribute("ml", ml);

			request.setAttribute("showingDates", showingDates);
			System.out.println("showingDates: " + showingDates.toString());


//			request.setAttribute("message", "this is my message. u have received it.");
			request.setAttribute("msg", "this is my message. u have received it.");

			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if (conn != null) {
				connectionPool.free(conn);
			}
		}

		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/moviePage.jsp");
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
