package com.example.atividadepam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DadosCad extends AppCompatActivity {

    TextView Nome, Phone, Email;
    Button btnProximo;

    private static final String KEY_NOME = "nome_key";
    private static final String KEY_EMAIL = "email_key";
    private static final String KEY_PHONE = "phone_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cad);

        Nome = (TextView) findViewById(R.id.txtNome);
        Phone = (TextView) findViewById(R.id.txtPhone);
        Email = (TextView) findViewById(R.id.txtEmail);
        btnProximo = (Button) findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            String savedNome = savedInstanceState.getString(KEY_NOME);
            String savedEmail = savedInstanceState.getString(KEY_EMAIL);
            String savedPhone = savedInstanceState.getString(KEY_PHONE);
        }

        Intent it = getIntent();

        if(it != null){
            Bundle params = it.getExtras();
            if(params != null){
                String strNome = params.getString(("Nome"));
                String strEmail = params.getString(("Email"));
                String strPhone = params.getString(("Telefone"));

                Nome.setText(strNome.toString());
                Email.setText(strEmail.toString());
                Phone.setText(strPhone.toString());
            }
        }

        btnProximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent it = new Intent(DadosCad.this, Home.class);
                startActivity(it);
            }
        });
    }
    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_NOME, Nome.getText().toString());
        savedInstanceState.putString(KEY_EMAIL, Phone.getText().toString());
        savedInstanceState.putString(KEY_PHONE, Email.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }
}