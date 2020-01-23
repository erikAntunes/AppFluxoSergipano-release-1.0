package com.titanz.fluxosergipano.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Entrada;
import com.titanz.fluxosergipano.models.Saida;

import java.util.List;


public class Total_Final_Fragment extends Fragment {

    private TextView valorEntradaTextView;
    private TextView valorSaidaTextView;
    private TextView valorTotalTextView;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_total__final_,container,false);

        valorEntradaTextView = view.findViewById(R.id.balanco_entrada_textView);

        List<Entrada> entradas = MainActivity.entradaDatabase.entradaDao().getEntradas();
        double entradaTotal = 0d;
        for (int i = 0; i < entradas.size(); i++) {
            Entrada objEntrada = entradas.get(i);

            entradaTotal += objEntrada.getValor();

            valorEntradaTextView.setText("R$ "+String.valueOf(entradaTotal));
        }

        valorSaidaTextView = view.findViewById(R.id.balanco_saida_textView);

        List<Saida> saidas = MainActivity.saidaDatabase.saidaDao().getSaidas();
        double saidaTotal = 0d;
        for (int i = 0; i < saidas.size(); i++) {
            Saida objSaida = saidas.get(i);

            saidaTotal += objSaida.getValor();

            valorSaidaTextView.setText("R$ " + String.valueOf(saidaTotal));
        }

        valorTotalTextView = view.findViewById(R.id.balanco_total_textView);

        double balancoTotal = (entradaTotal - saidaTotal);

        valorTotalTextView.setText("R$ "+ String.valueOf(balancoTotal));

        return view;
    }

}
