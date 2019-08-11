package com.example.talktime.Beans;

public class TipoSala {
    private Integer idtipoSala;
    private String nombre;

    public TipoSala() {
    }

    public TipoSala(Integer idtipoSala) {
        this.idtipoSala = idtipoSala;
    }

    public TipoSala(Integer idtipoSala, String nombre) {
        this.idtipoSala = idtipoSala;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdtipoSala() {
        return idtipoSala;
    }

    public void setIdtipoSala(Integer idtipoSala) {
        this.idtipoSala = idtipoSala;
    }
}
