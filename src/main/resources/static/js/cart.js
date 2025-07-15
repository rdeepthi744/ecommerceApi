checkAuth();

document.addEventListener("DOMContentLoaded", () => {
  fetchCartItems();
});

function fetchCartItems() {
  const token = localStorage.getItem("token");

  fetch("http://localhost:8080/api/cart", {
    method: "GET",
    headers: { Authorization: "Bearer " + token }
  })
    .then(res => res.json())
    .then(cartItems => {
      const container = document.getElementById("cart-items");
      container.innerHTML = "";

      if (cartItems.length === 0) {
        container.innerHTML = "<p>Your cart is empty.</p>";
        return;
      }

      cartItems.forEach(item => {
        const div = document.createElement("div");
        div.className = "cart-item";
        div.innerHTML = `
          <h4>${item.productName}</h4>
          <p>Price: ₹${item.price}</p>
          <p>Quantity: 
            <input type="number" value="${item.quantity}" min="1" id="qty-${item.cartItemId}">
            <button onclick="updateQuantity(${item.cartItemId})">Update</button>
          </p>
          <button onclick="removeItem(${item.cartItemId})">Remove</button>
          <hr>
        `;
        container.appendChild(div);
      });
    });
}

function updateQuantity(cartItemId) {
  const newQty = document.getElementById(`qty-${cartItemId}`).value;
  fetch(`http://localhost:8080/api/cart/update/${cartItemId}?quantity=${newQty}`, {
    method: "PUT",
    headers: { Authorization: "Bearer " + localStorage.getItem("token") }
  }).then(() => fetchCartItems());
}

function removeItem(cartItemId) {
  fetch(`http://localhost:8080/api/cart/remove/${cartItemId}`, {
    method: "DELETE",
    headers: { Authorization: "Bearer " + localStorage.getItem("token") }
  }).then(() => fetchCartItems());
}

function placeOrder() {
  fetch("http://localhost:8080/api/orders/place", {
    method: "POST",
    headers: { Authorization: "Bearer " + localStorage.getItem("token") }
  })
    .then(res => res.text())
    .then(msg => {
      alert(msg);
      fetchCartItems();
    });
}
