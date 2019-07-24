package com.example.talktime;

public class Mensaje {
    private Integer idmensaje;
    private String fecha;
    private String enlace;
    private String estado;
    private Cuenta cuenta;
    private Sala sala;

    public Mensaje() {
    }

    public Mensaje(Integer idmensaje) {
        this.idmensaje = idmensaje;
    }

    public Mensaje(Integer idmensaje, String fecha, String enlace, String estado, Cuenta cuenta, Sala sala) {
        this.idmensaje = idmensaje;
        this.fecha = fecha;
        this.enlace = enlace;
        this.estado = estado;
        this.cuenta = cuenta;
        this.sala = sala;
    }

    public Integer getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(Integer idmensaje) {
        this.idmensaje = idmensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
