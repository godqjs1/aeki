document.addEventListener("DOMContentLoaded", function() {
	
	const signUpBtn = document.getElementById("signUp");
	const signInBtn = document.getElementById("signIn");
	const container = document.querySelector(".signContainer");
	
	signUpBtn.addEventListener("click", () => {
	  container.classList.add("right-panel-active");
	});
	signInBtn.addEventListener("click", () => {
	  container.classList.remove("right-panel-active");
	});
});