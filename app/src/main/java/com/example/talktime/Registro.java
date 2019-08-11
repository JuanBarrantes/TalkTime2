package com.example.talktime;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.animation.DecelerateInterpolator;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    boolean click = false;
    private Transition transition;
    public static final long Duracion_transicion= 1000;
    RequestQueue requestQueue;

    EditText editNombre,editApellido,editCorreo,editUsuario,editClave;
    Button btnRegistrar,btnprueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //andres

       editNombre=(EditText)findViewById(R.id.txtnombre);
       editApellido=(EditText)findViewById(R.id.txtapellido);
       editUsuario=(EditText)findViewById(R.id.txtUsuario);
       editCorreo=(EditText)findViewById(R.id.txtcorreo);
       editClave=(EditText)findViewById(R.id.txtPass);
       btnRegistrar=(Button)findViewById(R.id.btncontinuar);
       btnprueba = findViewById(R.id.buttonprueba);

        //andres

       /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    Button fab = findViewById(R.id.btncontinuar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                builder.setTitle("¡LISTO!").setIcon(R.drawable.icono);
                builder.setMessage("Tu cuenta ha sido creada sin problemas");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int witch) {
                        onSlideClicked(view);
                    }
                });
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();

            }
        });

        btnprueba.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://clpe5.com/talk/json/personaJson.php");
            }
        });

    }
    public void onSlideClicked(View view){
        transition = new Slide(Gravity.LEFT);
        iniciarActividadSeccundaria();
    }

    @SuppressWarnings("unchecked")
    private void iniciarActividadSeccundaria(){
        transition.setDuration(Duracion_transicion);
        transition.setInterpolator(new DecelerateInterpolator());

        getWindow().setExitTransition(transition);
        //getWindow().setAllowEnterTransitionOverlap(true);
        Intent siguiente  = new Intent(this, com.example.talktime.Inicio.perfil_1.class);
        startActivity(siguiente, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }





    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                builder.setTitle("Respuesta de BD").setIcon(R.drawable.icono);
                if (response.toString()=="1"){
                    builder.setMessage("Usuario Registrado");
                }else{
                    builder.setMessage("Usuario NO Registrado");
                }

                builder.setPositiveButton("OK", null);
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                builder.setTitle("Respuesta de BD").setIcon(R.drawable.icono);
                builder.setMessage("fallo"+error.getMessage());
                builder.setPositiveButton("OK", null);
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("accion","save");
                parametros.put("nombre",editNombre.getText().toString());
                parametros.put("apellidos",editApellido.getText().toString());
                parametros.put("paisActual","unico");
                parametros.put("lenguaMaterna","unico");
                parametros.put("lenguaAprendida","unico");
                parametros.put("ocupacion","unico");
                parametros.put("fechaNace","unico");
                parametros.put("sexo","unico");
                return parametros;
            }
        };


         requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    private void buscarRegistro(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setTitle("Respuesta de BD").setIcon(R.drawable.icono);
                        builder.setMessage("exitosa");
                        builder.setPositiveButton("OK", null);
                        final AlertDialog mDialog = builder.create();
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.show();

                    } catch (JSONException e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setTitle("Respuesta de BD").setIcon(R.drawable.icono);
                        builder.setMessage("fallo en el try catch");
                        builder.setPositiveButton("OK", null);
                        final AlertDialog mDialog = builder.create();
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
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
