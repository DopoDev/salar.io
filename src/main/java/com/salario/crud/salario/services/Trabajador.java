package com.salario.crud.salario.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trabajador {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double salarioTotal;
    private Boolean esFestivo;

    @Autowired
    SalarioLogica salarioLogica;

    @Autowired
    Horario horario;

    public int numeroDiasTrabajados(LocalDate diaInicio, LocalDate diaFinal){
        long duracionDias = ChronoUnit.DAYS.between(diaInicio, diaFinal);
        return (int) duracionDias;
    }

    Double salarioHora = salarioLogica.calculoSalarioHora(salarioTotal);

    public Double calculoSalarioNocturno(LocalTime horaIngreso, LocalTime horaSalida, Boolean diaFestivo){
        horario.setHoraIngreso(horaIngreso);
        horario.setHoraSalida(horaSalida);
        int horasTrabajadas = horario.numeroHorasTrabajadas(horaIngreso, horaSalida);

        int numeroHorasExtras = horasTrabajadas - 8;

        if(horasTrabajadas<=8 && diaFestivo){
            return salarioLogica.calculoHoraNocturna(salarioHora, horasTrabajadas) + salarioLogica.calculoHoraDiaFestivo(salarioHora, horasTrabajadas);
        }else if(horasTrabajadas<=8 && !diaFestivo){
            return salarioLogica.calculoHoraNocturna(salarioHora, horasTrabajadas); 
        }else if(horasTrabajadas>8 && diaFestivo){
            return salarioLogica.calculoHoraNocturna(salarioHora, horasTrabajadas) + salarioLogica.calculoHoraExtraNocturna(salarioHora, numeroHorasExtras) + salarioLogica.calculoHoraNocturnaFestivo(salarioHora, horasTrabajadas);
        }else if(horasTrabajadas>8 && !diaFestivo){
            return salarioLogica.calculoHoraNocturna(salarioHora, horasTrabajadas) + salarioLogica.calculoHoraExtraNocturna(salarioHora, numeroHorasExtras);
        }
        return 0.0;
    }

    public Double calculoSalarioDiurno(LocalTime horaIngreso, LocalTime horaSalida, Boolean diaFestivo){
        horario.setHoraIngreso(horaIngreso);
        horario.setHoraSalida(horaSalida);
        int horasTrabajadas = horario.numeroHorasTrabajadas(horaIngreso, horaSalida);

        int numeroHorasExtras = horasTrabajadas - 8;

        if(horasTrabajadas<=8 && !diaFestivo){
            return salarioHora * horasTrabajadas;
        }else if(horasTrabajadas<=8 && diaFestivo){
            return salarioLogica.calculoHoraDiaFestivo(salarioHora, horasTrabajadas);
        }else if(horasTrabajadas>8 && !diaFestivo){
            return (salarioHora * (horasTrabajadas)) + salarioLogica.calculoHoraExtra(salarioHora, numeroHorasExtras);
        }else if(horasTrabajadas>8 && diaFestivo){
            return (salarioHora * (horasTrabajadas)) + salarioLogica.calculoHoraExtra(salarioHora, numeroHorasExtras) + salarioLogica.calculoHoraDiaFestivo(salarioHora, horasTrabajadas);
        }
        return 0.0;
    }

    public Double calculoSalarioMensual(int numeroDiasTrabajado, Double salarioCalculado){
        return salarioCalculado*numeroDiasTrabajado;
    }
}
