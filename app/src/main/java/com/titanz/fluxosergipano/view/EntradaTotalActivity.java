package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.EntradaAdapter;
import com.titanz.fluxosergipano.models.Entrada;
import java.util.List;

public class EntradaTotalActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEntrada;
    private EntradaAdapter entradaAdapter;
    private TextView entradaValorTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_total);
        getSupportActionBar().hide();

        entradaValorTextView = findViewById(R.id.entrada_valorTotal_TextView_id);

        List<Entrada> entradas = MainActivity.entradaDatabase.entradaDao().getEntradas();

        double entradaTotal = 0d;
        for (int i = 0; i < entradas.size(); i++) {
            Entrada objEntrada = entradas.get(i);

            entradaTotal += objEntrada.getValor();

            entradaValorTextView.setText("R$ "+String.valueOf(entradaTotal));

            entradaAdapter = new EntradaAdapter(entradas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewEntrada = findViewById(R.id.recyclerView_entrada_id);
            recyclerViewEntrada.setAdapter(entradaAdapter);
            recyclerViewEntrada.setLayoutManager(layoutManager);
        }
    }
}
