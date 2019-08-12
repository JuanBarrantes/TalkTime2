package com.example.talktime.Chat.FireBase.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.talktime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_FireBase extends AppCompatActivity {
   private EditText txtCorreo,txtContraseña;
    private Button btnLogin,btnRegistro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__fire_base);
        txtCorreo = (EditText) findViewById(R.id.txtUsuarioFB);
        txtContraseña = (EditText) findViewById(R.id.txtPassFB);
        btnLogin = (Button) findViewById(R.id.BtnIninciarFB);
        btnRegistro = (Button) findViewById(R.id.btnRegistroFB);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = txtCorreo.getText().toString();
                if(isValidEmail(correo) && validarContraseña()){
                    String contraseña = txtContraseña.getText().toString();
                    mAuth.signInWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(Login_FireBase.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Login_FireBase.this, "Se logeo correctamente.", Toast.LENGTH_SHORT).show();
                                        nextActivity();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login_FireBase.this, "Error, credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }else{
                    Toast.makeText(Login_FireBase.this, "Validaciones funcionando.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_FireBase.this, Registro_Fire_Base.class));
            }
        });
        //UsuarioDAO.getInstancia().añadirFotoDePerfilALosUsuariosQueNoTienenFoto();

    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validarContraseña(){
        String contraseña;
        contraseña = txtContraseña.getText().toString();
        if(contraseña.length()>=6 && contraseña.length()<=16){
            return true;
        }else return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(this, "Usuario logeado.", Toast.LENGTH_SHORT).show();
            nextActivity();
        }
    }

    private void nextActivity(){
        startActivity(new Intent(Login_FireBase.this,inicio.class));
        finish();
    }


}