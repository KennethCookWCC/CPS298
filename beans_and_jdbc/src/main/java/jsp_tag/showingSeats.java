package jsp_tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import java.io.*;

import beans.SeatBean;
import beans.ShowingBean;
import java.util.List;

public class showingSeats extends SimpleTagSupport {
	private List<SeatBean> seatList;
	private ShowingBean showing;
	private String output ="";
	
	public void setSeatList(List<SeatBean> list) {
		this.seatList = list;
	}
	public void setShowing(ShowingBean showing) {
		this.showing = showing;
	}
	
   public void doTag() throws JspException, IOException {
	   PageContext pageContext = (PageContext) getJspContext();  
	   HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	   
//	   sl = (List<SeatBean>) request.getSession().getAttribute("seatList");
	   
//	   seatList.forEach((s)-> {
////		   String row = s.getRow();
////		   int number = s.getNumber();
////		   int id = s.getSeatId();
//		   output += s.toString();
//		   output+= "\n";
//	   });
//	   
	   
	   String rowLabel="";
	   for (int i=0; i< showing.getScreenRows(); i++) {
		   rowLabel = seatList.get(i*showing.getScreenCols()).getRow();
		   output += "<div class=\"row seatRow justify-content-center\">";
		   output += ("<div class=\"col-1\">" + rowLabel +"</div>");
		   for(int n=0; n< showing.getScreenCols(); n++) {
			   int index= (i * (showing.getScreenCols())+n);
			   int number = seatList.get(index).getNumber();
			   String row = seatList.get(index).getRow();
			   String title= row + number;

			   int id=seatList.get(index).getSeatId();
			   Boolean reserved =seatList.get(index).getTaken();
			   
			   if (reserved == false) {
				   output+="<div class=\"unit col-1\" id=\""+id+"\" data-row=\""+row+"\" data-number=\""+number+"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"top\" title=\""+ title +"\" >&nbsp;</div>";
			   }
			   else if (reserved ==true) {
				   output+="<div class=\"takenUnit col-1\" id=\""+id+"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"top\" title=\""+ title +"\" >&nbsp;</div>";
			   }
			   
//			   title = rowLabel + Integer.toString(index);
//			   output += title + "\n";
			   
		   }
		   output +="</div>";
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
