
# Challenge técnico: Seniority Boost 2022

Challenge desarrollado para participar
de la iniciativa Seniority Boost 2022.


## Tech Stack

- Tech: Java, Java Spring.

- Control Version: Git, GitHub.

- Test: Postman.

- Data Base: H2.

## Access / users, pass

Not used in this project.


## Endpoints

#### 1) Recibe un texto y lo guarda en la DB

```http
  POST /text
```

#### 2) Devuelve información de un texto identificado por ID

```http
  GET /text/{id}
```

#### 3) Devuelve los textos de manera paginada. Los textos que se hayan marcado como borrados no aparecen. Todos los parámetros son opcionales.

```http
  GET /text?chars={DATE}
  GET /text?page={DATE}
  GET /text?rpp={DATE}
```

#### 4) Borra un texto por un ID específico.

```http
  DELETE /text/{id}
```
## Descripción del progreso

El desafío me pareció muy interesante. Traté de resolverlo de la mejor manera posible en relación a mis conocimientos. Para llevarlo a cabo utilice como principal herramienta el framework Spring Boot de Java. Implemente el modelo vista controlador (MVC), además realicé test unitarios y de integración utilizando el marco de prueba de código abierto Mockito. En este caso sólo implementé algunos test quedando pendiente agregar más test, para poder obtener un coverage adecuado. Envío datos hardcodeados para utilizar la base de datos H2.


## Link al repositorio del proyecto
[![portfolio](https://img.shields.io/github/watchers/FigueredoGaston/challengerooftop?style=social)](https://github.com/FigueredoGaston/challengerooftop)
