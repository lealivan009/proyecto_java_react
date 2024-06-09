# AlMedin API

Una API que agilioza la visualizacion y registro de turnos con medicos aderidos a la obra social.

---

## Running the application

Configure una base de datos `mysql` o `h2` en el `.env.template` como se ve a continuacion.

``` 
db.url= # tu rul de base de datos #
db.username= # tu usuario #
db.password= # tu contraseña #
db.kind= # mysql | h2 #
db.port= # puerto de la aplicacion (opcional) #

# copiar el archivo, setear las variables y cambiar por el nombre de -> .env.properties

```


You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```


## Java Structure
```
├───main
│   ├───docker
│   ├───java
│   │   ├───controllers
│   │   ├───dto
│   │   │   ├───request
│   │   │   └───response
│   │   ├───Exception
│   │   │   └───handler
│   │   ├───mapper
│   │   ├───models
│   │   │   └───enumerations
│   │   ├───repositories
│   │   ├───services
│   │   │   └───impl
│   │   └───validator
│   └───resources
└───test
    └───java
        └───org
            └───acme
```

## Dependencies
 - `Lombok`
 - `quarkus-resteasy`
 - `quarkus-resteasy-jsonb`
 - `quarkus-ark`
 - `quarkus-hibernate-orm`
 - `quarkus-hibernate-orm-panache`
 - `quarkus-hibernate-validator`
 - `quarkus-security-jpa`
 - `quarkus-smallrye-openai`
 - `quarkus-jdbc-mysql`
 - `quarkus-jdbc-h2`
 - `quarkus-jdbc`
 - `rest-assured`


# Api Description

Puede ver la documentacion completa de los recursos levantando la aplicacion e ingresando a la ruta.
```
http://localhost:8080/q/swagger-ui
```

![This is an alt text.](/images/title.webp "This is a sample image.")
### Medical Controller
![This is an alt text.](/images/medical.webp "This is a sample image.")

    Register a new medical
    /api/medicals/register -> POST
    
    Get all medicals
    /api/medicals -> GET

    Get one medical
    /api/medicals/{id} -> GET

    Update a medical
    /api/medicals/{id}/schedules -> PUT


### Specialist Controller

![This is an alt text.](/images/specialist.webp "This is a sample image.")

    Get all specialists
    /api/specialists -> GET

### User Controller

![This is an alt text.](/images/user.webp "This is a sample image.")

    Get all users
    /api/users -> GET

    Login user
    /api/users/login -> POST

    Register a new user
    /api/users/register -> POST

    Get one user
    /api/users/{id} -> GET

    Update one user
    /api/users/{id} -> PUT

    Delete one user
    /api/users/{id} -> DELETE

### Prescription Controller

![This is an alt text.](/images/prescription.webp "This is a sample image.")

    Post a prescription 
    /api/prescriptions/{appointmentId}

    Get a prescription 
    /api/prescriptions/{prescriptionId}

### Puntos a mejorar

 - Test en la aplicacion
 - Validar algunos datos de entrada 
 - implementacion de seguridad tanto autenticacion como autorizacion.
 - Posibles optimizaciones en las querys a base de datos