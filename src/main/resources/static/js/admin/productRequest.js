window.addEventListener("DOMContentLoaded", function(){
	const buttonEl = document.getElementById("product");

	buttonEl.addEventListener("click", function(){
		let okay = confirm("정말로 처리하시겠습니까?");
		if(okay == true){
			alert("처리가 완료되었습니다.");
			
			const frm = document.getElementById("frm");
			frm.submit();
			location.reload();
		}else{
			alert("처리가 취소되었습니다.");
		}
	});	
});
