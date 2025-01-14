package com.salario.crud.salario.pruebas;

import com.salario.crud.salario.DTOs.SalarioRequest;
import com.salario.crud.salario.services.Horario;
import com.salario.crud.salario.services.SalarioLogica;
import com.salario.crud.salario.services.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class saludo {
    @Autowired
    SalarioLogica salarioLogica;


    @GetMapping("saludoPrueba/{nombreUsuario}")
    public String saludo(@PathVariable String nombreUsuario){
        return "<h1>Inicializando Servidor ::: " + nombreUsuario + ": " + "</h1>";
    }

    @Autowired
    Trabajador trabajador;

    @Autowired
    Horario horario;

    @PostMapping("salarioPrueba")
    public Double calculoSalarioController(@RequestBody SalarioRequest request){
        Trabajador trabajadorBody = request.getTrabajador();
        Horario horarioBody = request.getHorario();

        Double salarioMensual = trabajadorBody.getSalarioTotal();
        trabajador.setSalarioTotal(salarioMensual);
        Double trabajadorSalarioMensual = trabajador.getSalarioTotal();
        Double salarioHora = salarioLogica.calculoSalarioHora(trabajadorSalarioMensual);
        trabajador.setSalarioHora(salarioHora);

        horario.setHoraIngreso(horarioBody.getHoraIngreso());
        LocalTime horaEntrada = horario.getHoraIngreso();
        horario.setHoraSalida(horarioBody.getHoraSalida());
        LocalTime horaSalida = horario.getHoraSalida();

        trabajador.setFechaInicio(trabajadorBody.getFechaInicio());
        LocalDate fechaInicio = trabajador.getFechaInicio();
        trabajador.setFechaFin(trabajadorBody.getFechaFin());
        LocalDate fechaFinal = trabajador.getFechaFin();

        int numeroDiasTrabajados = trabajador.numeroDiasTrabajados(fechaInicio, fechaFinal);

        trabajador.setEsFestivo(trabajadorBody.getEsFestivo());
        Boolean diaFestivo = trabajador.getEsFestivo();

        Double salarioTotal = 0.0;

        if(horario.horarioNoche(horaEntrada)){
           Double salarioNocturno = trabajador.calculoSalarioNocturno(horaEntrada, horaSalida, diaFestivo);
            salarioTotal += salarioNocturno;
        }

        if(!horario.horarioNoche(horaEntrada)){
             Double salarioDiurno = trabajador.calculoSalarioDiurno(horaEntrada, horaSalida, diaFestivo);
             salarioTotal += salarioDiurno;
        }

        Double salarioTotalTrabajado = trabajador.calculoSalarioMensual(numeroDiasTrabajados, salarioTotal);

        return salarioTotalTrabajado;
    }


}
