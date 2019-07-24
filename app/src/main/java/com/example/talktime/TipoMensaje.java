package com.example.talktime;

public class TipoMensaje {
    private Integer idtipoMensaje;
    private String tipo;
    private Mensaje mensaje;

    public TipoMensaje() {
    }

    public TipoMensaje(Integer idtipoMensaje) {
        this.idtipoMensaje = idtipoMensaje;
    }

    public TipoMensaje(Integer idtipoMensaje, String tipo, Mensaje mensaje) {
        this.idtipoMensaje = idtipoMensaje;
        this.tipo = tipo;
        this.mensaje = mensaje;
    }

    public Integer getIdtipoMensaje() {
        return idtipoMensaje;
    }

    public void setIdtipoMensaje(Integer idtipoMensaje) {
        this.idtipoMensaje = idtipoMensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
}
