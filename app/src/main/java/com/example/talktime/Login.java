package com.example.talktime;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    RequestQueue requestQueue;
    EditText LoginCorreo,LoginClave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LoginCorreo=findViewById(R.id.txtUsuario);
        LoginClave=findViewById(R.id.txtPass);



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
              /*  AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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
                }, 700);*/
                Log.i("MyActivity", "onClick: "+LoginCorreo.getText().toString());
                logear("http://clpe5.com/talk/json/cuentaJson.php?acion=data&email="+LoginCorreo.getText().toString()+"&clave="+LoginClave.getText().toString());

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

    private void Inicio(View view)
    {
        Intent siguiente  = new Intent(this, com.example.talktime.Inicio.inicio.class);
        startActivity(siguiente);
    }
    private void logear(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Respuesta de BD").setIcon(R.drawable.icono);
                builder.setMessage("exitosa"+response);
                builder.setPositiveButton("OK", null);
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Respuesta de BD").setIcon(R.drawable.icono);
                builder.setMessage("fallo"+error.getMessage());
                builder.setPositiveButton("OK", null);
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
