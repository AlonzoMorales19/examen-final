Examen Final - Sistema de Inventario Backend
Este es un proyecto backend desarrollado con Spring Boot para un caso de estudio de un sistema de inventario. La aplicación expone una API RESTful para gestionar Productos, Proveedores y Movimientos de inventario (entradas y salidas).

La lógica de negocio principal se encentra en InventarioService, que actualiza automáticamente el stock de un producto cada vez que se registra un movimiento.

🚀 Tecnologías Utilizadas
Este proyecto utiliza las siguientes tecnologías (basado en el pom.xml):

Java 21

Spring Boot 3.5.7

Spring Web: Para crear los controladores de la API REST.

Spring Data JPA: Para la persistencia de datos y la conexión con la base de datos.

PostgreSQL: Como motor de base de datos.

Maven: Como gestor de dependencias y construcción del proyecto.

📋 Prerrequisitos para la Ejecución
Antes de ejecutar el proyecto, asegúrate de tener instalado:

Java JDK 21 o superior.

Maven (o puedes usar el wrapper mvnw incluido).

Una instancia de PostgreSQL ejecutándose en tu máquina.

⚙️ Configuración
Base de Datos: El proyecto está configurado para conectarse a la base de datos examenfinal en localhost:5432. (Según application.properties).

URL: jdbc:postgresql://localhost:5432/examenfinal

Usuario: postgres

Contraseña: A17

Si tu base de datos, usuario o contraseña son diferentes, por favor modifica el archivo src/main/resources/application.properties.

Crear la Base de Datos: Si aún no lo has hecho, ejecuta el siguiente comando en pgAdmin:

SQL

CREATE DATABASE examenfinal;
Crear Tablas e Insertar Datos: La configuración spring.jpa.hibernate.ddl-auto=validate significa que Spring no creará las tablas por ti.

Debes ejecutar los scripts SQL (el DDL para CREATE TABLE y el DML para INSERT) que generamos anteriormente en la "Query Tool" de tu base de datos examenfinal antes de iniciar la aplicación.

▶️ Cómo Ejecutar el Proyecto
Una vez que la base de datos esté configurada y las tablas creadas:

Opción 1: Usando el Wrapper de Maven (Recomendado)
Abre una terminal en la raíz del proyecto y ejecuta:

Bash

./mvnw spring-boot:run
(Si estás en Windows, usa mvnw.cmd spring-boot:run)

Opción 2: Desde tu IDE (Eclipse, IntelliJ, VSCode)
Simplemente busca el archivo ExamenApplication.java (en src/main/java/com/examen/examen) y ejecútalo como una Aplicación Java.

La aplicación se iniciará en http://localhost:8080.

📄 API Endpoints (Punto 4.3)
La API base se encuentra en http://localhost:8080/api. Estos son los endpoints disponibles (definidos en InventarioController):

GET /api/productos
Descripción: Responde a: "Qué productos tienen y sus cantidades".

Devuelve: Un JSON con la lista de todos los productos y su stock_actual.

GET /api/productos/en-bodega
Descripción: Responde a: "Qué productos están en bodega".

Devuelve: Un JSON con la lista de productos donde el stock_actual es mayor a 0.

GET /api/proveedores
Descripción: Responde a: "De qué proveedores vienen".

Devuelve: Un JSON con la lista de todos los proveedores registrados.

GET /api/movimientos
Descripción: Responde a: "Historial de movimientos".

Devuelve: Un JSON con la lista de todos los movimientos (entradas y salidas) ordenados.

POST /api/movimientos
Descripción: Crea un nuevo movimiento (ENTRADA o SALIDA) y actualiza el stock del producto automáticamente.

Body (Ejemplo de SALIDA):

JSON

{
    "idMovimiento": 11,
    "tipo": "SALIDA",
    "cantidad": 5,
    "producto": { "idProducto": 101 },
    "proveedor": null
}
Body (Ejemplo de ENTRADA):

JSON

{
    "idMovimiento": 12,
    "tipo": "ENTRADA",
    "cantidad": 20,
    "producto": { "idProducto": 102 },
    "proveedor": { "idProveedor": 1 }
}
Respuesta: Devuelve el objeto Movimiento creado o un mensaje de error si el stock es insuficiente.