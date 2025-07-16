
---

## 📌 Project Overview

This project is a **backend system** for an E-Commerce application. It offers secure authentication, role-based operations (Admin/Customer), product management, shopping cart features, and order processing.

---

## 🛠️ Tech Stack

| Layer      | Technology                             |
|------------|-----------------------------------------|
| Language   | Java 17+                                |
| Framework  | Spring Boot, Spring Security            |
| Auth       | JWT (JSON Web Token)                    |
| Database   | MySQL                                   |
| Frontend   | HTML, CSS, JavaScript (Optional)        |
| Build Tool | Maven                                   |
| Hosting    | Render(still under progresss)                       |

---

## 👤 Roles

- **Admin**
  - Add/Update/Delete products
  - View all orders
- **Customer**
  - Register/Login
  - View products (with pagination)
  - Manage cart
  - Place orders
  - View their order history

---

## ✅ Features

### 🔐 Authentication
- Register as new user
- Login with JWT
- Role-based authorization

### 🛒 Cart
- Add product to cart
- Update quantity
- Remove product
- View cart contents

### 📦 Order
- Place order from cart
- View user’s orders (Customer)
- View all orders (Admin)

### 🧾 Products
- Add/Edit/Delete product (Admin)
- View product list with pagination
- Filter/Search (optional)

---

## 📁 Folder Structure
ecommerce-api/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
│ └── impl/
├── security/
├── util/
├── resources/
│ ├── static/
│ ├── templates/
│ └── application.properties
└── main class (EcommerceApiApplication.java)


---

## 📜 REST API Endpoints

### 🔐 Auth
| Method | Endpoint                | Description              |
|--------|-------------------------|--------------------------|
| POST   | `/api/auth/register`    | Register a new user      |
| POST   | `/api/auth/login`       | Login with credentials   |

### 📦 Products
| Method | Endpoint                        | Description                      |
|--------|----------------------------------|----------------------------------|
| GET    | `/api/products?page=0&size=10`  | View paginated product list      |
| POST   | `/api/products`                 | Add product (Admin)              |
| PUT    | `/api/products/{id}`            | Update product (Admin)           |
| DELETE | `/api/products/{id}`            | Delete product (Admin)           |

### 🛒 Cart
| Method | Endpoint                       | Description                      |
|--------|-------------------------------|----------------------------------|
| POST   | `/api/cart/add`               | Add item to cart                 |
| GET    | `/api/cart/user`              | Get user's cart items            |
| PUT    | `/api/cart/update/{id}`       | Update quantity                  |
| DELETE | `/api/cart/remove/{id}`       | Remove item from cart            |

### 📦 Orders
| Method | Endpoint                | Description                        |
|--------|-------------------------|------------------------------------|
| POST   | `/api/orders/place`     | Place an order from the cart       |
| GET    | `/api/orders/user`      | View logged-in user's orders       |
| GET    | `/api/orders/all`       | View all orders (Admin only)       |

---

## 📑 Setup Instructions

### ✅ Prerequisites
- Java 17+
- MySQL installed and running
- Maven installed

### 🔧 Configuration

1. Create a MySQL database:
```sql
CREATE DATABASE ecommerce;
2.Update src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret
 Run the Application
Option 1: Local Run

mvn clean install
mvn spring-boot:run
Option 2: JAR Build

mvn clean package
java -jar target/ecommerce-api-0.0.1-SNAPSHOT.jar
Then open:

arduino
Copy
Edit
http://localhost:8080/
🧪 Sample Request Payloads
✅ Register
json
Copy
Edit
POST /api/auth/register
{
  "username": "john123",
  "email": "john@example.com",
  "password": "pass123"
}
✅ Login

POST /api/auth/login
{
  "username": "john123",
  "password": "pass123"
}
🛡️ Security
All endpoints are protected via JWT.

Admins have separate privileges.

Users get a JWT token on login to access protected endpoints.

📌 Pagination Example
To get products page-wise:


🖥️ Frontend (Optional)
Basic HTML pages for:

Register/Login

View products

Manage cart

Place orders

Stored in: src/main/resources/static/


(Render Deployment Steps:
Push project to GitHub


Select “New Web Service”

Connect to your GitHub repository

Set Build Command:)---------YET TO BE DONE


./mvnw clean install
Start Command:


java -jar target/ecommerce-api-0.0.1-SNAPSHOT.jar
Environment:

Java version: 17

Add environment variables: DB credentials, JWT secret

🧑 Author
Deepthi .R
Java Full Stack Developer
📫 GitHub




---

## ✅ How to Add This README to GitHub

### Step-by-step:

1. Open terminal or PowerShell in the  project directory:

```bash
cd F:/ecommerce-api (1)/ecommerce-api
Create and open README file:

bash



Push to GitHub:


git add README.md
git commit -m "Added full documentation in README"
git push origin main

