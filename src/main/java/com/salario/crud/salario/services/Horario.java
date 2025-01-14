package com.salario.crud.salario.services;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@AllArgsConstructor
@Service
public class Horario {
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private static final LocalTime COMIENZO_HORA_NOCTURNA = LocalTime.of(18, 0);

    public int numeroHorasTrabajadas(LocalTime horaIngreso, LocalTime horaSalida){
        Duration duracion = Duration.between(horaIngreso, horaSalida);
        int duracionHoras = (int) duracion.toHours();
        return Math.abs(duracionHoras);
    }

    public Boolean horarioNoche(@NotNull LocalTime horaIngreso){
        return horaIngreso.isAfter(COMIENZO_HORA_NOCTURNA) || horaIngreso.equals(COMIENZO_HORA_NOCTURNA);
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
}
