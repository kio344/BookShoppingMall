let showReviewBtn
let showSameProductBtn
let productReviewEl
let sameProductEl

function init(){
	 showReviewBtn=document.getElementById('showReview')
	 showSameProductBtn=document.getElementById('showSameProduct')

	 productReviewEl=document.getElementById('productReview')
	 sameProductEl=document.getElementById('sameProduct')

}

function showSameProductEvent(){
	
	showSameProductBtn.addEventListener('click',function(){
		sameProductEl.className="sameProduct "
		productReviewEl.className="productReview dn"
	})
	
}

function showReviewEvent(){
	
	showReviewBtn.addEventListener('click',function(){
		sameProductEl.className="sameProduct dn"
		productReviewEl.className="productReview"
	})
	
}

window.addEventListener("DOMContentLoaded",function(){
	
	init();
	

	showSameProductEvent();
	showReviewEvent();
	
})