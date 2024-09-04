# NewsProject - Sistema de Gestión de Noticias

NewsProject es una aplicación diseñada para la gestión y visualización de noticias en tiempo real. Aprovechando una arquitectura basada en microservicios, la aplicación permite a los usuarios buscar noticias, gestionar contenido y personalizar su experiencia de lectura.

![gifproject](https://github.com/user-attachments/assets/8d674a46-6a7f-4d95-9c2f-bb1b94219d3e)

## Descripción

NewsProject facilita la gestión integral de noticias a través de una interfaz intuitiva y eficiente. Los usuarios pueden acceder a una amplia gama de noticias categorizadas, buscar contenido específico, y administrar sus artículos favoritos. La aplicación está diseñada para ser escalable y confiable, utilizando microservicios para asegurar un rendimiento óptimo.

## Microservicios Principales

- **Config Server Service**: Servicio de configuración centralizada que proporciona configuraciones a todos los demás microservicios.
- **Eureka Server Service**: Servicio de descubrimiento de microservicios, que permite a los servicios registrarse y descubrir otros servicios.
- **Gateway Service**: API Gateway que maneja las peticiones y las redirige al microservicio correspondiente.
- **News Service**: Servicio responsable de gestionar las noticias, incluyendo creación, actualización, eliminación y búsqueda de noticias.
- **User Service**: Servicio que gestiona la autenticación y los datos de los usuarios, como registros y roles.
- **Web Service**: Interfaz de usuario que interactúa con los demás microservicios a través del Gateway.

## Tecnologías Utilizadas

- **Backend**: Spring Boot
- **Base de Datos**: MongoDB
- **Frontend**: Thymeleaf
- **Gestión de dependencias**: Maven
- **Estilos**: Bootstrap
- **API Gateway**: Spring Cloud Gateway
- **Registro y Descubrimiento de Servicios**: Eureka Server
- **Configuración Centralizada**: Spring Cloud Config

## Versiones Utilizadas

- **Java**: 17
- **Maven**: 3.9.7
- **Spring Boot**: 3.3.2
- **Spring Cloud**: Versión gestionada por Spring Boot (utilizando BOM)
- **Bootstrap**: 5.3.0
- **MongoDB**: 7.0.11

## Instalación

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/pbl99/microservices-news-project.git
    cd microservices-news-project
    ```

2. Compilar el proyecto:

    ```bash
    mvn clean install
    ```

3. Ejecutar cada microservicio:

   ```bash
    mvn spring-boot:run -pl config-server
    mvn spring-boot:run -pl eureka-server
    mvn spring-boot:run -pl api-gateway
    mvn spring-boot:run -pl news-service
    mvn spring-boot:run -pl user-service
    mvn spring-boot:run -pl web-service
    ```

4. Acceder a la aplicación a través del API Gateway:

    Abre tu navegador web y navega a [http://localhost:8080](http://localhost:8080). El API Gateway enruta las solicitudes a los microservicios correspondientes.

    Aquí tienes algunas rutas de ejemplo para acceder a los servicios a través del Gateway:

    - **Noticias**: `http://localhost:8080/api/news/latestNews`
    - **Usuarios**: `http://localhost:8080/api/user`

## Configuración

### Base de Datos

La aplicación utiliza MongoDB como base de datos principal tanto en el microservicio de User como en el de News. Puedes configurar la conexión a la base de datos en el archivo `application.yml` de cada microservicio, ubicado en `src/main/resources/`.

Ejemplo de configuración para MongoDB:

```properties
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: nombreDB
```
### Esquema de la Base de Datos (MongoDB)

### Colección: `users` de userDb

```json
{
  "_id": "ObjectId('66cef0223750645abbf0ca64')",
  "username": "String",
  "email": "String",
  "password": "String",
  "favoriteNewsIds": ["ObjectId"],
  "_class": "String"
}
```
### Colección: `news` de newsDb

```json
{
  "_id": "ObjectId('66c345669f2b4c511830ed4e')",
  "title": "String",
  "body": "String",
  "author": "String",
  "createAt": "String (Fecha en formato YYYY/MM/DD)",
  "_class": "String"
}
```

## APIs Utilizadas
- **[RTVE API](https://api.rtve.es/api/noticias.json):** Este proyecto utiliza la API de RTVE para obtener información sobre noticias.

## Contribuir
Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Fork el repositorio
2. Crea una nueva rama (git checkout -b feature/nueva-funcionalidad)
3. Realiza tus cambios y haz commit (git commit -am 'Añadir nueva funcionalidad')
4. Push a la rama (git push origin feature/nueva-funcionalidad)
5. Crea un Pull Request 


## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
