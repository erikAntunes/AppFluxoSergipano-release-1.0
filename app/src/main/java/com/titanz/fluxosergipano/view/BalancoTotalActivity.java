package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Entrada;
import com.titanz.fluxosergipano.models.Saida;
import java.util.List;

public class BalancoTotalActivity extends AppCompatActivity {

    private TextView valorEntradaTextView;
    private TextView valorSaidaTextView;
    private TextView valorTotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balanco_total);

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
