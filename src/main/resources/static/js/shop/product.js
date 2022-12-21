let showReviewBtn
let showSameProductBtn
let productReviewEl
let sameProductEl
let shareBtnEl
const productData={
	bookimg:'',
    writer:'',
    price:'',
    bookname:'',
    publisher:'',
    link:''
}

function init(){
	 showReviewBtn=document.getElementById('showReview')
	 showSameProductBtn=document.getElementById('showSameProduct')

	 productReviewEl=document.getElementById('productReview')
	 sameProductEl=document.getElementById('sameProduct')
	
	let bookinfo=document.getElementsByClassName('productInfo')[0].dataset;
	let num=bookinfo.booknum
	productData.bookimg=location.origin+bookinfo.bookimg
	productData.bookname=bookinfo.bookname
	productData.link=`shop/product/${num}`
	productData.price=bookinfo.price
	productData.publisher=bookinfo.publisher
	productData.writer=bookinfo.writer
	shareBtnEl=document.getElementById('addCart')
}

function showSameProductEvent(){
	
	showSameProductBtn.addEventListener('click',function(){
		sameProductEl.className="sameProduct "
		productReviewEl.className="productReview dn"
	})
	
}
function shareBtnEvent(){
	shareBtnEl.addEventListener('click',function(){
		Share(productData.bookimg,productData.bookname,productData.writer,productData.publisher,productData.price,productData.link)
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
	shareBtnEvent();
	
})