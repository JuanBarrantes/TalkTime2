package com.example.talktime.Inicio;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.talktime.R;
import com.example.talktime.Registro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class perfil_2 extends AppCompatActivity {
    private Transition transition;
    Button btnGuardarPerfil;
    private String recuperamos_variable;
    private EditText editDescp1,editDescp2,editDescp3;
    TextView editInvi1,editInvi2;
    RequestQueue requestQueue;
    private String[] myArray = new String[11];
    private String IDpersona,IDcuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btnGuardarPerfil = findViewById(R.id.btnGuardarPerfil);
        setSupportActionBar(toolbar);

        editDescp1=findViewById(R.id.txtdescprimaria);
        editDescp2=findViewById(R.id.txtdescsecundaria);
        editDescp3=findViewById(R.id.txtdescterciaria);
        editInvi1=findViewById(R.id.txtidhidden1);
        editInvi2=findViewById(R.id.txtidhidden2);
        Bundle datos = this.getIntent().getExtras();
        myArray[0] = datos.getString("vaUsuario");
        myArray[1] =datos.getString("vaClave");
        myArray[2] =datos.getString("vaEmail");
        myArray[3] = datos.getString("vaNombre");
        myArray[4] = datos.getString("vaApellido");
        myArray[5] = datos.getString("vaPais");
        myArray[6] = datos.getString("vaSexo");
        myArray[7] = datos.getString("vaFecha");
        myArray[8] = datos.getString("vaIdioInteres");
        myArray[9] = datos.getString("vaIdioMaterno");
        myArray[10] = datos.getString("vaOcupacion");

        Log.i("MyActivity", "actividad : "+myArray[0]);
        Log.i("MyActivity", "actividad : "+myArray[6]);
        Log.i("MyActivity", "actividad : "+myArray[10]);

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
                if ( editDescp1.getText().toString().equals("") || editDescp2.getText().toString().equals("")
                        || editDescp3.getText().toString().equals("") ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(perfil_2.this);
                    builder.setTitle("DATO VACIO!").setIcon(R.drawable.icono);
                    builder.setMessage("tienes que llenar todos los campos");
                    builder.setPositiveButton("OK", null);
                    final AlertDialog mDialog = builder.create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }else {
                    crearRegistro(new Registro.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            editInvi1.setText(result.toString());
                            crearCuenta(new Registro.VolleyCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    editInvi2.setText(result.toString());
                                    crearDetalleUsuario("http://clpe5.com/talk/json/detalleUsuarioJson.php", view);
                                }
                            });
                        }
                    });
                }


               // crearCuenta("http://clpe5.com/talk/json/cuentaJson.php");
                //

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
    private void crearDetalleUsuario(String URL,final View view){
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //ALERTA(va despues del evento guardar)
                AlertDialog.Builder builder = new AlertDialog.Builder(perfil_2.this);
                builder.setTitle("¡LISTO!").setIcon(R.drawable.icono);
                builder.setMessage("Usuario Registrado con èxito");
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(perfil_2.this);
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
                parametros.put("descPrincipal",editDescp1.getText().toString());
                parametros.put("descSecundaria",editDescp2.getText().toString());
                parametros.put("descTerciaria",editDescp3.getText().toString());
                parametros.put("IDcuenta",editInvi2.getText().toString());
                return parametros;
            }
        };


        requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    private void crearRegistro(final Registro.VolleyCallback callback){
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://clpe5.com/talk/json/personaJson.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /* persona.setIdpersona(Integer.parseInt(response.toString()));
                crearRegistro("http://clpe5.com/talk/json/cuentaJson.php");*/

                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(perfil_2.this);
                builder.setTitle("Respuesta de BD").setIcon(R.drawable.icono);
                builder.setMessage("Falló la conexión "+error.getMessage());
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
                parametros.put("nombre",myArray[3]);
                parametros.put("apellidos",myArray[4]);
                parametros.put("paisActual",myArray[5]);
                parametros.put("lenguaMaterna",myArray[9]);
                parametros.put("lenguaAprendida",myArray[8]);
                parametros.put("ocupacion",myArray[10]);
                parametros.put("fechaNace",myArray[7]);
                parametros.put("sexo",myArray[6]);
                return parametros;
            }
        };

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void crearCuenta(final Registro.VolleyCallback callback){
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://clpe5.com/talk/json/cuentaJson.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(perfil_2.this);
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
                parametros.put("usuario",myArray[0]);
                parametros.put("clave",myArray[1]);
                parametros.put("email",myArray[2]);
                parametros.put("foto","");
                parametros.put("IDpersona",editInvi1.getText().toString());
                return parametros;
            }
        };


        requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
}
