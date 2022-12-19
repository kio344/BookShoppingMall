
function replaceCKEDITOR() {
	let reviewContexts = document.getElementsByClassName('reviewContext');

	for (let i = 0; i < reviewContexts.length; i++) {

		CKEDITOR.replace(reviewContexts[i].id,{
			width:'100%'
		});
		
		
	}
}

function  displayReviewEvent(writeReviewBtnE){
	
	const el=writeReviewBtnE.target.parentElement.parentElement.parentElement.parentElement.children[1];
	console.log()
	el.className='reviewArea'
	
}

function  displayNoneReviewEvent(writeReviewBtnE){
	
	const el=writeReviewBtnE.target.parentElement.parentElement.parentElement.parentElement.children[1];
	console.log()
	el.className='reviewArea'
	
}

function donBtnEvent(){
	let doneBtnEl=document.getElementsByClassName('doneBtn')
	for(let i=0;i<doneBtnEl.length;i++){
		doneBtnEl[i].addEventListener('click',function(e){
			let reviewArea=e.target.parentElement.parentElement;
			reviewArea.className='reviewArea dn'
		})
	}
}

function payment_complete(){
	
	let btns=document.getElementsByClassName('payment_complete');
	console.log(btns)
	
	for(let i = 0;i<btns.length;i++){
		btns[i].addEventListener('click',function(e){
			console.log(e.target.dataset.payment)
			let paymentNum=e.target.dataset.payment
			
			let formdata=new FormData();
			formdata.append('paymentNum',paymentNum);
			
			let xhr=new XMLHttpRequest;
			xhr.open("post","myorder/paymentcomplete");
			xhr.responseType='json'
			xhr.addEventListener("readystatechange", function(getreviewE) {

			if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
				let response = xhr.response;
				if(response.result){
					alert('처리 되었습니다.')
					location.reload();
				}else{
					alert(response.message)
				}
				
			}

		})
			
			xhr.send(formdata)
			
		})
	}

	
}



window.addEventListener('DOMContentLoaded', function(e) {

	replaceCKEDITOR();
	
	donBtnEvent();

	let writeReviewBtns = document.getElementsByClassName('writeReview');



	function writeReviewBtnEvent(writeReviewBtnE) {
		let paymentnum = writeReviewBtnE.target.dataset.payment

		let formData = new FormData();

		formData.append("paymentnum", paymentnum);

		let xhr = new XMLHttpRequest();

		xhr.responseType = "json"
		xhr.open("post", "myorder/getreview")

		

		xhr.addEventListener("readystatechange", function(getreviewE) {

			if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
				let response = xhr.response;
				console.log(response)

				CKEDITOR.instances['reviewContext_' + paymentnum].setData(response.content);
				
				let reviewEl=document.getElementById(`payment_`+paymentnum).children[4].children[0]
				
				reviewEl.value=response.score
			}

		})

		xhr.send(formData);

	}

	for (let i = 0; i < writeReviewBtns.length; i++) {
		let writeReviewBtn = writeReviewBtns[i];
		writeReviewBtn.addEventListener("click", (writeReviewBtnE) => {
			
			writeReviewBtnEvent(writeReviewBtnE)
			displayReviewEvent(writeReviewBtnE)
			
		})
	}
	
	payment_complete();



})


