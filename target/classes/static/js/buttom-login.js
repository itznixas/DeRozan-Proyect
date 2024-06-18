let slider_button = document.querySelector(".slider_button");
let signup_button = document.querySelector(".signup_button");
let login_button = document.querySelector(".login_button");
let form_slider = document.querySelector(".form_slider");

signup_button.style.color = "#fff";

login_button.onclick = function () {
    slider_button.style.left = "50%";
    login_button.style.color = "#fff";
    signup_button.style.color = "#000";
    form_slider.style.left = "-100%";
}
signup_button.onclick = function () {
    slider_button.style.left = "0%";
    signup_button.style.color = "#fff";
    login_button.style.color = "#000";
    form_slider.style.left = "0%";
}