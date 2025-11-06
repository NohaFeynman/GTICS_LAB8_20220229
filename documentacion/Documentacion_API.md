\## API REST - Servidor de Productos



\### Descripción General



Este documento describe los endpoints del servicio Servidor API, desarrollado en Spring Boot, que gestiona productos mediante operaciones CRUD básicas. Los datos son expuestos en formato JSON y están destinados a ser consumidos por el cliente web o cualquier otra aplicación que requiera acceso al catálogo de productos.



---



\### URL Base



```

http://localhost:8080

```



---



\## Endpoints del API



\### 1. Listar todos los productos



\*\*Método:\*\* GET

\*\*Endpoint:\*\* /product



\*\*Descripción:\*\*

Devuelve una lista con todos los productos almacenados en el sistema.



\*\*Respuesta Exitosa (200 OK):\*\*



```json

\[

&nbsp; {

&nbsp;   "id": 1,

&nbsp;   "name": "Laptop Dell Inspiron",

&nbsp;   "price": 3500.00,

&nbsp;   "stock": 10,

&nbsp;   "category": {

&nbsp;     "id": 1,

&nbsp;     "categoryName": "Computadoras"

&nbsp;   }

&nbsp; },

&nbsp; {

&nbsp;   "id": 2,

&nbsp;   "name": "Mouse Inalámbrico Logitech",

&nbsp;   "price": 85.50,

&nbsp;   "stock": 25,

&nbsp;   "category": {

&nbsp;     "id": 2,

&nbsp;     "categoryName": "Accesorios"

&nbsp;   }

&nbsp; }

]

```



\*\*Posibles Códigos de Estado:\*\*



| Código                    | Descripción                  |

| ------------------------- | ---------------------------- |

| 200 OK                    | Lista obtenida correctamente |

| 500 Internal Server Error | Error interno en el servidor |



---



\### 2. Buscar un producto por ID



\*\*Método:\*\* GET

\*\*Endpoint:\*\* /product/{id}



\*\*Descripción:\*\*

Devuelve la información del producto que coincida con el ID proporcionado.



\*\*Parámetros de Entrada:\*\*



| Parámetro | Tipo    | Descripción                      |

| --------- | ------- | -------------------------------- |

| id        | Integer | Identificador único del producto |



\*\*Respuesta Exitosa (200 OK):\*\*



```json

{

&nbsp; "id": 2,

&nbsp; "name": "Mouse Inalámbrico Logitech",

&nbsp; "price": 85.50,

&nbsp; "stock": 25,

&nbsp; "category": {

&nbsp;   "id": 2,

&nbsp;   "categoryName": "Accesorios"

&nbsp; }

}

```



\*\*Respuesta de Error (404 Not Found):\*\*



```json

{

&nbsp; "error": "Producto no encontrado"

}

```



\*\*Posibles Códigos de Estado:\*\*



| Código                    | Descripción                |

| ------------------------- | -------------------------- |

| 200 OK                    | Producto encontrado        |

| 400 Bad Request           | ID inválido                |

| 404 Not Found             | No se encontró el producto |

| 500 Internal Server Error | Error interno              |



---



\### 3. Crear un nuevo producto



\*\*Método:\*\* POST

\*\*Endpoint:\*\* /product



\*\*Descripción:\*\*

Registra un nuevo producto en el sistema.



\*\*Cuerpo de Solicitud (JSON):\*\*



```json

{

&nbsp; "name": "Teclado Mecánico Redragon",

&nbsp; "price": 190.00,

&nbsp; "stock": 15,

&nbsp; "category": {

&nbsp;   "id": 2

&nbsp; }

}

```



\*\*Respuesta Exitosa (201 Created):\*\*



```json

{

&nbsp; "id": 5,

&nbsp; "name": "Teclado Mecánico Redragon",

&nbsp; "price": 190.00,

&nbsp; "stock": 15,

&nbsp; "category": {

&nbsp;   "id": 2,

&nbsp;   "categoryName": "Accesorios"

&nbsp; }

}

```



\*\*Posibles Códigos de Estado:\*\*



| Código                    | Descripción                    |

| ------------------------- | ------------------------------ |

| 201 Created               | Producto creado correctamente  |

| 400 Bad Request           | Datos inválidos o incompletos  |

| 500 Internal Server Error | Error al registrar el producto |



---



\### 4. Actualizar un producto existente



\*\*Método:\*\* PUT

\*\*Endpoint:\*\* /product/{id}



\*\*Descripción:\*\*

Actualiza la información de un producto existente.



\*\*Parámetros de Entrada:\*\*



| Parámetro | Tipo    | Descripción                             |

| --------- | ------- | --------------------------------------- |

| id        | Integer | Identificador del producto a actualizar |



\*\*Cuerpo de Solicitud (JSON):\*\*



```json

{

&nbsp; "name": "Laptop Dell Inspiron 15",

&nbsp; "price": 3700.00,

&nbsp; "stock": 8,

&nbsp; "category": {

&nbsp;   "id": 1

&nbsp; }

}

```



\*\*Respuesta Exitosa (200 OK):\*\*



```json

{

&nbsp; "id": 1,

&nbsp; "name": "Laptop Dell Inspiron 15",

&nbsp; "price": 3700.00,

&nbsp; "stock": 8,

&nbsp; "category": {

&nbsp;   "id": 1,

&nbsp;   "categoryName": "Computadoras"

&nbsp; }

}

```



\*\*Posibles Códigos de Estado:\*\*



| Código                    | Descripción                        |

| ------------------------- | ---------------------------------- |

| 200 OK                    | Producto actualizado correctamente |

| 400 Bad Request           | Datos inválidos o faltantes        |

| 404 Not Found             | Producto no encontrado             |

| 500 Internal Server Error | Error interno                      |



---



\### 5. Eliminar un producto



\*\*Método:\*\* DELETE

\*\*Endpoint:\*\* /product/{id}



\*\*Descripción:\*\*

Elimina un producto existente por su identificador.



\*\*Respuesta Exitosa (204 No Content):\*\*

No retorna cuerpo.



\*\*Respuesta de Error (404 Not Found):\*\*



```json

{

&nbsp; "error": "Producto no encontrado"

}

```



\*\*Posibles Códigos de Estado:\*\*



| Código                    | Descripción                     |

| ------------------------- | ------------------------------- |

| 204 No Content            | Producto eliminado exitosamente |

| 404 Not Found             | Producto no encontrado          |

| 500 Internal Server Error | Error interno al eliminar       |



---



\### Estructura de un Objeto Producto



```json

{

&nbsp; "id": 1,

&nbsp; "name": "Laptop Dell Inspiron",

&nbsp; "price": 3500.00,

&nbsp; "stock": 10,

&nbsp; "category": {

&nbsp;   "id": 1,

&nbsp;   "categoryName": "Computadoras"

&nbsp; }

}

```



---



\### Tecnologías Utilizadas



\* Java 17

\* Spring Boot 3.3+

\* Spring Web

\* Spring Data JPA

\* MySQL / H2

\* Maven

\* Thymeleaf (para cliente web)



---



\### Autor



Nilton Fernando Condori Quispe

Pontificia Universidad Católica del Perú (PUCP)



