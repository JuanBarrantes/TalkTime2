package com.example.talktime.Beans;

public class SalaUsuario {
    private Integer idsalaUsuario;
    private Sala sala;
    private Cuenta cuenta;

    public SalaUsuario() {
    }

    public SalaUsuario(Integer idsalaUsuario) {
        this.idsalaUsuario = idsalaUsuario;
    }

    public SalaUsuario(Integer idsalaUsuario, Sala sala, Cuenta cuenta) {
        this.idsalaUsuario = idsalaUsuario;
        this.sala = sala;
        this.cuenta = cuenta;
    }

    public Integer getIdsalaUsuario() {
        return idsalaUsuario;
    }

    public void setIdsalaUsuario(Integer idsalaUsuario) {
        this.idsalaUsuario = idsalaUsuario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
