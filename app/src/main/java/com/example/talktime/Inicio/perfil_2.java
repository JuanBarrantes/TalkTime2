package com.example.talktime.Inicio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.talktime.R;
import com.example.talktime.Registro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public class perfil_2 extends AppCompatActivity {
    private Transition transition;
    Button btnGuardarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btnGuardarPerfil = findViewById(R.id.btnGuardarPerfil);
        setSupportActionBar(toolbar);

        FloatingActionButton fab4 = findViewById(R.id.fab4);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClicked(view);
            }
        });

        btnGuardarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //ALERTA(va despues del evento guardar)
                AlertDialog.Builder builder = new AlertDialog.Builder(perfil_2.this);
                builder.setTitle("¡LISTO!").setIcon(R.drawable.icono);
                builder.setMessage("Tu cuenta ha sido creada sin problemas");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int witch) {
                        Inicio(view);
                    }
                });
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
            }
        });
    }

    public void onBackClicked(View view){
        transition = new Slide(Gravity.RIGHT);
        transition.setDuration(Registro.Duracion_transicion);
        transition.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(transition);
        Intent siguiente  = new Intent(this, perfil_1.class);
        startActivity(siguiente, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    private void Inicio(View view){
        Intent siguiente  = new Intent(this, inicio.class);
        startActivity(siguiente);
    }

}
