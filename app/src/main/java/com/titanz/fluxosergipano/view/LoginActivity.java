package com.titanz.fluxosergipano.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText usuarioEditText;
    TextInputEditText senhaEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
        pDialog.setTitleText("Boas Vendas");

        pDialog.setCancelable(true);

        pDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
            }
        },1100);

        usuarioEditText = findViewById(R.id.login_usuario_TextInput_id);
        senhaEditText = findViewById(R.id.login_senha_TextInput_id);
        loginButton = findViewById(R.id.button_login_id);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Logar();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Logar(){

        if (Objects.requireNonNull(usuarioEditText.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            usuarioEditText.setError("Digite um Usuário");

        }
        if (Objects.requireNonNull(senhaEditText.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            senhaEditText.setError("Digite uma Senha");

        } if (usuarioEditText.getText().toString().equals("deninho.sergipano")&& senhaEditText.getText().toString().equals("123456")) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
            else{
                 usuarioEditText.setError("E-mail e Senha não correspondem");
                 senhaEditText.setError("E-mail e Senha não correspondem");
                }
            }
    }
