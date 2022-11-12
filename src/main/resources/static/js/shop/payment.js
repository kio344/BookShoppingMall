/**
 * 
 */


//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.

let zip_code;
let roadAddress;
let detailAddress;


window.addEventListener("DOMContentLoaded", function(e) {
	zip_code = document.getElementById('findAddress');
	roadAddress = document.getElementById('roadAddress');
	detailAddress = document.getElementById('detailAddress')
	let ifrm_back = document.getElementById('ifrm_back');
	let button = document.getElementById('buy');
	console.log(ifrm_back)
	console.log(button)
	button.addEventListener("click", function(e) {
		console.log(ifrm_back)
		ifrm_back.classList = "";
		console.log(ifrm_back)
	})

	
})


function sample4_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			console.log(data);
			console.log(zip_code)
			console.log(roadAddress)

			zip_code.value = data.zonecode;
			roadAddress.value = data.address;

		}
	}).open();
}



