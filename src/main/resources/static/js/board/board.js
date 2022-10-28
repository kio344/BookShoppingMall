window.addEventListener("DOMContentLoaded", function() {
	CKEDITOR.replace("text_content");
	CKEDITOR.config.height = 350;
	
	const checkEl = document.getElementById("private");
	checkEl.addEventListener("change", function() {
		const passEl = document.getElementById("pass");
		if(checkEl.checked) {
			passEl.classList.add("action");
		} else {
			passEl.classList.remove("action");
		}
	});
	
});