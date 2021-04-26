/*
 * seatScript.js -- respond to clicks on seats
 * add a seat to the cart, or remove a seat from the cart
 *
 * TODO: rebuild the cartArr[] from the cartBean on page load
 * <body onload="seatlistbuild()" or something like that
 */
 
let cartArr = []; // holds event.target object references
let cartStr=""; // 
let formStr="";

// what is this for?
// not referenced anywhere that I can find
function turnGreen(){
	
	this.classList.remove("unit");
	this.classList.add("unit-clicked");
	
}

function addSeat(target){
			
			cartArr.push(target);
			cartStr="";
			formStr="";
			
			var showingId="";
			cartArr.forEach(function(seat){
				let seatStr = "<li>Row "+seat.dataset.row+", Seat "+seat.dataset.number+"</li>";
				cartStr += seatStr;
				
				let hiddenSeat= '<input type="hidden" name="id" value="'+seat.getAttribute('id')+'">';
				formStr += hiddenSeat;
				
			})

			showingId = document.getElementById("showing").getAttribute("data-showingId");
			formStr += '<input type="hidden" name="showingId" value='+showingId+'>';
			
			if (cartArr.length>= 1){
				formStr += '<input type="submit" class="btn btn-primary" name="submit" value="Add to Cart">';	
			}
			
			
			
			
			document.getElementById("cartList").innerHTML = cartStr;
			document.getElementById("cartForm").innerHTML = formStr;
			console.log("tried to add to cart list");
		
}

function removeSeat(seatId){
	var forRemoval;

	for(i=0; i<cartArr.length; i++){
		if (cartArr[i].id == seatId){
			forRemoval=i;
			console.log("found seat for removal");
		}
		else{
			console.log("NO SEAT for removal");
		}
	}

	console.log("found for removal: "+forRemoval);
	cartArr.splice(forRemoval,1);
	console.log("cartArr after removal: "+cartArr);
	
	cartStr="";
	formStr="";
	var showingId="";
			
	cartArr.forEach(function(seat){
				let seatStr = "<li>Row "+seat.dataset.row+", Seat "+seat.dataset.number+"</li>";
				cartStr += seatStr;
				
				let hiddenSeat= '<input type="hidden" name="id" value="'+seat.getAttribute('id')+'">';
				formStr += hiddenSeat;
	})
	
	

	showingId = document.getElementById("showing").getAttribute("data-showingId");
	formStr += '<input type="hidden" name="showingId" value='+showingId+'>';
	if (cartArr.length>= 1){
	
		formStr += '<input type="submit" class="btn btn-primary" name="submit" value="Add to Cart">';
	}
	
	
	// update the rendered dynamic areas
	document.getElementById("cartList").innerHTML = cartStr;
	document.getElementById("cartForm").innerHTML = formStr;
}

// listener for body clicks
// reacts to clicks on seats
// seats are targets that have specific classes:
// .unit = available seat
// .unitTaken = sold seat not clickable
// .clicked = seat in our cart
// 
function clickSeat(){	
	// was it a seat?
	var target = event.target;
	
	console.log("cartArr"+cartArr);
	console.log("cartStr"+cartStr);
	
	// add a seat?
	// only if target is of the class "unit"
	if(target.className.match("unit")){
		// yes
		target.classList.add("clicked");
		target.classList.remove("unit");
		console.log("the class was clicked ");
		
		var seatId = target.getAttribute('id');
		addSeat(target);
	}
	else if(target.className.match("clicked")){
		console.log("this contains class 'clicked' ");
		target.classList.add("unit");
		target.classList.remove("clicked");
		
		var seatId = target.getAttribute('id');
		removeSeat(seatId);

	}
}

// tie the listener to the body
// probably should tie this to the seat array div
// not the whole body
document.getElementById("body").addEventListener("click", clickSeat);

// not sure what this is for
// when does this execute?
for(i=0; i<5; i++){
	let rowArr = ["a","b","c","d","e"];
	for(n=1; n<=14; n++){
		let rowStr = rowArr[i] + n;
		console.log(rowStr);
		var el = document.getElementById(rowStr);

	}
	
}
