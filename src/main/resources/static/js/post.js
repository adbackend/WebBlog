let postObject = {
	
	init : function(){
		let _this = this;
		
		// 글 등록
		$("#btn-insert").on("click", ()=>{
			_this.insertPost();
		});
		
		// 글 수정
		$("#btn-update").on("click", ()=>{
			_this.updatePost();
		});
		
		// 글 삭제
		$("#btn-delete").on("click", ()=>{
			_this.deletePost();
		});
		
	},
	
	// 글 등록
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
			
			let status = response["status"];
			
			if(status == 200){
				let message = response["data"];
				alert(message);
				location.href = "/";
			}else{
				
				let warn = "";
				let errors = reponse["data"];
				if(errors.username != null) warn = warn + errors.username + "\n";
				if(errors.password != null) warn = warn + errors.password + "\n";
				if(errors.email != null) warn = warn + errors.email + "\n";
				alert(warn);
			}
			
		}).fail(function(error){
			
			let message = error["data"];
			alert("문제 발생 : " + message);
		
		}); //ajax end
	},
	
	// 글 수정
	updatePost : function(){
		alert("포스트 수정 요청됨");
		
		let post = {
			id : document.getElementById("id").value,
			title : document.getElementById("title").value,
			content : document.getElementById("content").value
		}
		
		$.ajax({
			type : "PUT",
			url : "/post",
			data : JSON.stringify(post),
			contentType : "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			
			location = "/";
		}).fail(function(error){
			let message = error["data"];
			alert("문제 발생 : " + message);		
		})
	},
	
	// 글 삭제
	deletePost : function(){
		alert("포스트 삭제 요청");
		
		let id = document.getElementById("id").childNodes[0].innerHTML;
		
		$.ajax({
			type : "DELETE",
			url : "/post/" + id,
			contentType : "application/json; charset=utf-8"
		}).done(function(response){
		
			let message = response["data"];
			
			alert(message);
			location = "/";
			
		}).fail(function(error){
			let message = error["data"];
			alert("문제 발생 : " + message);
		})
	}
	
	
	
}

postObject.init();