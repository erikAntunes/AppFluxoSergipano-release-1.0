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
import com.titanz.fluxosergipano.adapters.SaidaAdapter;
import com.titanz.fluxosergipano.models.Saida;
import java.util.List;


public class Total_Saida_Fragment extends Fragment {

    private RecyclerView recyclerViewSaida;
    private SaidaAdapter saidaAdapter;
    private TextView saidaValorTextView;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_total__saida_,container,false);

        saidaValorTextView = view.findViewById(R.id.saida_valorTotal_TextView_id);

        List<Saida> saidas = MainActivity.saidaDatabase.saidaDao().getSaidas();

        double saidaTotal = 0d;
        for (int i = 0; i < saidas.size(); i++) {
            Saida objSaida = saidas.get(i);

            saidaTotal += objSaida.getValor();

            saidaValorTextView.setText("R$ " + String.valueOf(saidaTotal));


            saidaAdapter = new SaidaAdapter(saidas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewSaida = view.findViewById(R.id.recyclerView_saida_id);
            recyclerViewSaida.setAdapter(saidaAdapter);
            recyclerViewSaida.setLayoutManager(layoutManager);
        }

        return view;
    }

}
