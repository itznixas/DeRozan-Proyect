document.addEventListener('DOMContentLoaded', () => {
    console.log('JavaScript cargado correctamente');
    const addToCartButtons = document.querySelectorAll('.add-to-cart');
    const cartItemCount = document.querySelector('.cart-icon span');
    const cartItemList = document.querySelector('.cart-tems');
    const cartTotal = document.querySelector('.cart-total');
    const cartIcon = document.querySelector('.open-carrito'); // Asegúrate de que el selector sea correcto
    const sidebar = document.getElementById('sidebar'); // Selecciona el sidebar por id

    let cartItems = [];
    let totalamount = 0;

    addToCartButtons.forEach((button, index) => {
        button.addEventListener('click', () => {
            const itemNameElement = document.querySelectorAll('.card-title')[index];
            const itemPriceElement = document.querySelectorAll('.price-in-card')[index];

            if (itemNameElement && itemPriceElement) {
                const item = {
                    name: itemNameElement.textContent,
                    price: parseFloat(itemPriceElement.textContent.slice(1)),
                    quantity: 1,
                };

                const existingItem = cartItems.find(cartItem => cartItem.name === item.name);

                if (existingItem) {
                    existingItem.quantity++;
                } else {
                    cartItems.push(item);
                }

                totalamount += item.price;

                updateCartUI();
            } else {
                console.error('Elemento no encontrado:', itemNameElement, itemPriceElement);
            }
        });

        function updateCartUI() {
            updateCartItemCount(cartItems.length);
            updateCartItemList();
            updateCartTotal();
        }

        function updateCartItemCount(count) {
            cartItemCount.textContent = count;
        }

        function updateCartItemList() {
            cartItemList.innerHTML = '';
            cartItems.forEach((item, index) => {
                const cartItem = document.createElement('div');
                cartItem.classList.add('cart-item', 'individual-cart-item');
                cartItem.innerHTML = `
                    <span>${item.quantity} x ${item.name}</span>
                    <span class="cart-item-price">${(item.price * item.quantity).toFixed(2)}
                        <button class="remove-btn" data-index="${index}"><i class="fa-solid fa-times"></i></button>
                    </span>
                `;
                cartItemList.append(cartItem);
            });

            const removeButtons = document.querySelectorAll('.remove-btn');
            removeButtons.forEach((button) => {
                button.addEventListener('click', (event) => {
                    const index = event.target.dataset.index;
                    removeItemFromCart(index);
                });
            });
        }

        function removeItemFromCart(index) {
            const removedItem = cartItems.splice(index, 1)[0];
            totalamount -= removedItem.price * removedItem.quantity;
            updateCartUI();
        }

        function updateCartTotal() {
            cartTotal.textContent = `$${totalamount.toFixed(2)}`;
        }

        cartIcon.addEventListener('click', () => {
            sidebar.classList.toggle('open');
        });

        const closeButton = document.querySelector('.sidebar-close');
        closeButton.addEventListener('click', () => {
            sidebar.classList.remove('open');
        });
    });
});


