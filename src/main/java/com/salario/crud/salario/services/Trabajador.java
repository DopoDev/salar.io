package com.salario.crud.salario.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trabajador {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean esFestivo;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida; 

    @Autowired
    SalarioLogica salarioLogica;

    public int numeroDiasTrabajados(LocalDate diaInicio, LocalDate diaFinal){
        long duracionDias = ChronoUnit.DAYS.between(diaInicio, diaFinal);
        return (int) duracionDias;
    }

        //Duration duracion = Duration.between(horaIngreso, horaSalida);
        //int duracionHoras = (int) duracion.toHours();
    public int numeroHorasTrabajadas(LocalDateTime horaIngreso, LocalDateTime horaSalida){
        int horaInicial = (int) horaIngreso.getHour();
        int horaFinal = (int) horaSalida.getHour();

        int duracionHoras = horaFinal - horaInicial;

        return duracionHoras;
    }

    public Double calculoSalarioTotal(Double salarioTotalMes, LocalDateTime horaIngreso, LocalDateTime horaSalida, LocalDate diaInicio, LocalDate diaFinal){
        Double salarioHora = salarioLogica.calculoSalarioHora(salarioTotalMes);

    }

}
