package com.example.userservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MatriculaDto;
import com.example.userservice.service.MatriculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping("/hola")
    public String getMethodName() {
        return "Saludos desde el servicio de usuarios!";
    }
    
    @PostMapping("/send-message")
    public void sendMessage(@RequestBody MatriculaDto matriculaDto) {
        matriculaService.sendMessage(matriculaDto);
    }
}
