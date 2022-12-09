package com.navio.coffeemore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.navio.coffeemore.databinding.ActivityCoffeeCounterBinding;

import java.util.concurrent.TimeUnit;

public class CoffeeCounter extends AppCompatActivity {

    private ActivityCoffeeCounterBinding binding;
    private MyCountDownTimer miContador;
    private MediaPlayer miReproductor;
    private int minutosIniciales = 1;
    private int cuentaInicial = 0;

    private boolean contado = false;

    //Views
    private TextView textoTiempo;
    private TextView textoCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding
        binding = ActivityCoffeeCounterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Initialize views
        textoCuenta = binding.textoCuenta;
        textoCuenta.setText(String.valueOf(cuentaInicial));
        textoTiempo = binding.textoTiempo;
        textoTiempo.setText(String.valueOf(minutosIniciales));
        //Buttons
        binding.botonComenzar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                if (cuentaInicial < 10) {
                    if(!contado){
                        miContador = new MyCountDownTimer(minutosIniciales * 60 * 1000, 1000);
                        miContador.start();
                        contado = true;
                    }
                } else {
                    textoTiempo.setText("Límite alcanzado!");
                }
            }
        });
        binding.botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuentaInicial = 0;
                textoCuenta.setText(String.valueOf(cuentaInicial));
            }
        });
        binding.botonMas.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                minutosIniciales++;
                textoTiempo.setText(String.valueOf(minutosIniciales));
            }
        });
        binding.botonMenos.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (minutosIniciales > 0)
                    minutosIniciales--;

                textoTiempo.setText(String.valueOf(minutosIniciales));
            }
        });
    }



    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textoTiempo.setText(String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
        }

        @Override
        public void onFinish() {
            cuentaInicial++;
            if (cuentaInicial == 10) {
                popUp();
            }
            textoCuenta.setText(String.valueOf(cuentaInicial));
            textoTiempo.setText("Pausa terminada!");
            contado = false;
            play();
        }
    }
    //Reproductor
    public void play() {

        //Crearlo soló una vez...
        if (miReproductor == null) {
            Log.d("Cafe", "Reproduciendo...");
            miReproductor = MediaPlayer.create(this, R.raw.ring);
            miReproductor.start();
            miReproductor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });
        }
    }
    private void pause() {
        if (miReproductor != null) {
            miReproductor.pause();
        }
    }
    private void stop() {
        if (miReproductor != null) {
            miReproductor.release();
            miReproductor = null;
            Log.d("Cafe", "Parando...");
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stop();
    }
    //Alerta
    private void popUp() {

        AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setTitle("Alerta");
        popup.setMessage("Para ya con el café...");
        popup.setPositiveButton("Ok", null);
        popup.show();
    }
}

