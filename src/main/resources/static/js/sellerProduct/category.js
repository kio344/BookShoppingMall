window.addEventListener("DOMContentLoaded", function() {
	const genreEl = document.getElementById("genre");
	const categoryBtn = document.getElementById("categoryBtn");
	const countryEl = document.getElementById("country");
	const subcategoryEl = document.getElementsByClassName('subcategory');
	
	let show = '';
	let selectInner = '';
	let countryInner = '';
	let subInner = '';
	
	for (let i = 0; i < subcategoryEl.length; i++) {
		subcategoryEl[i].addEventListener("change", function(e) {
			let selectIndex = e.target.selectedIndex;
			subInner = e.target[selectIndex].innerText;
		});
	}
	
	countryEl.addEventListener("change", function(e){
		let country = e.target.selectedIndex;
		countryInner = e.target[country].innerText;
		
	});
	

	genreEl.addEventListener("change", function(e) {
		let selected = e.target.value;
		let selectIndex = e.target.selectedIndex;
		selectInner = e.target[selectIndex].innerText;
		
		
		if (show != '') {
			let showEl = document.getElementById(show);
			showEl.className = "dn";
		}

		let selectEl = document.getElementById(selected);

		selectEl.className = "";
		show = selected;

		categoryBtn.addEventListener("click", function() {
			const categoryInput = document.getElementById("categoryInput");
			categoryInput.value = countryInner + "/" + selectInner + "/" + selectEl.value;

		});
	});


});