package com.example.talktime;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

        FloatingActionButton fab = findViewById(R.id.btncontinuar);
        //fab.setImageResource(R.drawable.ic_agregar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* click = !click;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                            android.R.interpolator.fast_out_slow_in);
                    view.animate()
                            .rotation(click ? 90f : 0)
                            .setInterpolator(interpolador)
                            .start();
                }*/

                onSlideClicked(view);
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
        Intent siguiente  = new Intent(this, Registro_2.class);
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
