package com.salario.crud.salario.services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class Trabajador {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double salarioTotal;
    private Boolean esFestivo = false;

    @Autowired
    SalarioLogica salarioLogica;

    @Autowired
    Horario horario;

    private Double salarioHora;

    public int numeroDiasTrabajados(LocalDate diaInicio, LocalDate diaFinal){
        long duracionDias = ChronoUnit.DAYS.between(diaInicio, diaFinal);
        if(duracionDias == 0){
            return 1;
        }else{
            return (int) Math.abs(duracionDias);
        }
    }

    @PostConstruct
    public void init(){
        if(salarioTotal != null){
            salarioHora = salarioLogica.calculoSalarioHora(salarioTotal);
        }
    }

    public Double calculoSalarioNocturno(LocalTime horaIngreso, LocalTime horaSalida, Boolean diaFestivo){
        horario.setHoraIngreso(horaIngreso);
        horario.setHoraSalida(horaSalida);
        int horasTrabajadas = horario.numeroHorasTrabajadas(horaIngreso, horaSalida);

        int numeroHorasExtras = horasTrabajadas - 8;

        if(horasTrabajadas<=8 && diaFestivo){
            return salarioLogica.calculoHoraNocturna(salarioHora, horasTrabajadas) + salarioLogica.calculoHoraDiaFestivo(salarioHora, horasTrabajadas);
        }else if(horasTrabajadas<=8 && !diaFestivo){
            return salarioLogica.calculoHoraNocturna(salarioHora, horasTrabajadas); 
        }else if(numeroHorasExtras>0 && diaFestivo){
            return salarioLogica.calculoHoraNocturna(salarioHora, horasTrabajadas) + salarioLogica.calculoHoraExtraNocturna(salarioHora, numeroHorasExtras) + salarioLogica.calculoHoraNocturnaFestivo(salarioHora, horasTrabajadas);
        }else if(numeroHorasExtras>0 && !diaFestivo){
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
        }else if(numeroHorasExtras>0 && !diaFestivo){
            return (salarioHora * (horasTrabajadas)) + salarioLogica.calculoHoraExtra(salarioHora, numeroHorasExtras);
        }else if(numeroHorasExtras>0 && diaFestivo){
            return (salarioHora * (horasTrabajadas)) + salarioLogica.calculoHoraExtra(salarioHora, numeroHorasExtras) + salarioLogica.calculoHoraDiaFestivo(salarioHora, horasTrabajadas);
        }
        return 0.0;
    }

    public Double calculoSalarioMensual(int numeroDiasTrabajado, Double salarioCalculado){
        return salarioCalculado*numeroDiasTrabajado;
    }

    public Double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(Double salarioHora) {
        this.salarioHora = salarioHora;
    }

    public Boolean getEsFestivo() {
        return esFestivo;
    }

    public void setEsFestivo(Boolean esFestivo) {
        this.esFestivo = esFestivo;
    }

    public Double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(Double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
