window.addEventListener("DOMContentLoaded", function(){
	const buttonEl = document.getElementById("request");
		
	buttonEl.addEventListener("click", function(){
		console.log("11111");
		let okay = confirm("정말로 처리하시겠습니까?");
		if(okay == true){
			alert("처리가 완료되었습니다.");
				
			const frm = document.getElementById("frm");
			const frm2 = document.getElementById("frm2")
			frm.submit();
			frm2.submit();
			
		}else{
			alert("처리가 취소되었습니다.");
		}
	});
});
