package jsp_tag;

/*
 * Custom JSP tag to generate the seat array on the page
 * 
 * showing indicates the geometery
 * seatlist has the already sold seats
 * cart has our selected seats
 * 
 * creates a div for each row
 * and a div within that for each seat
 * 
 * sets the class appropriately for reserved, selected, and available seats
 */

import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import java.io.*;

import beans.CartBean;
import beans.SeatBean;
import beans.ShowingBean;
import java.util.List;

public class showingSeats extends SimpleTagSupport {
	private List<SeatBean> seatList;
	private ShowingBean showing;
	private String output = "";

	public void setSeatList(List<SeatBean> list) {
		this.seatList = list;
	}

	public void setShowing(ShowingBean showing) {
		this.showing = showing;
	}

	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		CartBean cartBean = (CartBean) request.getSession().getAttribute("cart");
		if( cartBean == null ) {
			System.out.println("ShowingSeats:doTag:ERROR:NO CART?");
		}

		System.out.println("ShowingSeats:doTag:cart has:" + cartBean.count() + " seats");
		
//	   seatList.forEach((s)-> {
////		   String row = s.getRow();
////		   int number = s.getNumber();
////		   int id = s.getSeatId();
//		   output += s.toString();
//		   output+= "\n";
//	   });
//	   

		String rowLabel = "";
		for (int i = 0; i < showing.getScreenRows(); i++) {
			
			rowLabel = seatList.get( i * showing.getScreenCols() ).getRow();
			
			output += "<div class=\"row seatRow justify-content-center\">";
			
			output += ("<div class=\"col-1\">" + rowLabel + "</div>");
			
			for (int n = 0; n < showing.getScreenCols(); n++) {
				int index = (i * (showing.getScreenCols()) + n);
				int number = seatList.get(index).getNumber();
				String row = seatList.get(index).getRow();
				int seatId = seatList.get(index).getSeatId();
				String title = row + number;

				// int id = seatList.get(index).getSeatId();

				// already reserved by someone else?
				Boolean reserved = seatList.get(index).getTaken();
				
				// in our cart?
				Boolean incart = cartBean.containsTicket(showing.getShowingId(), seatId); 
				
				// seat attributes:
				// unit = available
				// takenUnit = someone else bought it
				// clicked = in our cart

				// in our cart and someone else's seat?
				if( reserved && incart ) {
					// this is an error!
					System.out.println("ShowingSeats:doTag:ERROR:reserved and incart seat:" + title );
				}
				
				// someone else's seat?
				if ( reserved ) {
					// yes - format as not click-able
					output += "<div class=\"takenUnit col-1\" id=\"" + seatId + "\" ";
					output += "data-bs-toggle=\"tooltip\" data-bs-placement=\"top\" ";
					output += "title=\"" + title + "\" >&nbsp;</div>";
				} else if ( incart ) {
					// in our cart?
					// yes, click-able but ours
					output += "<div class=\"clicked col-1\" id=\"" + seatId + "\" ";
					output += "data-row=\"" + row + "\" data-number=\"" + number + "\" ";
					output += "data-bs-toggle=\"tooltip\" data-bs-placement=\"top\" ";
					output += "title=\"" + title + "\" >&nbsp;</div>";
				} else {
					// open available seat
					output += "<div class=\"unit col-1\" id=\"" + seatId + "\" ";
					output += "data-row=\"" + row + "\" data-number=\"" + number + "\" ";
					output += "data-bs-toggle=\"tooltip\" data-bs-placement=\"top\" ";
					output += "title=\"" + title + "\" >&nbsp;</div>";
				}
				
//			   title = rowLabel + Integer.toString(index);
//			   output += title + "\n";


			}
			output += "</div>";
		}

//	   <c:forEach begin="1" end="${showing.screenRows}" varStatus="loop">
//		<div class="row seatRow justify-content-center">
//		<div class="col-1">${seatList.get((loop.index*showing.screenCols)-1).getRow()}</div>
//		<c:forEach begin="1" end="${showing.screenCols}" varStatus="loop2">
//	<div class="unit col-1" id="${seatList.get((loop.index*showing.screenCols)-1).getRow().concat(seatList.get(loop2.index-1).getNumber())}" data-bs-toggle="tooltip" data-bs-placement="top" title="${seatList.get((loop.index*showing.screenCols)-1).getRow().concat(seatList.get(loop2.index-1).getNumber())}" >&nbsp;</div>
//		</c:forEach>
//	</div>
//	</c:forEach>

		JspWriter out = getJspContext().getOut();
		out.println(output);
	}
}
