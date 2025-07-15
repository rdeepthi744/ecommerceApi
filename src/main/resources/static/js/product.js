checkAuth();

document.addEventListener("DOMContentLoaded", () => {
  fetch("http://localhost:8080/api/products", {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token")
    }
  })
    .then(res => res.json())
    .then(products => {
      const container = document.getElementById("product-list");
      products.forEach(product => {
        const div = document.createElement("div");
        div.className = "product";
        div.innerHTML = `
          <h3>${product.name}</h3>
          <p>${product.description}</p>
          <p><strong>₹${product.price}</strong></p>
          <button onclick="addToCart(${product.id})">Add to Cart</button>
        `;
        container.appendChild(div);
      });
    });
});

function addToCart(productId) {
  const token = localStorage.getItem("token");
  fetch("http://localhost:8080/api/cart/add", {
    method: "POST",
    headers: {
      "Authorization": "Bearer " + token,
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ productId, quantity: 1 })
  })
    .then(res => res.text())
    .then(msg => alert(msg));
}
