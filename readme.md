# 🍷 Bodega

Aplicación CRUD para la gestión de licores, desarrollada con **Java, Spring Boot, MySQL y Vaadin**.

El proyecto está dividido en un **backend REST** y un **frontend web**, comunicados mediante HTTP.

## 🛠️ Tecnologías

### Backend

* ☕ Java
* 🌱 Spring Boot
* 🗄️ Spring Data JPA / Hibernate
* 🐬 MySQL
* 📦 Maven

### Frontend

* ☕ Java 21
* 🌱 Spring Boot 4.1.1
* 🎨 Vaadin 25.3.0
* 🔗 RestClient
* 📦 Maven

## 📋 Requisitos

* Java JDK
* MySQL Server
* Maven
* IDE compatible con Java

## 📥 Instalación

### 1. Clonar el repositorio

```bash
git clone URL_DEL_REPOSITORIO
cd bodega
```

### 2. Crear la base de datos

El proyecto incluye:

```text
database/
└── bodega_licores.sql
```

Ejecutá el script desde MySQL Workbench para crear la base de datos y cargar los datos iniciales.

### 3. Configurar MySQL

Crear `config.properties` con las credenciales locales:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bodega_licores
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
```

El archivo está excluido mediante `.gitignore`. No subir credenciales reales.

## 🚀 Ejecución

### Backend

Desde NetBeans o Maven:

```bash
mvn spring-boot:run
```

Disponible en:

```text
http://localhost:8080
```

### Frontend

Ejecutar el proyecto frontend desde NetBeans o Maven.

Disponible en:

```text
http://localhost:8081
```

El frontend consume la API REST y no accede directamente a la base de datos.

## 🖥️ Funcionalidades

* 📋 Listado de licores
* 🔎 Filtrado por tipo
* ➕ Alta de licores
* ✏️ Edición
* 🗑️ Eliminación con confirmación
* 🏷️ Creación de nuevos tipos
* 🖼️ Carga y visualización de imágenes
* 🖼️ Imagen predeterminada cuando no se selecciona una

Las imágenes nuevas se almacenan localmente en:

```text
uploads/images/
```

La base de datos almacena únicamente el nombre del archivo.

## 🔗 API REST

| Método | Endpoint            | Función                   |
| ------ | ------------------- | ------------------------- |
| GET    | `/licores`          | Obtener todos los licores |
| GET    | `/licores?tipo=ron` | Filtrar por tipo          |
| GET    | `/licores/{id}`     | Obtener un licor          |
| POST   | `/licores`          | Crear un licor            |
| PUT    | `/licores/{id}`     | Actualizar un licor       |
| DELETE | `/licores/{id}`     | Eliminar un licor         |
| POST   | `/licores/foto`     | Cargar una imagen         |

### Ejemplo de creación

```json
{
    "tipo": "ron",
    "marca": "Havana Club",
    "foto": "havana.png"
}
```

`tipo` y `marca` son obligatorios. `foto` es opcional.

## 🗂️ Estructura

```text
bodega/
├── bodega-backend/
│   ├── database/
│   ├── src/
│   ├── config.properties.example
│   └── pom.xml
│
├── bodega-frontend/
│   ├── src/
│   └── pom.xml
│
└── README.md
```

## 🔐 Seguridad

Las credenciales de MySQL se mantienen fuera del repositorio mediante `config.properties`, incluido en `.gitignore`.

**No subir credenciales reales al repositorio.**

## 👤 Autor

Proyecto académico desarrollado como aplicación CRUD para la gestión de licores - Programación II INSPT UTN.
