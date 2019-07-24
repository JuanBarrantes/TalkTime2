package com.example.talktime;

public class Idioma {
    private Integer ididioma;
    private String nombre;
    private String nivel;
    private Cuenta cuenta;

    public Idioma() {
    }

    public Idioma(Integer ididioma) {
        this.ididioma = ididioma;
    }

    public Idioma(Integer ididioma, String nombre, String nivel, Cuenta cuenta) {
        this.ididioma = ididioma;
        this.nombre = nombre;
        this.nivel = nivel;
        this.cuenta = cuenta;
    }

    public Integer getIdidioma() {
        return ididioma;
    }

    public void setIdidioma(Integer ididioma) {
        this.ididioma = ididioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
