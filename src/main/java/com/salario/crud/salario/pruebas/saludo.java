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

    @GetMapping("salarioPrueba/{salarioTotal}")
    public Double salarioHora(@PathVariable Double salarioTotal){
        return salarioLogica.calculoSalarioHora(salarioTotal);
    }

    @GetMapping("saludoPrueba/{nombreUsuario}")
    public String saludo(@PathVariable String nombreUsuario){
        Double salarioHoraCalculo = salarioHora(1200000.0);
        return "<h1>Inicializando Servidor ::: " + nombreUsuario + ": " + salarioHoraCalculo + "</h1>";
    }

}
