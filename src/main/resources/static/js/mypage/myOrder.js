
function replaceCKEDITOR(){
	let reviewContexts=document.getElementsByClassName('reviewContext');

	for(let i =0; i<reviewContexts.length;i++){
		
		CKEDITOR.replace( reviewContexts[i].id );
		
	}
}



window.addEventListener('DOMContentLoaded',function(e){
	
	replaceCKEDITOR();
	 
	let writeReviewBtns=document.getElementsByClassName('writeReview');
	
	function writeReviewBtnEvent (writeReviewBtnE){
		let paymentnum=writeReviewBtnE.target.dataset.payment
		
		let formData=new FormData();
		
		formData.append("paymentnum",paymentnum);
		
		let xhr=new XMLHttpRequest();
		
		xhr.responseType="json"
		xhr.open("post","myorder/getreview")
		
		
		
		xhr.addEventListener("readystatechange",function(getreviewE){
			
			if(xhr.readyState == XMLHttpRequest.DONE && xhr.status==200){
				console.log(xhr.response);
			}
			
		})
		
		xhr.send(formData);

	}
	
	for(let i=0;i<writeReviewBtns.length;i++){
		let writeReviewBtn=writeReviewBtns[i];
		writeReviewBtn.addEventListener("click",(writeReviewBtnE)=>writeReviewBtnEvent(writeReviewBtnE))
	}
	
	

})

    
