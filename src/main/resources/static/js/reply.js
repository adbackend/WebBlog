let replyObject = {

	init : function(){
		
		// 댓글 등록
		$("#btn-save-reply").on("click", () => {
			this.insertReply();
		});
	},
	
	// 댓글 작성
	insertReply : function(){
		
		let id = document.getElementById("postId").value;
		let reply = {
			content : document.getElementById("reply-contents").value
		}
		
		$.ajax({
			type : "POST",
			url : "/reply/" + id,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8"		
		}).done(function(response){
			
			let message = response["data"];
			alert(message);
			location = "/post/" + id;
		
		}).fail(function(error){
			
			let message = error["data"];
			alert(message);
			
		});
	},
	
	// 댓글 삭제
	deleteReply : function(postId, replyId){
		
		$.ajax({
			type : "DELETE",
			url : "/reply/" + replyId
		}).done(function(reponse){
			
			let message = reponse["data"];
			alert(message);
			location = "/post/"+ postId;
			
		}).fail(function(error){
		
		});
	},


}

replyObject.init();