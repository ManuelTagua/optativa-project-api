# ProyectoOptativa – API REST con Spring Boot

## Descripción general
ProyectoOptativa es una aplicación desarrollada con Spring Boot que implementa un sistema completo de gestión de desarrolladores, proyectos y tecnologías.

El backend sigue una arquitectura Model–DTO–Mapper–Controller–Service–Repository, garantizando separación de responsabilidades, escalabilidad y facilidad de mantenimiento. También incluye un frontend sencillo en HTML y JavaScript para consumir la API.

---

## Arquitectura del proyecto

El proyecto sigue el patrón Controlador → Servicio → Repositorio, recomendado para aplicaciones empresariales modernas.

```
src/
 └── main/
      ├── java/com/example/ProyectoOptativa
      │     ├── controller/        Controladores REST (API)
      │     ├── service/           Lógica de negocio
      │     ├── model/
      │     │     ├── entity/      Entidades JPA
      │     │     ├── dto/         Objetos de transferencia
      │     │     ├── mapper/      Conversión Entity ↔ DTO
      │     ├── repository/        Repositorios JPA
      │     ├── config/            Configuración CORS
      │     └── ProyectoOptativaApplication.java
      └── resources/
            ├── application.properties
            └── static / frontend   HTML, JS y CSS
```

---

## Entidades principales

### Developer
- id  
- name  
- position  
- yearsExperience  
- technologies (ManyToMany)

### Project
- id  
- name  
- description  
- startDate  
- endDate  
- status (ManyToOne)
- developers (ManyToMany)
- technologies (ManyToMany)

### Technology
- id  
- name  
- version

---

## Relaciones
- Developer ⇄ Project: ManyToMany  
- Project ⇄ Technology: ManyToMany  
- Project → Status: ManyToOne

Todos gestionados mediante JPA/Hibernate.

---

## Endpoints principales

### Developers
```
GET    /api/v1/developers
GET    /api/v1/developers/{palabra}
POST   /api/v1/developers
PUT    /api/v1/developers/{id}
DELETE /api/v1/developers/{id}
```

### Projects
```
GET    /api/v1/projects
POST   /api/v1/projects
DELETE /api/v1/projects/{id}
```

### Technologies
```
GET    /api/v1/technologies
POST   /api/v1/technologies
DELETE /api/v1/technologies/{id}
```

Todos los endpoints reciben y devuelven DTOs, y cada respuesta se envía mediante ResponseEntity con los códigos HTTP adecuados.

---

## Frontend
En la carpeta `frontend/` se incluyen varias páginas HTML:

- index.html  
- developers.html  
- projects.html  
- technologies.html  

Cada una utiliza JavaScript para consultar la API mediante la función fetch.

---

## Configuración y ejecución

### 1. Clonar el repositorio
```
git clone <url>
cd ProyectoOptativa
```

### 2. Instalar dependencias
```
mvn clean install
```

### 3. Ejecutar el servidor
```
mvn spring-boot:run
```

El servidor se iniciará en:

http://localhost:8080

---

## Configuración CORS
El proyecto incluye una configuración CORS personalizada en:

`src/main/java/.../config/CorsConfig.java`

Permite el acceso desde el frontend local y navegadores.

---

## Tecnologías utilizadas
- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Hibernate  
- Maven  
- MySQL / H2  
- HTML y JavaScript  
- Git

---

## Requisitos del proyecto (evaluación)
| Requisito | Estado |
|----------|--------|
| Estructura MVC/Service/Repository | Cumplido |
| Entidades bien modeladas | Cumplido |
| Repositorios con métodos necesarios | Cumplido |
| Lógica en servicios | Cumplido |
| Servidor activo durante ejecución | Cumplido |
| Uso de ResponseEntity | Cumplido |
| Uso de Git | Cumplido |
| Archivo README | Cumplido |

---

## Conclusión
ProyectoOptativa es una API REST bien estructurada, desarrollada siguiendo buenas prácticas profesionales. Utiliza DTOs, mapeadores, servicios y repositorios para garantizar un diseño modular, claro y mantenible. El frontend integrado permite probar fácilmente la funcionalidad del servidor.
