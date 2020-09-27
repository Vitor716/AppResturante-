package com.example.atividadepam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home extends AppCompatActivity implements SensorEventListener {

    Button btnHam;
    ImageButton imgBtnConf;
    private Sensor mySensor;
    private SensorManager SM;

    private static final String KEY_MAIS = "mais_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHam = (Button) findViewById(R.id.bntHam);
        imgBtnConf = (ImageButton) findViewById(R.id.imgBtnConf);

        if (savedInstanceState != null) {
            String savedEmail = savedInstanceState.getString(KEY_MAIS);
        }


        btnHam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent it = new Intent(Home.this, hamb.class);
                startActivity(it);
            }
        });

        imgBtnConf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent it = new Intent(Home.this, Config.class);
                startActivity(it);
            }
        });


        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor = SM.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_MAIS, btnHam.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.values[0] >= 5 || event.values[1] >= 5 || event.values[0] <= -5 || event.values[1] <= -5) {

            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Não peça comida enquanto estiver dirigindo");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}