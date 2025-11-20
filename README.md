mvn clean install

mvn spring-boot:run

docker-compose up --build

docker-compose up -d

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
    "fechaRegistro": "2025-11-17T21:58:57.3551145-05:00",
    "estudianteId": 2,
    "nombreEstudiante": "EDILBERTO RODRIGUEZ",
    "correoEstudiante": "ERODRIGUEZ@GMAIL.COM",
    "seccionId": 6,
    "codigoSeccion": "SEC001",
    "nombreCurso": "BASE DE DATOS",
    "nombreProfesor": "ROBERTO ARAMBURO"
}'

## pruebas
curl -X GET http://localhost:9100/hola
