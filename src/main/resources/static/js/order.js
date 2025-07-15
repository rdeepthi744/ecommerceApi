checkAuth();

document.addEventListener("DOMContentLoaded", () => {
  fetchOrders();
});

function fetchOrders() {
  const token = localStorage.getItem("token");

  fetch("http://localhost:8080/api/orders/my", {
    headers: { Authorization: "Bearer " + token }
  })
    .then(res => res.json())
    .then(orders => {
      const container = document.getElementById("order-list");
      container.innerHTML = "";

      if (orders.length === 0) {
        container.innerHTML = "<p>You haven't placed any orders yet.</p>";
        return;
      }

      orders.forEach(order => {
        const div = document.createElement("div");
        let items = "";
        order.orderItems.forEach(item => {
          items += `<li>${item.productName} x ${item.quantity} - ₹${item.price}</li>`;
        });

        div.innerHTML = `
          <h4>Order ID: ${order.id}</h4>
          <p>Date: ${new Date(order.orderTime).toLocaleString()}</p>
          <ul>${items}</ul>
          <p><strong>Total: ₹${order.totalAmount}</strong></p>
          <hr>
        `;
        container.appendChild(div);
      });
    });
}
