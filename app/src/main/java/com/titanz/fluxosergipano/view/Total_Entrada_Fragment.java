package com.titanz.fluxosergipano.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.EntradaAdapter;
import com.titanz.fluxosergipano.models.Entrada;
import java.util.List;


public class Total_Entrada_Fragment extends Fragment {

    private RecyclerView recyclerViewEntrada;
    private EntradaAdapter entradaAdapter;
    private TextView entradaValorTextView;



    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_total__entrada_, container, false);

        entradaValorTextView = view.findViewById(R.id.entrada_valorTotal_TextView_id);

        List<Entrada> entradas = MainActivity.entradaDatabase.entradaDao().getEntradas();

        double entradaTotal = 0d;
        for (int i = 0; i < entradas.size(); i++) {
            Entrada objEntrada = entradas.get(i);

            entradaTotal += objEntrada.getValor();

            entradaValorTextView.setText("R$ "+String.valueOf(entradaTotal));

            entradaAdapter = new EntradaAdapter(entradas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewEntrada = view.findViewById(R.id.recyclerView_entrada_id);
            recyclerViewEntrada.setAdapter(entradaAdapter);
            recyclerViewEntrada.setLayoutManager(layoutManager);
        }

        return view;
    }
}
