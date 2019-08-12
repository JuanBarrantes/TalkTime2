package com.example.talktime.Chat.FireBase.Entidades.Logica;

import com.example.talktime.Chat.FireBase.Entidades.FireBase.Usuario;
import com.example.talktime.Chat.FireBase.Persistencia.UsuarioDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LUsuario {
    private String key;
    private Usuario usuario;

    public LUsuario(String key, Usuario usuario) {
        this.key = key;
        this.usuario = usuario;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String obtenerFechaDeCreacion(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date(UsuarioDao.getInstancia().fechaDeCreacionLong());
        return simpleDateFormat.format(date);
    }

    public String obtenerFechaDeUltimaVezQueSeLogeo(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date(UsuarioDao.getInstancia().fechaDeUltimaVezQueSeLogeoLong());
        return simpleDateFormat.format(date);
    }

}
