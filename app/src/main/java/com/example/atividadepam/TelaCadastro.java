package com.example.atividadepam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} , 1000);
        }

        Nome = (EditText) findViewById(R.id.txtNome);
        Phone = (EditText) findViewById(R.id.txtPhone);
        Email = (EditText) findViewById(R.id.txtEmail);
        btnCad = (Button) findViewById(R.id.btnCad);


        btnCad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String salvaNome = Nome.getText().toString();
                salvarAquivo(salvaNome);

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case 1000:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permissão Concedida", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"Permissão negada", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void salvarAquivo(String conteudoArquivo){
        File folder = new File (Environment.getExternalStorageDirectory()+ "/PASTA");
        if(folder.exists()){
            folder.mkdir();
        }

        File arquivo = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/PASTA/" +Nome);
        try{
            FileOutputStream salvar = new FileOutputStream(arquivo);
            salvar.write(conteudoArquivo.getBytes());
            salvar.close();
            Toast.makeText(this, "Arquivo salvo", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Arquivo salvo", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_PHONE, Phone.getText().toString());
        savedInstanceState.putString(KEY_EMAIL, Email.getText().toString());
        savedInstanceState.putString(KEY_NOME, Nome.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
}
}