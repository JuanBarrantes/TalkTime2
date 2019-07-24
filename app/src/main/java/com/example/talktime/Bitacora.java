package com.example.talktime;

public class Bitacora {
    private Integer idbitacora;
    private String fecha;
    private String hora;
    private String estado;
    private Persona persona;

    public Bitacora() {
    }

    public Bitacora(Integer idbitacora) {
        this.idbitacora = idbitacora;
    }

    public Bitacora(Integer idbitacora, String fecha, String hora, String estado, Persona persona) {
        this.idbitacora = idbitacora;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.persona = persona;
    }

    public Integer getIdbitacora() {
        return idbitacora;
    }

    public void setIdbitacora(Integer idbitacora) {
        this.idbitacora = idbitacora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
