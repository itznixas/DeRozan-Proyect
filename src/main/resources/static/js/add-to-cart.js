document.addEventListener('DOMContentLoaded', () => {
    const sidebar = document.getElementById('sidebar');
    const cartItemCountSpans = document.querySelectorAll('.cart-icon span');
    const cartItemList = document.querySelector('.cart-tems');
    const cartTotal = document.querySelector('.cart-total');
    let cartItems = [];
    let totalAmount = 0;
    let totalItems = 0;

    function updateCartUI() {
        updateCartItemCount();
        updateCartItemList();
        updateCartTotal();
    }

    function updateCartItemCount() {
        cartItemCountSpans.forEach(span => {
            span.textContent = totalItems;
        });
    }

    function updateCartItemList() {
        cartItemList.innerHTML = '';
        cartItems.forEach((item, index) => {
            const cartItem = document.createElement('div');
            cartItem.classList.add('cart-item', 'individual-cart-item');
            cartItem.innerHTML = `
                <span>(${item.quantity}x) ${item.name}</span>
                <span class="cart-item-price">${(item.price * item.quantity).toFixed(2)}</span>
                <button class="remove-btn" data-index="${index}"><i class="fa-solid fa-times"></i></button>
            `;
            cartItemList.append(cartItem);
        });

        const removeButtons = document.querySelectorAll('.remove-btn');
        removeButtons.forEach((button) => {
            button.addEventListener('click', (event) => {
                const index = event.target.closest('button').dataset.index;
                removeItemFromCart(index);
            });
        });
    }

    function removeItemFromCart(index) {
        const removedItem = cartItems.splice(index, 1)[0];
        totalAmount -= removedItem.price * removedItem.quantity;
        totalItems -= removedItem.quantity;
        updateCartUI();
    }

    function updateCartTotal() {
        cartTotal.textContent = `$${totalAmount.toFixed(2)}`;
    }

    const addToCartButtons = document.querySelectorAll('.add-to-cart');
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

                totalAmount += item.price;
                totalItems++;
                updateCartUI();
            } else {
                console.error('Elemento no encontrado:', itemNameElement, itemPriceElement);
            }
        });
    });

    // Eventos para abrir y cerrar el sidebar
    const cartIcons = document.querySelectorAll('.open-carrito');
    cartIcons.forEach(cartIcon => {
        cartIcon.addEventListener('click', () => {
            sidebar.classList.toggle('open');
        });
    });

    const closeButton = document.querySelector('.sidebar-close');
    closeButton.addEventListener('click', () => {
        sidebar.classList.remove('open');
    });
});
