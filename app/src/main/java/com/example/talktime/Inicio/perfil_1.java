package com.example.talktime.Inicio;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.talktime.Beans.Persona;
import com.example.talktime.R;
import com.example.talktime.Registro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import android.os.Parcelable;
import android.provider.MediaStore;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class perfil_1 extends AppCompatActivity {
    private Spinner spPaises,spSexo,spOcupacion;
    RequestQueue requestQueue;

    private ImageButton tomarFoto;
    private ImageView verfoto;
    static final int REQUEST_IMAGE_CAPTURE=1;
    private Button btnSubir;
    private EditText editTextName;
    private Bitmap bitmap;
    private String KEY_IMAGEN = "foto";
    private String KEY_NOMBRE = "nombre";
    private EditText etBirthday,idioMaterno,idioInteres;
    Calendar calendario = Calendar.getInstance();
    private Transition transition;
    private String recuperamos_variable,recuperamos_IDcuenta,recuperamos_nombre,recuperamos_apellido,recuperamos_usuario,recuperamos_clave,recuperamos_correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Slide slide = new Slide(Gravity.RIGHT);
        slide.setDuration(Registro.Duracion_transicion);
        slide.setInterpolator(new DecelerateInterpolator());

        getWindow().setEnterTransition(slide);
        //getWindow().setAllowEnterTransitionOverlap(false);

        setContentView(R.layout.activity_perfil_1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //andres
        spPaises=findViewById(R.id.spinner_paises);
        spSexo=findViewById(R.id.spinner2_sexo);
        spOcupacion=findViewById(R.id.spinner_ocupaciones);
        idioMaterno=findViewById(R.id.editText2);
        idioInteres=findViewById(R.id.editText3);

        //estoy traendo los datos del activiy registro

        //recuperamos_variable = datos.getString("vaIDpersona");
        //recuperamos_IDcuenta=datos.getString("vaIDcuenta");
        //Log.i("MyActivity", "actividad : "+recuperamos_variable);
        //Log.i("MyActivity", "actividadIDcuneta : "+recuperamos_IDcuenta);

        //andres  finish

        etBirthday = findViewById(R.id.cumpleaños);
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(perfil_1.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tomarFoto = findViewById(R.id.btnCamara);
        verfoto = findViewById(R.id.verFoto);

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClicked(view);
            }
        });

        FloatingActionButton fab3 = findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyActivity", "onClick: "+spPaises.getSelectedItem().toString());
                Log.i("MyActivity", "onClick: "+spSexo.getSelectedItem().toString());
                Log.i("MyActivity", "onClick: "+etBirthday.getText().toString());
                //actualizarRegistro("http://clpe5.com/talk/json/personaJson.php", view);
                if (spPaises.getSelectedItem().toString().equals("") || etBirthday.getText().toString().equals("")
                        ||spSexo.getSelectedItem().toString().equals("") ||spOcupacion.getSelectedItem().toString().equals("") ||
                        idioMaterno.getText().toString().equals("")|| idioInteres.getText().toString().equals("") ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(perfil_1.this);
                    builder.setTitle("DATO VACIO!").setIcon(R.drawable.icono);
                    builder.setMessage("tienes que llenar todos los campos");
                    builder.setPositiveButton("OK", null);
                    final AlertDialog mDialog = builder.create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }else {
                    onSlideClicked2(view);
                }

            }
        });

        tomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerImagen();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();

        }
    };

    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        etBirthday.setText(sdf.format(calendario.getTime()));
    }

    public void onSlideClicked2(View view){
        transition = new Slide(Gravity.LEFT);
        iniciarActividadSeccundaria2();
    }

    @SuppressWarnings("unchecked")
    private void iniciarActividadSeccundaria2(){
        transition.setDuration(Registro.Duracion_transicion);
        transition.setInterpolator(new DecelerateInterpolator());

        getWindow().setExitTransition(transition);
        //getWindow().setAllowEnterTransitionOverlap(true);

        Intent siguiente  = new Intent(this, perfil_2.class);
        //siguiente.putExtra("vaIDcuenta", recuperamos_IDcuenta);
        Bundle datos = this.getIntent().getExtras();
        siguiente.putExtra("vaUsuario", datos.getString("vaUsuario"));
        siguiente.putExtra("vaClave",datos.getString("vaClave"));
        siguiente.putExtra("vaEmail",datos.getString("vaEmail"));
        siguiente.putExtra("vaNombre", datos.getString("vaNombre"));
        siguiente.putExtra("vaApellido",datos.getString("vaApellido"));
        siguiente.putExtra("vaPais",spPaises.getSelectedItem().toString());
        siguiente.putExtra("vaSexo",spSexo.getSelectedItem().toString());
        siguiente.putExtra("vaFecha",etBirthday.getText().toString());
        siguiente.putExtra("vaIdioMaterno",idioMaterno.getText().toString());
        siguiente.putExtra("vaIdioInteres",idioInteres.getText().toString());
        siguiente.putExtra("vaOcupacion",spOcupacion.getSelectedItem().toString());
        startActivity(siguiente, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }


    public void onBackClicked(View view){
        transition = new Slide(Gravity.RIGHT);
        transition.setDuration(Registro.Duracion_transicion);
        transition.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(transition);

    }

    private void obtenerImagen(){
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for(ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            cameraIntents.add(intent);
        }

        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        startActivityForResult(chooserIntent, REQUEST_IMAGE_CAPTURE);
    }

    private void uploadImage(String URL){
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        //Toast.makeText(ActividadCamaraReclamos.this, s , Toast.LENGTH_LONG).show();
                        //pasarCapturarUbicacion();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        //Toast.makeText(ActividadCamaraReclamos.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Obtener el nombre de la imagen
                String nombre = editTextName.getText().toString().trim();

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(KEY_IMAGEN, imagen);
                params.put(KEY_NOMBRE, nombre);

                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data.getExtras()!= null) {
                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
                verfoto.setImageBitmap(bitmap);
            } else {
                Uri selectedimg = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg);
                    verfoto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void actualizarRegistro(String URL,final View view){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(perfil_1.this);
                builder.setTitle("¡LISTO!").setIcon(R.drawable.icono);
                builder.setMessage("Usuario Actualizado");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int witch) {
                        onSlideClicked2(view);
                    }
                });
                final AlertDialog mDialog = builder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(perfil_1.this);
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
                parametros.put("accion","update");
                parametros.put("ID",recuperamos_variable);
                parametros.put("lenguaMaterna",idioMaterno.getText().toString());
                parametros.put("lenguaAprendida",idioInteres.getText().toString());
                parametros.put("paisActual",spPaises.getSelectedItem().toString());
                parametros.put("fechaNace",etBirthday.getText().toString());
                parametros.put("sexo",spSexo.getSelectedItem().toString());
                parametros.put("ocupacion",spOcupacion.getSelectedItem().toString());
                return parametros;
            }
        };


        requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
}
