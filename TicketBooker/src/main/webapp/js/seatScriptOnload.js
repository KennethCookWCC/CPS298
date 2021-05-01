/*
 * seatScriptOnload.js -- respond to clicks on seats
 * add a seat to the cart, or remove a seat from the cart
 *
 * TODO: rebuild the cartArr[] from the cartBean on page load
 * <body onload="seatlistbuild()" or something like that
 */
 
let cartArr = []; // holds event.target object references
let cartStr = ""; // holds pretty selected seat list
let formStr = ""; // holds hidden internal seat list
let newCart = true; // assume new cart unless we have to load existing selected seats
let chgCart = false; // assume no changes to the cart
let ssol = "seatScriptOnLoad:";	// name for console messages

// what is this for?
// not referenced anywhere that I can find
/*
function turnGreen() {
	
	this.classList.remove("unit");
	this.classList.add("unit-clicked");
	
}
*/

// update the document for the user
function updateDOM() {
	document.getElementById("cartList").innerHTML = cartStr;
	document.getElementById("cartForm").innerHTML = formStr;
}


// build cartStr and formStr from cartArr[]
function cartArr2Str() {
	cartStr = "";
	formStr = "";

	var showingId = "";
	cartArr.forEach(function(seat) {
		let seatStr = "<li>Row " + seat.dataset.row + ", Seat " + seat.dataset.number + "</li>";
		cartStr += seatStr;

		let hiddenSeat = '<input type="hidden" name="id" value="' + seat.getAttribute('id') + '">';
		formStr += hiddenSeat;

	})

	showingId = document.getElementById("showing").getAttribute("data-showingId");
	formStr += '<input type="hidden" name="showingId" value=' + showingId + '>';

	if( (cartArr.length >= 1) || !newCart ) {
		if( newCart ) {
			formStr += '<input type="submit" class="btn btn-primary" name="submit" value="Add to Cart">';
		} else {
			formStr += '<input type="submit" class="btn btn-primary" name="submit" value="Update Cart">';
		}
	}
}


// add clicked open seat to list
function addSeat(target) {
	chgCart = true;
	
	// add seat to end of list
	cartArr.push(target);
	
	// build UI lists
	cartArr2Str();
	
	// put the build lists into the UI document
	updateDOM();
	
	console.log(ssol+"tried to add to cart list");
}


// remove clicked selected seat
function removeSeat(seatId){
	var forRemoval;

	chgCart = true;
	
	for(i=0; i<cartArr.length; i++){
		if (cartArr[i].id == seatId){
			forRemoval=i;
			console.log(ssol+"found seat for removal");
		}
		else{
			console.log(ssol+"NO SEAT for removal");
		}
	}

	console.log(ssol+"found for removal: "+forRemoval);
	
	cartArr.splice(forRemoval,1);
	
	console.log(ssol+"cartArr after removal: "+cartArr);
	
	cartArr2Str();
	updateDOM();
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
	
	console.log(ssol+"cartArr"+cartArr);
	console.log(ssol+"cartStr"+cartStr);
	
	// add a seat?
	// only if target is of the class "unit"
	if(target.className.match("unit")){
		// yes
		target.classList.add("clicked");
		target.classList.remove("unit");
		console.log(ssol+"the class was clicked ");
		
		var seatId = target.getAttribute('id');
		addSeat(target);
	}
	else if(target.className.match("clicked")){
		console.log(ssol+"this contains class 'clicked' ");
		target.classList.add("unit");
		target.classList.remove("clicked");
		
		var seatId = target.getAttribute('id');
		removeSeat(seatId);

	}
}

// tie the listener to the body
// probably should tie this to the seat array div
// not the whole body

// move this into the jsp file
function seatScriptSetListener() {
	document.getElementById("body").addEventListener("click", clickSeat);
}


// build selected seat list from elements with "clicked" class 
// https://www.w3schools.com/jsref/event_onload.asp
// need: <body onload="onLoadBuildSeats()">
function onLoadBuildSeats() {
	
	// I think this will work
	// from: https://www.w3schools.com/jsref/met_document_getelementsbyclassname.asp
	var selected = document.getElementsByClassName("clicked");
	
	var nsel = selected.length;
	
	console.log(ssol+"onload:Loading "+nsel+" seats");
	
	// any seats to load?
	if( nsel > 0 ) {
		// yes, so we are updating not adding
		newCart = false ;
		// push all the selected seats into cartArr
		for( i=0; i<nsel; i++ ) {
			cartArr.push(selected[i]);
		}
		
		cartArr2Str();
		updateDOM();
		console.log(ssol+"tried to existing add to cart list");
	}
	
}


// not sure what this is for
// when does this execute?
// I don't think we need this
/*
for(i=0; i<5; i++){
	let rowArr = ["a","b","c","d","e"];
	for(n=1; n<=14; n++){
		let rowStr = rowArr[i] + n;
		console.log(rowStr);
		var el = document.getElementById(rowStr);

	}
	
}
*/
