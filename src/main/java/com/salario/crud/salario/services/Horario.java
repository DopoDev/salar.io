package com.salario.crud.salario.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class Horario {
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private LocalTime comienzoHoraNocturna;

    public int numeroHorasTrabajadas(LocalTime horaIngreso, LocalTime horaSalida){
        Duration duracion = Duration.between(horaIngreso, horaSalida);
        int duracionHoras = (int) duracion.toHours();
        return duracionHoras;
    }

    public Boolean horarioNoche(@NotNull LocalTime horaIngreso){
        comienzoHoraNocturna=LocalTime.of(18, 0);
        if(horaIngreso.isAfter(comienzoHoraNocturna) || horaIngreso.equals(comienzoHoraNocturna)){
            return true;
        }
        return false;
    }

}
