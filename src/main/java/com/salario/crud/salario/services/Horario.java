package com.salario.crud.salario.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Service
public class Horario {
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private static final LocalTime COMIENZO_HORA_NOCTURNA = LocalTime.of(18, 0);

    public int numeroHorasTrabajadas(LocalTime horaIngreso, LocalTime horaSalida){
        Duration duracion = Duration.between(horaIngreso, horaSalida);
        int duracionHoras = (int) duracion.toHours();
        return duracionHoras;
    }

    public Boolean horarioNoche(@NotNull LocalTime horaIngreso){
        return horaIngreso.isAfter(COMIENZO_HORA_NOCTURNA) || horaIngreso.equals(COMIENZO_HORA_NOCTURNA);
    }

}
