window.addEventListener("DOMContentLoaded", function() {
	const btnEl = document.getElementsByClassName("update");
	for(btn of btnEl) {
		btn.addEventListener("click", function(e) {
		const text = e.target.parentElement.children[0].innerHTML;
		const id = btn.dataset.id;
		
		const tplEl = document.getElementById("commentTpl");
		const tpl = tplEl.innerHTML;
		const domParser = new DOMParser();
		let html = tpl;
		html = html.replace(/<%=id%>/g, id).replace(/<%=text%>/g, text);
		const dom = domParser.parseFromString(html, "text/html");
		const formEl = dom.querySelector("form");
		
		const parent = e.target.parentElement;
		const spanEl = parent.children[0];
		const delEl = parent.children[2];
		parent.removeChild(spanEl);
		parent.removeChild(e.target);
		parent.removeChild(delEl);
		
		parent.appendChild(formEl);
		
	});
	}
	
	
	/** 답글 등록 */
	const replyBtn = document.getElementsByClassName("reply");
	for(reply of replyBtn) {
		reply.addEventListener("click", function(e) {
		const id = reply.dataset.id;
		
		const tplEl = document.getElementById("replyTpl");
		const tpl = tplEl.innerHTML;
		const domParser = new DOMParser();
		let html = tpl;
		html = html.replace(/<%=id%>/g, id);
		const dom = domParser.parseFromString(html, "text/html");
		const formEl = dom.querySelector("form");
		
		const parent = e.target.parentElement;
		parent.removeChild(e.target);
		
		parent.appendChild(formEl);
	});
	}
	
});