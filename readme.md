# 🍷 Bodega Backend

Backend REST para la gestión de licores, desarrollado con Java, Spring Boot, Spring Data JPA, Hibernate y MySQL.

El proyecto implementa un CRUD completo de licores y expone una API REST que puede ser consumida por un frontend u otras aplicaciones.

## 🛠️ Tecnologías

* ☕ Java
* 🌱 Spring Boot
* 🗄️ Spring Data JPA / Hibernate
* 🐬 MySQL
* 📦 Maven
* 🧪 Postman
* 💻 Apache NetBeans (opcional)

## 📋 Requisitos

Para ejecutar el proyecto necesitás tener instalado:

* Java JDK
* MySQL Server
* Maven (si no utilizás el Maven integrado del IDE)
* Un IDE compatible con Java, como Apache NetBeans, IntelliJ IDEA o Eclipse.

Además, necesitás iniciar el servicio de MySQL antes de ejecutar el backend.

## 📥 Instalación

### 1. Clonar el repositorio
Cloná el repositorio y entrá en la carpeta del proyecto:

```bash
git clone URL_DEL_REPOSITORIO
cd bodega-backend
```

### 2. Crear la base de datos
El proyecto incluye el script SQL necesario para crear y cargar la base de datos:

```text
database/
└── bodega_licores.sql
```

El script:
* 🗄️ Crea la base de datos `bodega_licores`.
* 📋 Crea la tabla `licores`.
* 📦 Carga los datos iniciales.
* 🖼️ Asocia cada registro con el nombre de su imagen correspondiente.

#### Ejecutar el script
Podés utilizar MySQL Workbench:
1. Abrir MySQL Workbench.
2. Conectarse al servidor MySQL local.
3. Abrir: `database/bodega_licores.sql`
4. Ejecutar el script con el botón ⚡ **Execute**.

Al finalizar debería existir la base: `bodega_licores`

> 💡 El script crea la base de datos en la instalación local de MySQL de cada usuario. El repositorio no contiene una base de datos real ni datos de conexión.

---

## ⚙️ Configuración de MySQL

Las credenciales de MySQL se mantienen fuera del repositorio mediante `config.properties`. Este archivo está incluido en `.gitignore` y no debe subirse al repositorio.

### Crear `config.properties`
En la raíz del proyecto creá un archivo llamado `config.properties` con tus propios datos de MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bodega_licores
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
```

> 🔒 Cada usuario debe utilizar sus propias credenciales de MySQL. El proyecto incluye `config.properties.example` como referencia para crear el archivo de configuración local.

---

## 🖼️ Imágenes

Las imágenes utilizadas por los licores se encuentran dentro del backend:

```text
src/
└── main/
    └── resources/
        └── static/
            └── images/
```

La base de datos almacena únicamente el nombre del archivo, por ejemplo: `corona.png`.

Spring Boot sirve automáticamente estas imágenes mediante:
`http://localhost:8080/images/corona.png`

Si se crea un licor sin especificar una imagen, el backend utiliza: `noimage.jpg`

---

## 🚀 Ejecutar el backend

Una vez configurada la base de datos:

### Desde NetBeans
1. Abrir el proyecto.
2. Esperar a que Maven descargue las dependencias.
3. Abrir: `src/main/java/prog2/bodega_backend/BodegaBackendApplication.java`
4. Ejecutar la clase principal.

### Desde Maven
También podés ejecutar:

```bash
mvn spring-boot:run
```

El servidor estará disponible en: `http://localhost:8080`

---

## 🖥️ Bodega Frontend

El proyecto cuenta con un frontend desarrollado con **Java, Spring Boot y Vaadin**, encargado de proporcionar la interfaz gráfica para gestionar los licores.

### Tecnologías

- ☕ Java 21
- 🌱 Spring Boot 4.1.1
- 🎨 Vaadin 25.3.0
- 📦 Maven
- 🔗 RestClient para la comunicación con el backend

### Funcionalidades actuales

- 📋 Listado de licores
- 🔎 Filtrado por tipo
- ➕ Alta de licores
- ✏️ Edición de licores
- 🗑️ Eliminación con confirmación
- 🏷️ Incorporación de nuevos tipos de licor
- 🖼️ Visualización de las imágenes asociadas

