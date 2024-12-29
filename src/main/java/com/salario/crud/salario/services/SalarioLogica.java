package com.salario.crud.salario.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalarioLogica {
    private Double salarioTotal;
    private Double salarioHora;

    public Double calculoSalarioHora(Double salarioTotal){
        double valorDia = salarioTotal/30;
        double valorHora = valorDia/8;
        salarioHora = valorHora;
        return valorHora;
    }

    public Double calculoHoraNocturna(Double valorHora, int numeroHoras){
        return (valorHora + valorHora*(0.35)) * numeroHoras;
    }

    public Double calculoHoraDiaFestivo(Double valorHora, int numeroHoras){
        return (valorHora + valorHora*(0.75)) * numeroHoras;
    }

    public Double calculoHoraNocturnaFestivo(Double valorHora, int numeroHoras){
        return calculoHoraNocturna(valorHora, numeroHoras) + calculoHoraDiaFestivo(valorHora, numeroHoras);
    }

    public Double calculoHoraExtra(Double valorHora, int numeroHoras){
        return (valorHora + valorHora*(0.25)) * numeroHoras;
    }

    public Double calculoHoraExtraNocturna(Double valorHora, int numeroHoras){
        return (valorHora + valorHora*(0.75)) * numeroHoras;
    }

    public Double calculoHoraExtraDiurnaFestivo(Double valorHora, int numeroHoras){
        return (valorHora*numeroHoras) + calculoHoraExtra(valorHora, numeroHoras) + valorHora*(0.75)*numeroHoras;
    }

    public Double calculoHoraExtraNocturnaFestivo(Double valorHora, int numeroHoras){
        return valorHora*numeroHoras + calculoHoraNocturna(valorHora, numeroHoras) + valorHora*(0.75)*numeroHoras;
    }
}
