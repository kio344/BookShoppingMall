/**
 * 
 */


//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.

let recipient_name;
let recipient_mobile;

let zip_code;
let roadAddress;
let detailAddress;


let form;
var clientKey = 'test_ck_ADpexMgkW36PDm5OyXMrGbR5ozO0'
window.addEventListener("DOMContentLoaded", function(e) {
	let ifrm_back = document.getElementById('ifrm_back');

	let button = document.getElementById('buy');
	let ifrm = document.getElementsByName('ifrm');
	let findAddressBtn = document.getElementById('findAddressBtn');
	zip_code = document.getElementById('zipCode');
	roadAddress = document.getElementById('roadAddress');
	detailAddress = document.getElementById('detailAddress')
	recipient_name = document.getElementById('recipient_name')
	recipient_mobile = document.getElementById('recipient_mobile')
	form = document.getElementById('form')

	ifrm = document.getElementById('ifrm');






	button.addEventListener("click", function(e) {

		const xhr_valid = new XMLHttpRequest();

		xhr_valid.responseType = "json";
		xhr_valid.open("POST", "../payment/validProcess");

		xhr_valid.addEventListener("readystatechange", (valid) => validation(valid))

		xhr_valid.send(new FormData(form));






	})


	ifrm_back.addEventListener("click", function(e) {
		ifrm_back.classList = "dn";
	})



});

function sample4_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			zip_code.value = data.zonecode;
			roadAddress.value = data.address;

		}
	}).open();
}

function tossProcess(e) {
	ifrm_back.classList = "";

	//form.target='ifrm';
	var tossPayments = TossPayments(clientKey)

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "../payment/process");
	xhr.responseType = "json"
	xhr.addEventListener("readystatechange", function(e) {

		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			let response = xhr.response;
			console.log(response);


			tossPayments.requestPayment('카드', {
				amount: response.product.price * response.count,
				orderId: new Date().getTime() + '__' + response.num,
				orderName: response.product.bookName,
				customerName: response.user.memNm,
				successUrl: 'http://localhost:3000/BookShoppingMall/shop/payment/result/sc',
				failUrl: 'http://localhost:3000/BookShoppingMall/shop/payment/result/fail',
			})


		}

	});


	xhr.send(new FormData(form));




}

function validation(valid) {

	if (valid.currentTarget.status == 200 && valid.currentTarget.readyState == XMLHttpRequest.DONE) {
		console.log('로딩완료')
		console.log(valid.currentTarget.response);

		let response = valid.currentTarget.response

		if (response.result) {	//데이터 검증 성공
			console.log("데이터 검증 성공")

			tossProcess();
		} else {					//데이터 검증 실패
			console.log('데이터 검증 실패')
			form.submit();
		}

	}


}
