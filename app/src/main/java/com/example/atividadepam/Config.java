package com.example.atividadepam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class Config extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    ImageButton imgBtnContato;
    TextView TxtContato;

    Button btnMap;
    TextView txtAdress;

    private static final String KEY_CONTATO = "contato_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        btnMap = (Button) findViewById(R.id.btnMap);

        if (savedInstanceState != null) {
            String savedNome = savedInstanceState.getString(KEY_CONTATO);
        }

        imgBtnContato = (ImageButton) findViewById(R.id.imgBtnContato);
        TxtContato = (TextView) findViewById(R.id.txtContato);

        imgBtnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ligar();
            }
        });
    }


    public void Ligar(){
        if (ContextCompat.checkSelfPermission(Config.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Config.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:11994635595")));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Ligar();
            }
            else{
                Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_CONTATO, TxtContato.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }
}