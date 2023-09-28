let postObject = {
	
	init : function(){
		let _this = this;
		
		$("#btn-insert").on("click", ()=>{
			_this.insertPost();
		});
	},
	
	insertPost : function(){
		alert("포스트 등록 요청됨");
		
		let post = {
			title : document.getElementById("title").value,
			content : document.getElementById("content").value
		}
		
		$.ajax({
			type : "POST",
			url : "/post",
			data : JSON.stringify(post),
			contentType : "application/json; charset=utf-8"
		}).done(function(response){
			
			let message = response["data"];
			alert(message);
			location.href = "/";
			
		}).fail(function(error){
			
			let message = error["data"];
			alert("문제 발생 : " + message);
		
		}); //ajax end
	},
	
	
	
}

postObject.init();