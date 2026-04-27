# 🚀 AppDrop – Instant App Landing Page Generator

An end-to-end backend system that allows developers to **generate, preview, and publish professional landing pages** for their Android applications before Play Store approval.

---

## 🌟 Live Demo

👉 [Live Application](YOUR_DEPLOYED_LINK_HERE)

---

## 🧠 What It Does

AppLaunch enables developers to:

* Create an app profile (name, description, APK link)
* Upload app assets (icon, screenshots)
* Generate a modern landing page instantly
* Preview and publish shareable app pages

---

## ⚙️ Key Features

### 📦 App Landing Page Generation

* Dynamic HTML rendering using Thymeleaf
* Clean, responsive UI templates
* Mobile-friendly and SEO-ready pages

---

### 🖼️ Image Management (Cloudinary)

* Upload app icon and screenshots
* One-to-many image mapping per app
* Secure delete & update using `public_id`
* Optimized image delivery via CDN

---

### ⚡ Preview & Publish System

* Instant preview of generated pages
* Public shareable URLs
* No frontend required — backend-driven rendering

---

### 🔐 Authentication & Authorization

* Secure endpoints using Spring Security
* User-based project isolation
* Protected CRUD operations

---

### 🧱 Scalable Architecture

* Separation of concerns (Controller → Service → Repository)
* External storage integration (Cloudinary)
* Stateless backend design

---

## 🏗️ Architecture

User Input (App Info + Images)
↓
Spring Boot Backend
↓
Cloudinary (Image Storage)
↓
Database (Metadata Storage)
↓
Thymeleaf Rendering Engine
↓
Generated Landing Page (Preview / Publish)

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Security

---

### Storage

* Cloudinary (Image Hosting & CDN)

---

### Database

* PostgreSQL (or any relational DB)

---

### DevOps

* Docker
* Render (Deployment)

---

### Templating

* Thymeleaf

---

## ⚡ API Endpoints

### Create App

```
POST /projects
```

---

### Upload Icon

```
POST /projects/{id}/icon
```

---

### Upload Screenshots

```
POST /projects/{id}/screenshots
```

---

### Preview App

```
GET /preview/{id}
```

---

### Public App Page

```
GET /app/{slug}
```

---

## 🧪 Run Locally

---

### 🥇 1. Clone Repository

```
git clone https://github.com/YOUR_USERNAME/AppLaunch.git
cd applaunch
```

---

### 🥈 2. Configure Environment Variables

```
# Cloudinary
cloudinary.cloud-name=<your-cloud-name>
cloudinary.api-key=<your-api-key>
cloudinary.api-secret=<your-api-secret>

# Database
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/applaunch
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=yourpassword

# JWT
jwt-security-key=<256-bit-secret>
```

---

### 🥉 3. Run Application

```
./mvnw spring-boot:run
```

---

## 🐳 Run with Docker

### Build Image

```
docker build -t applaunch .
```

---

### Run Container

```
docker run -p 8080:8080 \
-e cloudinary.cloud-name=<name> \
-e cloudinary.api-key=<key> \
-e cloudinary.api-secret=<secret> \
-e SPRING_DATASOURCE_URL=<db-url> \
-e SPRING_DATASOURCE_USERNAME=<db-user> \
-e SPRING_DATASOURCE_PASSWORD=<db-pass> \
-e jwt-security-key=<jwt-key> \
applaunch
```

---

## 🔄 CI/CD

* Dockerized backend
* Deployable on Render
* Ready for GitHub Actions integration

---

## 🧱 System Design Highlights

* Cloud-based image storage with CDN delivery
* Dynamic template rendering system
* Scalable multi-project architecture
* Clean RESTful API design
* Stateless service layer

---

## 📌 Future Improvements

* Multiple landing page templates
* Custom domain support
* Analytics (views & downloads)
* Drag & drop image uploader
* Template marketplace system

---

## 👨‍💻 Author

**Salim Saudagar**

* 📧 [salimsaudagar84@gmail.com](mailto:salimsaudagar84@gmail.com)
* 🔗 https://linkedin.com/in/salim-saudagar
* 📍 Maharashtra, India

---

⭐ If you found this useful, consider starring the repo!
