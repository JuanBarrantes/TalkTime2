package com.example.talktime;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.animation.DecelerateInterpolator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    boolean click = false;
    private Transition transition;
    public static final long Duracion_transicion= 1000;
    RequestQueue rq;
    JsonRequest jrq;
    EditText editNombre,editApellido,editCorreo,editUsuario,editClave;
    Button btnRegistrar;

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
        Intent siguiente  = new Intent(this, perfil_1.class);
        startActivity(siguiente, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }




    //andres
    @Override
    public void onErrorResponse(VolleyError error){
        Toast.makeText(getBaseContext(),"No se encontro el usuario"+error.toString(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onResponse(JSONObject response){
        Cuenta cuenta=new Cuenta();
        Toast.makeText(getBaseContext(),"Se encontro el usuario"+editUsuario.toString(),Toast.LENGTH_SHORT).show();
        JSONArray jsonArray=response.optJSONArray("datos");//el json de php es datos.
        JSONObject jsonObject=null;
        try {
            jsonObject =jsonArray.getJSONObject(0);
            cuenta.setUsuario(jsonObject.optString("usuario"));

        }catch (JSONException e){
            e.printStackTrace();
        }


    }
    private void iniciarSesion(){
        String url="http://190.0.3.61/talk/modelo/personaModelo.php?accion=dato&user="+editCorreo.getText().toString()+"&pwd="+editClave.getText().toString();
        jrq=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);
    }
}
