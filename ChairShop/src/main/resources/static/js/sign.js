document.addEventListener("DOMContentLoaded", function() {
	
	const signUpBtn = document.getElementById("signUp");
	const signInBtn = document.getElementById("signIn");
	const container = document.querySelector(".signContainer");
	var signupChk = document.signup.signupChk.value;
	
	/*var signupChk = document.signup ? document.signup.signupChk.value : null;*/
	if (signupChk == "fail") {
	  container.classList.add("right-panel-active");
	};	
	
	signUpBtn.addEventListener("click", () => {
	  container.classList.add("right-panel-active");
	});
	
	signInBtn.addEventListener("click", () => {
	  container.classList.remove("right-panel-active");
	});	 
});

