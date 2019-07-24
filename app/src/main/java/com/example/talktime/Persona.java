package com.example.talktime;

public class Persona {

    private Integer idpersona;
    private String nombre;
    private String apellidos;
    private String paisActual;
    private String lenguajeMaterna;
    private String lenaguajeAprendida;
    private String ocupacion;
    private String fechaNace;
    private String sexo;

    public Persona() {
    }

    public Persona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public Persona(Integer idpersona, String nombre, String apellidos, String paisActual,
                   String lenguajeMaterna, String lenaguajeAprendida, String ocupacion,
                   String fechaNace, String sexo) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.paisActual = paisActual;
        this.lenguajeMaterna = lenguajeMaterna;
        this.lenaguajeAprendida = lenaguajeAprendida;
        this.ocupacion = ocupacion;
        this.fechaNace = fechaNace;
        this.sexo = sexo;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPaisActual() {
        return paisActual;
    }

    public void setPaisActual(String paisActual) {
        this.paisActual = paisActual;
    }

    public String getLenguajeMaterna() {
        return lenguajeMaterna;
    }

    public void setLenguajeMaterna(String lenguajeMaterna) {
        this.lenguajeMaterna = lenguajeMaterna;
    }

    public String getLenaguajeAprendida() {
        return lenaguajeAprendida;
    }

    public void setLenaguajeAprendida(String lenaguajeAprendida) {
        this.lenaguajeAprendida = lenaguajeAprendida;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getFechaNace() {
        return fechaNace;
    }

    public void setFechaNace(String fechaNace) {
        this.fechaNace = fechaNace;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



}
