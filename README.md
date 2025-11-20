# Microservicio de Notificaciones con RabbitMQ

Sistema de notificaciones por correo electrónico usando Spring Boot, RabbitMQ y Mailtrap para el envío de correos de matrícula.

## Ejecución Local
mvn clean install

mvn spring-boot:run

## Ejecución con Docker
**Configuración requerida:**
1. Copiar `.env.example` a `.env`
2. Editar `.env` con tus credenciales reales de Mailtrap

```bash
cp .env.example .env
# Editar .env con tus credenciales
docker-compose up --build
```

Para ejecutar en segundo plano:
```bash
docker-compose up -d
```

http://localhost:15672
Usuario: guest
Contraseña: guest

curl -X POST http://localhost:9100/send-message \
-H "Content-Type: application/json" \
-d '{
    "matriculaId": 14,
    "fechaMatricula": "2025-11-18",
    "estado": "A",
    "costo": 300,
    "metodoPago": "TRANSFERENCIA",
    "fechaRegistro": "2025-11-17T21:58:57",
    "estudianteId": 2,
    "nombreEstudiante": "EDILBERTO RODRIGUEZ",
    "correoEstudiante": "ERODRIGUEZ@GMAIL.COM",
    "seccionId": 6,
    "codigoSeccion": "SEC001",
    "nombreCurso": "BASE DE DATOS",
    "nombreProfesor": "ROBERTO ARAMBURO"
}'
```

**Nota**: El formato de `fechaRegistro` debe ser `yyyy-MM-dd'T'HH:mm:ss` (sin microsegundos ni zona horaria).

## Arquitectura del Sistema

- **Spring Boot**: Framework principal de la aplicación
- **RabbitMQ**: Message broker para comunicación asíncrona
- **Mailtrap**: Servicio de correo para testing
- **H2 Database**: Base de datos en memoria para demo
- **Docker Compose**: Orquestación de contenedores

## Acceso a Servicios

### RabbitMQ Management UI
http://localhost:15672  
Usuario: `guest`  
Contraseña: `guest`

### Aplicación Web
http://localhost:9100

### Base de Datos H2 Console
http://localhost:9100/h2-console

## Pruebas de API

### Endpoint de saludo
```bash
curl -X GET http://localhost:9100/hola
```

### Envío de notificación de matrícula
