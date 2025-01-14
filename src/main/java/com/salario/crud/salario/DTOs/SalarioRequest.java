package com.salario.crud.salario.DTOs;

import com.salario.crud.salario.services.Horario;
import com.salario.crud.salario.services.Trabajador;

public class SalarioRequest {
    private Trabajador trabajador;
    private Horario horario;

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
