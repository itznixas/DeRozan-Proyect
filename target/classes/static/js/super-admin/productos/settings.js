const editBtnSA = document.querySelector('#editSAbtn'),
    settings = document.querySelector('.settings'),
    editCloseBtnSA = document.querySelector('#editCloseSA');

    editBtnSA.addEventListener("click", () => settings.classList.add("show"));
    editCloseBtnSA.addEventListener("click", () => settings.classList.remove("show"));