package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Entrada;
import com.titanz.fluxosergipano.models.Saida;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BalancoTotalActivity extends AppCompatActivity {

    private TextView valorEntradaTextView;
    private TextView valorSaidaTextView;
    private TextView valorTotalTextView;
    private ImageView button_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balanco_total);
        getSupportActionBar().hide();

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
}
