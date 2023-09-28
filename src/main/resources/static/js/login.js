let loginObject = {
	
	init : function(){
		$("#btn-login").on("click", ()=>{
			this.login();
		});
	},
	
	login : function(){
		alert("로그인 요청됨");
		
		let data = {
			username : document.getElementById("username").value,
			password : document.getElementById("password").value
		}
		
		console.log(data);
		
		$.ajax({
			type : "POST",
			url : "/auth/login",
			data : JSON.stringify(data), // 객체를 JSON 문자열로 변환
			contentType : "application/json; charset=utf-8" // 서버로 보낼때 데이터 타입
		}).done(function(response){
		
			let message = response["data"];
			alert(message);
			location = "/";
		
		}).fail(function(error){
		
			let message = error["data"];
			alert("문제 발생 : " + message);
		});
	}
	
	
}


loginObject.init();