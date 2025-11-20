package com.example.dto;


import java.math.BigDecimal;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MatriculaDto {
    private Long matriculaId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMatricula;
    
    private String estado;
    private BigDecimal costo;
    private String metodoPago;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegistro;

    private Long estudianteId;
    private String nombreEstudiante;
    private String correoEstudiante;

    private Long seccionId;
    private String codigoSeccion;
    private String nombreCurso;
    private String nombreProfesor;

    public Long getMatriculaId() {
        return matriculaId;
    }
    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }
    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }
    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public BigDecimal getCosto() {
        return costo;
    }
    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Long getEstudianteId() {
        return estudianteId;
    }
    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }
    public String getNombreEstudiante() {
        return nombreEstudiante;
    }
    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }
    public Long getSeccionId() {
        return seccionId;
    }
    public void setSeccionId(Long seccionId) {
        this.seccionId = seccionId;
    }
    public String getCodigoSeccion() {
        return codigoSeccion;
    }
    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }
    public String getNombreCurso() {
        return nombreCurso;
    }
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    public String getNombreProfesor() {
        return nombreProfesor;
    }
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
    public String getCorreoEstudiante() {
        return correoEstudiante;
    }
    public void setCorreoEstudiante(String correoEstudiante) {
        this.correoEstudiante = correoEstudiante;
    }
    
}