El frontend se comunica con la API REST del backend mediante HTTP y no accede directamente a la base de datos.

### Puertos

- Backend: `http://localhost:8080`
- Frontend: `http://localhost:8081`


---

## 🔗 API REST

La API utiliza una estructura REST basada en el recurso `licores`.

### 📋 Obtener todos los licores
* **GET** `/licores`
* **Ejemplo:** `http://localhost:8080/licores`
* **Descripción:** Devuelve todos los registros.

### 🔎 Filtrar por tipo
* **GET** `/licores?tipo=ron`
* **Ejemplo:** `http://localhost:8080/licores?tipo=ron`
* **Descripción:** Devuelve los licores cuyo tipo sea ron.

### 🔍 Obtener un licor por ID
* **GET** `/licores/{id}`
* **Ejemplo:** `http://localhost:8080/licores/5`
* **Descripción:** Devuelve el licor correspondiente al ID indicado.

### ➕ Crear un licor
* **POST** `/licores`
* **Ejemplo de JSON:**
```json
{
    "tipo": "ron",
    "marca": "Havana Club",
    "foto": "havana.png"
}
```
* **Respuesta exitosa:** `201 Created`

> **Imagen opcional:** Si no se especifica foto:
> ```json
> {
>     "tipo": "ron",
>     "marca": "Havana Club"
> }
> ```
> El backend asigna automáticamente: `noimage.jpg`

### ✏️ Actualizar un licor
* **PUT** `/licores/{id}`
* **Ejemplo:** `http://localhost:8080/licores/5`
* Se pueden modificar solamente los campos necesarios. Por ejemplo:
```json
{
    "marca": "Nueva Marca"
}
```
* Los campos que no se envían o se dejan vacíos conservan sus valores anteriores. También es posible enviar `{}` (en ese caso, el registro permanece sin modificaciones y se devuelve el registro actual).
* **Respuesta exitosa:** `200 OK`

### 🗑️ Eliminar un licor
* **DELETE** `/licores/{id}`
* **Ejemplo:** `http://localhost:8080/licores/5`
* **Respuesta exitosa:** `204 No Content`

---

## 📡 Códigos HTTP

| Código | Significado |
| :--- | :--- |
| **200 OK** | Operación realizada correctamente |
| **201 Created** | Recurso creado correctamente |
| **204 No Content** | Recurso eliminado correctamente |
| **400 Bad Request** | Datos enviados incorrectamente |
| **404 Not Found** | No se encontró el licor solicitado |

---

## ⚠️ Validaciones

* **Al crear un licor:**
  * `tipo` es obligatorio.
  * `marca` es obligatoria.
  * `foto` es opcional.
  * Los espacios innecesarios al principio y al final se eliminan.
  * Si no se proporciona una imagen, se utiliza `noimage.jpg`.

* **Al actualizar:**
  * Se pueden modificar campos individualmente.
  * Los campos vacíos no reemplazan los valores existentes.
  * Si se envía `{}`, el registro permanece igual.
  * Si el ID no existe, se devuelve `404 Not Found`.

---

## 🧪 Probar la API

Podés utilizar Postman para probar los endpoints. Ejemplos de peticiones:

```http
GET     http://localhost:8080/licores
GET     http://localhost:8080/licores?tipo=whisky
GET     http://localhost:8080/licores/1

POST    http://localhost:8080/licores
PUT     http://localhost:8080/licores/1
DELETE  http://localhost:8080/licores/1
```

---

## 📁 Estructura del proyecto

```text
bodega-backend/
├── database/
│   └── bodega_licores.sql
│
├── src/
│   └── main/
│       ├── java/
│       │   └── prog2/
│       │       └── bodega_backend/
│       │           ├── controller/
│       │           ├── exceptions/
│       │           ├── model/
│       │           ├── repository/
│       │           ├── service/
│       │           └── BodegaBackendApplication.java
│       │
│       └── resources/
│           └── static/
│               └── images/
│
├── config.properties.example
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🔐 Seguridad

Las credenciales de la base de datos se almacenan localmente en `config.properties`. Este archivo está excluido mediante `.gitignore`. **No subir credenciales reales al repositorio.**

## 👤 Autor

Proyecto académico desarrollado como aplicación CRUD para la gestión de licores.