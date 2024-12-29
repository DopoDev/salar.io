package com.salario.crud.salario.pruebas;

import com.salario.crud.salario.services.SalarioLogica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class saludo {
    @Autowired
    SalarioLogica salarioLogica;


    @GetMapping("saludoPrueba/{nombreUsuario}")
    public String saludo(@PathVariable String nombreUsuario){
        return "<h1>Inicializando Servidor ::: " + nombreUsuario + ": " + "</h1>";
    }

}
