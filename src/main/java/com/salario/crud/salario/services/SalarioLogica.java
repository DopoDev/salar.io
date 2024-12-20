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

    public Double calculoHoraNocturna(Double valorHora){
        return valorHora + valorHora*(0.35);
    }

    public Double calculoHoraExtra(Double valorHora){
        return valorHora + valorHora*(0.25);
    }

    public Double calculoHoraExtraNocturna(Double valorHora){
        return valorHora + valorHora*(0.75);
    }

    public Double calculoHoraExtraDiurnaFestivo(Double valorHora){
        return valorHora + calculoHoraExtra(valorHora) + valorHora*(0.75);
    }

    public Double calculoHoraExtraNocturnaFestivo(Double valorHora){
        return valorHora + calculoHoraNocturna(valorHora) + valorHora*(0.75);
    }
}
