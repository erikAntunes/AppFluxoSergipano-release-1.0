package com.titanz.fluxosergipano.view;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.SaidaAdapter;
import com.titanz.fluxosergipano.models.Saida;

import java.util.ArrayList;


public class Total_Saida_Fragment extends Fragment {

    private RecyclerView recyclerViewSaida;
    private SaidaAdapter saidaAdapter;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_total__saida_,container,false);


        ArrayList<Saida> saidaArrayList = new ArrayList<>();

        Saida saida1 = new Saida();
        saida1.setDescricao("Motoboy");
        saida1.setValor(30.00);
        saida1.setStatus("Saida");
        saidaArrayList.add(saida1);

        Saida saida2 = new Saida();
        saida2.setDescricao("Gasolina");
        saida2.setValor(50.00);
        saida2.setStatus("Saida");
        saidaArrayList.add(saida2);

        Saida saida3 = new Saida();
        saida3.setDescricao("Vale Deninho");
        saida3.setValor(30.00);
        saida3.setStatus("Saida");
        saidaArrayList.add(saida3);

        Saida saida4 = new Saida();
        saida4.setDescricao("Conta/Água");
        saida4.setValor(100.00);
        saida4.setStatus("Saida");
        saidaArrayList.add(saida4);


        saidaAdapter = new SaidaAdapter(saidaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerViewSaida = view.findViewById(R.id.recyclerView_saida_id);

        recyclerViewSaida.setAdapter(saidaAdapter);
        recyclerViewSaida.setLayoutManager(layoutManager);


        return view;
    }

}
