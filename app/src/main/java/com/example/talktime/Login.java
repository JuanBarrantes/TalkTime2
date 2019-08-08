package com.example.talktime;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnLogin = findViewById(R.id.BtnIninciar);
        Button btnRegistro = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Siguiente(view);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("CARGANDO...").setIcon(R.drawable.icono);
                builder.setPositiveButton("OK",null);
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(null != mDialog && mDialog.isShowing()) {
                            mDialog.dismiss();
                            Inicio(view);
                        }
                    }
                }, 700);
            }
        });

      /*  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void Siguiente(View view){
        Intent siguiente  = new Intent(this, Registro.class);
        startActivity(siguiente);
    }

    private void Inicio(View view){
        Intent siguiente  = new Intent(this, inicio.class);
        startActivity(siguiente);
    }

}
