package com.salario.crud.salario.services;


import java.time.LocalTime;


public class TipoHorarios {
    private LocalTime mañana1 = LocalTime.of(6, 0);
    private LocalTime mañana2 = LocalTime.of(8, 0);
    private LocalTime mañana3 = LocalTime.of(9, 0);

    private LocalTime tarde1 = LocalTime.of(14, 0);
    private LocalTime tarde2 = LocalTime.of(16, 0);
    private LocalTime tarde3 = LocalTime.of(17, 0);
    private LocalTime tarde4 = LocalTime.of(18, 0);

    private LocalTime noche1 = LocalTime.of(20, 0);
    private LocalTime noche2 = LocalTime.of(22, 0);
    private LocalTime noche3 = LocalTime.of(4, 0);

    public Horario horarioEjecutivo = new Horario(mañana2, tarde3);
    public Horario horario1 = new Horario(mañana1, tarde1);
    public Horario horario2 = new Horario(tarde1, noche2);
    public Horario horario3 = new Horario(noche2, mañana1);
}
