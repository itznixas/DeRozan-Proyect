var thumb1 =  document.getElementById("thumb1");
var thumb1Src =  document.getElementById("thumb1").src;
var thumb2 =  document.getElementById("thumb2");
var thumb2Src =  document.getElementById("thumb2").src;
var thumb3 =  document.getElementById("thumb3");
var thumb3Src =  document.getElementById("thumb3").src;
var thumb4 =  document.getElementById("thumb4");
var thumb4Src =  document.getElementById("thumb4").src;

thumb1.addEventListener("click",function(){
	document.getElementById("mainImg").src = thumb1Src
})
thumb2.addEventListener("click",function(){
	document.getElementById("mainImg").src = thumb2Src
})
thumb3.addEventListener("click",function(){
	document.getElementById("mainImg").src = thumb3Src
})
thumb4.addEventListener("click",function(){
	document.getElementById("mainImg").src = thumb4Src
})