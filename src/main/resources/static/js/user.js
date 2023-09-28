// userObject 객체 선언
let userObject = {
	
	// init() 함수 선언
	init:function() {
		let _this = this;
		
		$("#btn-save").on("click", () => {
			_this.insertUsert();
		});
	},
	
	insertUsert:function(){
		
		let user = {
			username : document.getElementById("username").value,
			password : document.getElementById("password").value,
			email : document.getElementById("email").value
		}
		
		$.ajax({
			type : "POST",
			url : "/auth/insertUser",
			data : JSON.stringify(user), // user 객체 -> JSON 형식
			contentType : "application/json; charset=UTF-8" // 서버로 보낼때
		}).done(function(response){

			alert(response.data);
			location.href = "/";
			
		}).fail(function(error){
		
			alert("오류 발생 : " + error);
			
		});		
	},
}

// userObject 객체의 init() 함수 호출
userObject.init();