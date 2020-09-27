package com.example.atividadepam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaCadastro extends AppCompatActivity {

    EditText Nome, Phone, Email;
    Button btnCad;

    private static final String KEY_NOME = "Nome_key";
    private static final String KEY_PHONE = "Phone_key";
    private static final String KEY_EMAIL = "Email_key";


    private Boolean validacaoNome() {
        String val = Nome.getText().toString();

        if (val.isEmpty()) {
            Nome.setError("Campo vazio");
            return false;
        } else {
            Nome.setError(null);
            return true;
        }
    }

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

    private Boolean validacaoNumero() {
        String val = Phone.getText().toString();

        if (val.isEmpty()) {
            Phone.setError("Campo vazio");
            return false;
        } else if(val.length() < 8){
            Phone.setError("O número tem que ser maior");
            return false;
        }
        else{
            Phone.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        if (savedInstanceState != null) {
            String savedEmail = savedInstanceState.getString(KEY_EMAIL);
            String savedPhone = savedInstanceState.getString(KEY_PHONE);
            String savedNome = savedInstanceState.getString(KEY_NOME);
        }

        Nome = (EditText) findViewById(R.id.txtNome);
        Phone = (EditText) findViewById(R.id.txtPhone);
        Email = (EditText) findViewById(R.id.txtEmail);
        btnCad = (Button) findViewById(R.id.btnCad);


        btnCad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(!validacaoEmail() |!validacaoNome() |!validacaoNumero()){
                    return;
                }

                Intent dados = new Intent(TelaCadastro.this, DadosCad.class);

                Bundle params = new Bundle();

                params.putString("Nome", Nome.getText().toString());
                params.putString("Email", Email.getText().toString());
                params.putString("Telefone", Phone.getText().toString());

                dados.putExtras(params);

                startActivity(dados);
            }
        });
    }
    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_PHONE, Phone.getText().toString());
        savedInstanceState.putString(KEY_EMAIL, Email.getText().toString());
        savedInstanceState.putString(KEY_NOME, Nome.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
}
}