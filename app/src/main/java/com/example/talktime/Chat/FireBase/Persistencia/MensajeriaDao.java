package com.example.talktime.Chat.FireBase.Persistencia;

import com.example.talktime.Chat.FireBase.Entidades.FireBase.Mensaje;
import com.example.talktime.Chat.FireBase.Utilidades.Constantes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MensajeriaDao {
    private static MensajeriaDao mensajeriaDAO;

        private FirebaseDatabase database;
        private DatabaseReference referenceMensajeria;

        public static MensajeriaDao getInstancia(){
            if(mensajeriaDAO==null) mensajeriaDAO = new MensajeriaDao();
            return mensajeriaDAO;
        }

        private MensajeriaDao(){
            database = FirebaseDatabase.getInstance();
            referenceMensajeria = database.getReference(Constantes.NODO_MENSAJES);
            //storage = FirebaseStorage.getInstance();
            //referenceUsuarios = database.getReference(Constantes.NODO_USUARIOS);
            //referenceFotoDePerfil = storage.getReference("Fotos/FotoPerfil/"+getKeyUsuario());
        }

        public void nuevoMensaje(String keyEmisor, String keyReceptor, Mensaje mensaje){
            DatabaseReference referenceEmisor = referenceMensajeria.child(keyEmisor).child(keyReceptor);
            DatabaseReference referenceReceptor = referenceMensajeria.child(keyReceptor).child(keyEmisor);
            referenceEmisor.push().setValue(mensaje);
            referenceReceptor.push().setValue(mensaje);
        }

}