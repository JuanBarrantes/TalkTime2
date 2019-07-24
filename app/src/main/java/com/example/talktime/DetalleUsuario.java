package com.example.talktime;

public class DetalleUsuario {
    private Integer iddetalleUsuario;
    private String descPrincipal;
    private String descSecundaria;
    private String descTerciaria;
    private Cuenta cuenta;

    public DetalleUsuario() {
    }

    public DetalleUsuario(Integer iddetalleUsuario) {
        this.iddetalleUsuario = iddetalleUsuario;
    }

    public DetalleUsuario(Integer iddetalleUsuario, String descPrincipal, String descSecundaria,
                          String descTerciaria, Cuenta cuenta) {
        this.iddetalleUsuario = iddetalleUsuario;
        this.descPrincipal = descPrincipal;
        this.descSecundaria = descSecundaria;
        this.descTerciaria = descTerciaria;
        this.cuenta = cuenta;
    }

    public Integer getIddetalleUsuario() {
        return iddetalleUsuario;
    }

    public void setIddetalleUsuario(Integer iddetalleUsuario) {
        this.iddetalleUsuario = iddetalleUsuario;
    }

    public String getDescPrincipal() {
        return descPrincipal;
    }

    public void setDescPrincipal(String descPrincipal) {
        this.descPrincipal = descPrincipal;
    }

    public String getDescSecundaria() {
        return descSecundaria;
    }

    public void setDescSecundaria(String descSecundaria) {
        this.descSecundaria = descSecundaria;
    }

    public String getDescTerciaria() {
        return descTerciaria;
    }

    public void setDescTerciaria(String descTerciaria) {
        this.descTerciaria = descTerciaria;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
