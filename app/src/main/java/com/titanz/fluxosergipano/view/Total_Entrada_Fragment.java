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
import com.titanz.fluxosergipano.adapters.EntradaAdapter;
import com.titanz.fluxosergipano.models.Entrada;
import java.util.ArrayList;



public class Total_Entrada_Fragment extends Fragment {

    private RecyclerView recyclerViewEntrada;
    private EntradaAdapter entradaAdapter;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_total__entrada_,container,false);


        ArrayList<Entrada> entradaArrayList = new ArrayList<>();

        Entrada entrada1 = new Entrada();
        entrada1.setDescricao("Mussarela");
        entrada1.setValor(28.50);
        entrada1.setStatus("Entrada");
        entradaArrayList.add(entrada1);

        Entrada entrada2 = new Entrada();
        entrada2.setDescricao("Calabresa");
        entrada2.setValor(28.50);
        entrada2.setStatus("Entrada");
        entradaArrayList.add(entrada2);

        Entrada entrada3 = new Entrada();
        entrada3.setDescricao("Portuguesa");
        entrada3.setValor(35.00);
        entrada3.setStatus("Entrada");
        entradaArrayList.add(entrada3);

        Entrada entrada4 = new Entrada();
        entrada4.setDescricao("2 Queijos");
        entrada4.setValor(30.00);
        entrada4.setStatus("Entrada");
        entradaArrayList.add(entrada4);

        Entrada entrada5 = new Entrada();
        entrada5.setDescricao("Italiana");
        entrada5.setValor(37.50);
        entrada5.setStatus("Entrada");
        entradaArrayList.add(entrada5);

        Entrada entrada6 = new Entrada();
        entrada6.setDescricao("Atum");
        entrada6.setValor(32.50);
        entrada6.setStatus("Entrada");
        entradaArrayList.add(entrada6);


        entradaAdapter = new EntradaAdapter(entradaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerViewEntrada = view.findViewById(R.id.recyclerView_entrada_id);

        recyclerViewEntrada.setAdapter(entradaAdapter);
        recyclerViewEntrada.setLayoutManager(layoutManager);

        return view;
    }

}
