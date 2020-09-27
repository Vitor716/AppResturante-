package com.example.atividadepam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText  Email, Senha;
    Button btnNewCad, bntLogin;

    private static final String KEY_EMAIL = "email_key";
    private static final String KEY_SENHA = "senha_key";

    private Boolean validacaoEmail() {
        String val = Email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            Email.setError("Campo vazio");
            return false;
        } else if (!val.matches((emailPattern))) {
            Email.setError("Email invalido");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }
    private Boolean validacaoSenha() {
        String val = Senha.getText().toString();

        if (val.isEmpty()) {
            Senha.setError("Campo vazio");
            return false;
        }
        else{
            Senha.setError(null);
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String savedEmail = savedInstanceState.getString(KEY_EMAIL);
            String savedSenha = savedInstanceState.getString(KEY_SENHA);
        }


        Email = (EditText) findViewById(R.id.txtEmail);
        Senha = (EditText) findViewById(R.id.txtSenha);
        btnNewCad = (Button) findViewById(R.id.btnNewCad);
        bntLogin = (Button) findViewById(R.id.bntLogin);

        btnNewCad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this, TelaCadastro.class);
                startActivity(it);
            }
        });

        bntLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(!validacaoEmail()| !validacaoSenha()){
                    return;
                }
                Intent it = new Intent(MainActivity.this, Home.class);
                startActivity(it);
            }
        });
    }
    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_SENHA, Senha.getText().toString());
        savedInstanceState.putString(KEY_EMAIL, Email.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }
}