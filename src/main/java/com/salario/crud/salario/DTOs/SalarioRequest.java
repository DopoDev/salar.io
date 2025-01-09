package com.salario.crud.salario.DTOs;

import com.salario.crud.salario.services.Horario;
import com.salario.crud.salario.services.Trabajador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalarioRequest {
    private Trabajador trabajador;
    private Horario horario;
}
