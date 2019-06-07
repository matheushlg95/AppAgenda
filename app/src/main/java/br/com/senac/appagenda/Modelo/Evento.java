package br.com.senac.appagenda.Modelo;

import java.io.Serializable;

public class Evento implements Serializable {
    private Long id;
    private String evento;
    private String dia;
    private String hora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return getId() + " - Evento: " + getEvento() +" - Dia: " + getDia() + " - Hora: " + getHora();
    }
}
