// Función principal para configurar las áreas de arrastrar y soltar
function setupDropArea(selector) {
    const dropArea = document.querySelector(selector),
      dragText = dropArea.querySelector(".title-drag"),
      button = dropArea.querySelector("button"),
      input = dropArea.querySelector("input");
    let file; // Variable global para almacenar el archivo
  
    button.onclick = () => {
      input.click(); // Si el usuario hace clic en el botón, también se activa el input
    };
  
    input.addEventListener("change", function() {
      // Obtener el archivo seleccionado por el usuario (solo el primero si hay múltiples)
      file = this.files[0];
      dropArea.classList.add("active");
      showFile(); // Llamar a la función para mostrar el archivo
    });
  
    // Si el usuario arrastra un archivo sobre el área
    dropArea.addEventListener("dragover", (event) => {
      event.preventDefault(); // Prevenir el comportamiento predeterminado
      dropArea.classList.add("active");
      dragText.textContent = "Release to Upload File";
    });
  
    // Si el usuario deja de arrastrar un archivo sobre el área
    dropArea.addEventListener("dragleave", () => {
      dropArea.classList.remove("active");
      dragText.textContent = "Drag & Drop to Upload File";
    });
  
    // Si el usuario suelta un archivo sobre el área
    dropArea.addEventListener("drop", (event) => {
      event.preventDefault(); // Prevenir el comportamiento predeterminado
      // Obtener el archivo seleccionado por el usuario (solo el primero si hay múltiples)
      file = event.dataTransfer.files[0];
      showFile(); // Llamar a la función para mostrar el archivo
    });
  
    function showFile() {
      let fileType = file.type; // Obtener el tipo de archivo seleccionado
      let validExtensions = ["image/jpeg", "image/jpg", "image/png"]; // Agregar algunas extensiones de imagen válidas
      if (validExtensions.includes(fileType)) { // Si el archivo seleccionado por el usuario es una imagen
        let fileReader = new FileReader(); // Crear un nuevo objeto FileReader
        fileReader.onload = () => {
          let fileURL = fileReader.result; // Pasar la fuente del archivo del usuario a la variable fileURL
          let imgTag = `<img src="${fileURL}" alt="">`; // Crear una etiqueta img y pasar la fuente del archivo seleccionado por el usuario dentro del atributo src
          dropArea.innerHTML = imgTag; // Agregar esa etiqueta img creada dentro del contenedor dropArea
        };
        fileReader.readAsDataURL(file);
      } else {
        alert("This is not an Image File!");
        dropArea.classList.remove("active");
        dragText.textContent = "Drag & Drop to Upload File";
      }
    }
  }
  
  // Configurar las áreas de arrastrar y soltar para las tres clases
  setupDropArea(".drag-imagen1");
  setupDropArea(".drag-imagen2");
  setupDropArea(".drag-imagen3");
  