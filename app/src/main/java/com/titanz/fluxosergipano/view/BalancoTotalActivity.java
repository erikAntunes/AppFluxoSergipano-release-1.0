package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Entrada;
import com.titanz.fluxosergipano.models.Saida;
import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import github.nisrulz.screenshott.ScreenShott;

public class BalancoTotalActivity extends AppCompatActivity {

    private TextView valorEntradaTextView;
    private TextView valorSaidaTextView;
    private TextView valorTotalTextView;
    private ImageView button_voltar;
    private ImageView button_reset;
    private ImageView scrennShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balanco_total);
        getSupportActionBar().hide();

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},00);

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
        pDialog.setTitleText("Carregando Saídas");

        pDialog.setCancelable(true);

        pDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
            }
        },1100);


        scrennShot = findViewById(R.id.balanco_screenshot);

        button_reset = findViewById(R.id.reset_circleView_id);
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BalancoTotalActivity.this);

                builder.setMessage("Deseja Fazer o Fechamento?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                captureScreen();
                                limparDataBase();
                                irParaBalanco();
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        button_voltar = findViewById(R.id.button_voltar_balanco_id);

        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        valorEntradaTextView = findViewById(R.id.balanco_entrada_textView);
        getEntrada();

        valorSaidaTextView = findViewById(R.id.balanco_saida_textView);
        getSaida();

        valorTotalTextView = findViewById(R.id.balanco_total_textView);
        getTotal();
    }

    public double getEntrada(){

        List<Entrada> entradas = MainActivity.entradaDatabase.entradaDao().getEntradas();
        double entradaTotal = 0d;
        for (int i = 0; i < entradas.size(); i++) {
            Entrada objEntrada = entradas.get(i);

            entradaTotal += objEntrada.getValor();
            valorEntradaTextView.setText("R$ "+String.valueOf(entradaTotal));
        } return entradaTotal;
    }

    public double getSaida (){

        List<Saida> saidas = MainActivity.saidaDatabase.saidaDao().getSaidas();
        double saidaTotal = 0d;
        for (int i = 0; i < saidas.size(); i++) {
            Saida objSaida = saidas.get(i);

            saidaTotal += objSaida.getValor();
            valorSaidaTextView.setText("R$ " + String.valueOf(saidaTotal));
        }
        return saidaTotal;
    }

    public void getTotal(){

        double entradaTotal = getEntrada();
        double saidaTotal = getSaida();
        double balancoTotal = (entradaTotal - saidaTotal);
        valorTotalTextView.setText("R$ "+ String.valueOf(balancoTotal));
    }

    public void irParaBalanco () {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void limparDataBase(){

        MainActivity.entradaDatabase.clearAllTables();
        MainActivity.saidaDatabase.clearAllTables();

        Toast.makeText(getApplicationContext(),"Fechamento enviado para Galeria",Toast.LENGTH_LONG).show();

    }

    private void captureScreen() {

        Bitmap bitmap_rootview = ScreenShott.getInstance().takeScreenShotOfRootView(scrennShot);
        try {
            File file = ScreenShott.getInstance().saveScreenshotToPicturesFolder(getApplicationContext(),bitmap_rootview, "Fechamento");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
