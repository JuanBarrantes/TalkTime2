package com.example.talktime;

public class Sala {
    private Integer idsala;
    private String fecha;
    private TipoSala tipoSala;

    public Sala() {
    }

    public Sala(Integer idsala) {
        this.idsala = idsala;
    }

    public Sala(Integer idsala, String fecha, TipoSala tipoSala) {
        this.idsala = idsala;
        this.fecha = fecha;
        this.tipoSala = tipoSala;
    }

    public Integer getIdsala() {
        return idsala;
    }

    public void setIdsala(Integer idsala) {
        this.idsala = idsala;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }
}
