package com.example.atividadepam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Email, Senha;
    Button btnNewCad, bntLogin, btnGravar, btnRecuperar;
    private PessoaDAO dao;

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
        } else {
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

        dao = new PessoaDAO(this);

        bntLogin = (Button) findViewById(R.id.bntLogin);
        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa a = new Pessoa();
                a.setEmail(Email.getText().toString());
                a.setSenha(Senha.getText().toString());
                long id = dao.inserir(a);

                if (!validacaoEmail() | !validacaoSenha()) {
                    return;
                }
                Intent it = new Intent(MainActivity.this, Home.class);
                startActivity(it);

            }
        });


        btnRecuperar = (Button) findViewById(R.id.bntRecuperar);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("preferencias",
                        Context.MODE_PRIVATE);
                Email.setText(prefs.getString("Email", "não encontrado"));
                Senha.setText(prefs.getString("Senha", "não encontrado"));
            }
        });

        btnGravar = (Button) findViewById(R.id.bntGravar);
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("preferencias",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("Email", Email.getText().toString());
                ed.putString("Senha", Senha.getText().toString());
                ed.apply();
                Toast.makeText(getBaseContext(), "Gravado com sucesso",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnNewCad = (Button) findViewById(R.id.btnNewCad);
        btnNewCad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, TelaCadastro.class);
                startActivity(it);
            }
        });

    }

}