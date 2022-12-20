const productData={
	bookimg:'',
    writer:'',
    price:'',
    bookname:'',
    publisher:'',
    link:''
}

function shareEvent(e){
	let bookinfo=e.target.dataset;
	let num=bookinfo.booknum
	productData.bookimg=`productImages/${num%10}/${num}`
	productData.bookname=bookinfo.bookname
	productData.link=`shop/product/${num}`
	productData.price=bookinfo.price
	productData.publisher=bookinfo.publisher
	productData.writer=bookinfo.writer
	shareBtnEl=document.getElementById('addCart')
	Share(productData.bookimg,productData.bookname,productData.writer,productData.publisher,productData.price,productData.link)

	
	
}

function init(){
	
	let shareBtnsEl=document.getElementsByClassName('shareBtn')
	
	for(let i=0;i<shareBtnsEl.length;i++){
		shareBtnsEl[i].addEventListener("click",function(e){
			shareEvent(e);
		})
	}

	
}


function shareBtnEvent(){
	shareBtnEl.addEventListener('click',function(){
		Share(productData.bookimg,productData.bookname,productData.writer,productData.publisher,productData.price,productData.link)
	})
}



window.addEventListener("DOMContentLoaded",function(){
	
	init();
	

})