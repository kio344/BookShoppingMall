const fileUpload = {
	/**
	* 파일 업로드 처리
	 */
	process(files) {
		try {
			if (!files || files.length == 0) {
				throw new Error("파일을 업로드 하세요.");
			}

			const formData = new FormData();
			for (const file of files) {
				formData.append("file", file);
			}

			

			//그룹 ID
			const gidEl = document.querySelector("input[name='gid']");
			if (gidEl && gidEl.value.trim() != "") {
				formData.append("gid", gidEl.value);
			}

			const xhr = new XMLHttpRequest();
			xhr.open("POST", "../../file/upload?gid=" + gidEl.value);
			xhr.addEventListener("readystatechange", function() {
				if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
					const data = JSON.parse(xhr.responseText);

					if (!data.success && data.message) {// 파일 업로드 실패
						alert(data.message);
						return;
					}

					//파일 업로드 템플릿 처리
					fileUpload.appendFiles(data);

					// 파일 업로드 콜백 처리
					if (typeof parent.fileUploadCallback == 'function') {
						parent.fileUploadCallback(data);
					}

					// 파일 태그 초기화
					const fileEl = document.getElementById("file");
					if (fileEl) fileEl.value = "";
				}
			});

			xhr.send(formData);

		} catch (err) {
			alert(err.message);
		}

	},
	/** 업로드 완료 파일 처리 */
	appendFiles(files) {
		const uploadedFilesEl = document.getElementById("uploaded_files");
		if (!uploadedFilesEl || !files || files.length == 0) {
			return;
		}

		const tplEl = document.getElementById("fileListTpl");
		if (!tplEl) return;

		const tpl = tplEl.innerHTML;
		const domParser = new DOMParser();
		for (const file of files) {
			let html = tpl;
			html = html.replace(/<%=id%>/g, file.id).replace(/<%=fileName%>/g, file.fileName);

			const dom = domParser.parseFromString(html, "text/html");
			const liEl = dom.querySelector("li");
			uploadedFilesEl.appendChild(liEl);
			const textEl = document.getElementById("text_remove");
			if(textEl) {
				textEl.parentElement.removeChild(textEl);
			}

			const removeEl = liEl.querySelector(".remove");
			if (removeEl) {
				removeEl.addEventListener("click", fileUpload.delete);
			}
		}

	},
	/** 파일 삭제 
	* @param e
	*/
	delete(e) {
		if (!confirm('정말 삭제하시겠습니까?')) {
			return;
		}
		const target = e.currentTarget;
		const id = target.dataset.id;
		let url = `../file/delete/${id}`;
		if (location.href.indexOf("board") != -1) {
			url = "../" + url;
		}
		const xhr = new XMLHttpRequest();
		xhr.open("GET", url);

		xhr.addEventListener("readystatechange", function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				const data = JSON.parse(xhr.responseText);

				try {
					if (!data.isSuccess && data.message) {
						throw new Error(data.message);
					}

					const parentEl = target.parentElement;
					parentEl.parentElement.removeChild(parentEl);

				} catch (err) {
					alert(err.message);
				}

			}
		})

		xhr.send(null);
	}
};





window.addEventListener("DOMContentLoaded", function() {
	/** 드래그 앤 드롭 이벤트 처리 S */
	const dropBoxEl = document.getElementById("drop_box");
	if (dropBoxEl) {
		dropBoxEl.addEventListener("dragover", function(e) {
			e.preventDefault();
		});

		dropBoxEl.addEventListener("drop", function(e) {
			e.preventDefault();
			const files = e.dataTransfer.files;
			if (files && files.length > 0) {// 업로드 된 파일이 있는 경우
				fileUpload.process(files);
			}
		});
	}
	/** 드래그 앤 드롭 이벤트 처리 E */

	/** 파일 태그에서 선택 이벤트 처리 S */
	const fileEl = document.getElementById("file");
	if (fileEl) {
		fileEl.addEventListener("change", function(e) {
			const files = e.target.files;
			if (files && files.length > 0) {
				fileUpload.process(files);
			}
		});
	}
	/** 파일 태그에서 선택 이벤트 처리 E */
	
	/** 파일 삭제 처리 S */
	const removeEls = document.querySelectorAll(".file_list .remove");
	for(const el of removeEls){
		el.addEventListener("click", fileUpload.delete);
			
	}
	/** 파일 삭제 처리 E */

});