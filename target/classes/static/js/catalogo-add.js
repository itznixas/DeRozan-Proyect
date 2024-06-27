document.addEventListener("DOMContentLoaded", function() {
    const productsContainer = document.getElementById("products-container");

    function loadProducts() {
        const products = JSON.parse(localStorage.getItem("products")) || [];
        products.forEach(product => {
            createProductCard(product);
        });
    }

    function createProductCard(product) {
        const card = document.createElement("div");
        card.className = "card";

        card.innerHTML = `
            <div class="product-img" style="background-image: url('${product.image}');"></div>
            <div class="product-detail">
                <span class="card-title">${product.name}</span>

                <div class="rating">
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star-half'></i>
                </div>

                <p>${product.description || "Lorem ipsum dolor sit amet consectetur adipisicing elit."}</p>

                <div class="buttons">
                    <div class="price-in-card">$${product.price || 900}
                    </div>
                    <button class="add-to-cart">Add to Cart</button>
                    <button class="favourite"><i class='bx bx-heart'></i></button>
                </div>
            </div>
        `;

        // Agregar la tarjeta al contenedor de productos
        productsContainer.appendChild(card);

        // Agregar evento click a la tarjeta
        card.addEventListener("click", () => {
            showModal(product);
        });

        function showModal(product) {
            // Actualizar el contenido del modal con los detalles del producto
            document.querySelector(".modal-card-title").textContent = product.name;
            document.querySelector(".modal-product-description").textContent = product.description || "No description available.";
            const modalProductImg = document.querySelector(".modal-product-img");
            modalProductImg.style.backgroundImage = `url('${product.image}')`;
        
            // Mostrar el modal
            document.getElementById("product-modal").style.display = "block";
        }
        
        // Cerrar el modal cuando se haga clic en el botón de cerrar
        document.querySelector(".close-button").addEventListener("click", () => {
            document.getElementById("product-modal").style.display = "none";
        });
        
        // Cerrar el modal cuando se haga clic fuera del modal
        window.addEventListener("click", (event) => {
            if (event.target == document.getElementById("product-modal")) {
                document.getElementById("product-modal").style.display = "none";
            }
        });
    }        

    loadProducts();
});
