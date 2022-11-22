window.addEventListener("DOMContentLoaded",function(){
	
	let categorys=document.getElementsByClassName("category");
	
	let form=document.getElementById('productNavFrm')
	
	let cat1=document.getElementById('cat1')
	let cat2=document.getElementById('cat2')
	let cat3=document.getElementById('cat3')
	let nowCat3='current_not'
	
	form.submit();
	
	let cate_1=categorys[0].children[0].children;
	for(let i =0;i<cate_1.length;i++){
		cate_1[i].addEventListener("click",function(e){
			cat1.value=e.target.dataset.value

			form.submit();
		})
	}
	
	let cate_2=categorys[1].children[0].children;
	
	for(let i =0;i<cate_2.length;i++){
		cate_2[i].addEventListener("click",function(e){
			
			let getOldCat3El=document.getElementById(nowCat3);
			getOldCat3El.className="dn"
			
			console.log()
			
			let getNowCat3El=document.getElementById(e.target.dataset.value)
			getNowCat3El.className="";
			nowCat3=e.target.dataset.value;
			
			cat2.value=e.target.innerHTML
			cat3.value=""
			form.submit();
		})
	}
	
	let cate_3=categorys[2].children;
	
	console.log(cate_3)
	
	for(let i = 0; i<cate_3.length;i++){
		let cat_3_ul=cate_3[i]
		
		let cat_3_ul_li=cat_3_ul.children
		
		for(let l=0;l<cat_3_ul_li.length;l++){
			
			cat_3_ul_li[l].addEventListener("click",function(e){
			cat3.value=e.target.innerHTML
			form.submit();
		})
		}
		
		
	}
	
	
})