package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartBean implements Serializable {
	private ArrayList<TicketBean> cart;
	private String test = "This is a test string.";
	
	public  List<TicketBean> getCart(){
		return cart;
	}
	public  ArrayList<TicketBean> getCartAL(){
		return cart;
	}
	public void setCart(ArrayList<TicketBean> cart) {
		this.cart = cart;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public void addTicket(TicketBean ticket) {
		ArrayList<TicketBean> tix = new ArrayList<TicketBean>();
		if(cart != null) {
			tix = this.cart;
		}
		tix.add(ticket);
		this.setCart(tix);
	}
	
	public boolean containsTicket(int showingId, int seatId ) {
		boolean contained= false;
		if(cart !=null) {
			for(int i=0; i< cart.size(); i++) {
				int cartShowId = cart.get(i).getShowing_id();
				int cartSeatId = cart.get(i).getSeatId();		// KC - was getID which returns ticketID not seatID
				if(cartShowId == showingId && cartSeatId == seatId ) {
					contained = true;
					break;
				}
			}
		}
		return contained;
	}
	
	public void deleteShowing(int showingId ) {
		CartBean result = new CartBean();
		
		int n = 0;
		if(cart != null) {
			for(int i=0; i< cart.size(); i++) {
				int cartShowId = cart.get(i).getShowing_id();
			
				if(cartShowId != showingId ) {
					result.addTicket(cart.get(i));
				}
			}
		}
		
		this.setCart(result.getCartAL());
		
		return;
	}
	
	public int getCount() {
		/*
		String out = "";
		out = "" + this.count();
		return( out );
		*/
		return( this.count() );
	}
	public int count() {
		if(cart != null) {
			return cart.size();
		}
		else {
			return 0;
		}
	}
	public String toString() {
		String out = "";
		
		out = "Cart has " + this.count() + " seats:[";
		out += this.getCart();
		out += "]";
		return out;
	}
	

	public ArrayList<TicketBean> getCartUI(Connection conn, CartBean cart){
		
		CartBean newCart = new CartBean();
		List cartList = cart.getCart();
		
		
		
		try {
			
			
			String sql;
//			sql = "SELECT seat.id, showing.id, movie.title, showing.time, showing.price, seat.seat_number, seat.row FROM showing LEFT JOIN screen ON  showing.screen_id = screen.id JOIN seat ON screen.id = seat.screen_id LEFT JOIN movie on showing.movie_id = movie.id WHERE showing.id =10 AND seat.id =1142 ";
			sql = "select showing.id, showing.date, showing.time, showing.price, seat.seat_number, seat.row, movie.title from movie join showing on movie.id = showing.movie_id join screen on showing.screen_id = screen.id join seat on screen.id = seat.screen_id where showing.id = ? AND seat.id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);

		
			cartList.forEach((<TicketBean> t)->{
				String showId = t.getShowingId();
				String seatId = t.
				stmt.setInt(1, showId);
				stmt.setInt(2, seatId);
				ResultSet results = stmt.executeQuery();
				System.out.println(results.toString());
				
			
			});
			
	public ArrayList<TicketBean> getCartUI(Connection conn){
		CartBean newCart = new CartBean();
		ArrayList cartList = this.getCartAL();
		String sql = "select showing.id, showing.date, showing.time, showing.price, seat.seat_number, seat.row, movie.title from movie join showing on movie.id = showing.movie_id join screen on showing.screen_id = screen.id join seat on screen.id = seat.screen_id where showing.id = ? AND seat.id =?";
		try {
			int showId;
			int seatId;
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			for(int i=0; i<cartList.size(); i++) {
				TicketBean ticket = (TicketBean) cartList.get(i);
				showId = ticket.getShowing_id();
				seatId = ticket.getSeatId();
				stmt.setInt(1, showId);
				stmt.setInt(2, seatId);
				ResultSet results = stmt.executeQuery();
				
				if(results.next()) {
					TicketBean newTicket = new TicketBean();
					newTicket.setTitle(results.getString("movie.title"));
					newTicket.setSeatId(seatId);
					newTicket.setRow(results.getString("seat.row"));
					newTicket.setNumber(Integer.parseInt(results.getString("seat.seat_number")));
//					newTicket.setTime(results.getString("showing.time"));
//					newTicket.setDate(Integer.parseInt(results.getString("showing.time")));
					newTicket.setPrice(Integer.parseInt(results.getString("showing.price")));
					System.out.println("ticket added: "+ newTicket.toString());
					newCart.addTicket(newTicket);
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  newCart.getCartAL();

	}
	
	
//	test function to load all tickets for user 1 into cart
	public void loadTicketsForCust(Connection conn) throws SQLException {
//		PreparedStatement stmt = conn.prepareStatement("SELECT showing.id, showing.date, showing.time, movie.title, movie.rated, movie.release_date, movie.image_link, screen.name, screen.max_rows, screen.max_cols FROM showing JOIN movie ON showing.movie_id = movie.id JOIN screen ON showing.screen_id = screen.id");
//		ResultSet results = stmt.executeQuery();
//		
//		if(results.next()) {
//			date = results.getDate("showing.date");
//			if(date == null) {
//				date = getCurrentDay();
//			}
//			time = results.getTime("showing.time");
//			MovieBean movieBean = new MovieBean();
//			movieBean.setTitle(results.getString("movie.title"));
//			movieBean.setRating(MovieBean.Rating.fromIndex(results.getInt("movie.rated") - 1));
//			movieBean.setReleaseDate(results.getDate("movie.release_date"));
//			movieBean.setImageLink(results.getString("movie.image_link"));
//			movie = movieBean;
//			screenName = results.getString("screen.name");
//			screenRows = results.getInt("screen.max_rows");
//			screenCols = results.getInt("screen.max_cols");
//			return true;
//		} else {
//			return false;
//		}
	}
	
}
