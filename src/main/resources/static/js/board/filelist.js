window.addEventListener("DOMContentLoaded", function() {
	const fileEl = document.getElementById("file_btn");
	fileEl.addEventListener("click", function() {
		const file = document.getElementById("file_list");
		if(file.classList.contains("action")) {
			file.classList.remove("action");
		} else {
			file.classList.add("action");
		}
	});
});