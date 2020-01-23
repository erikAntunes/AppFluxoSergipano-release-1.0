package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.SaidaAdapter;
import com.titanz.fluxosergipano.models.Saida;
import java.util.List;

public class SaidaTotalActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSaida;
    private SaidaAdapter saidaAdapter;
    private TextView saidaValorTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saida_total);
        getSupportActionBar().hide();

        saidaValorTextView = findViewById(R.id.saida_valorTotal_TextView_id);

        List<Saida> saidas = MainActivity.saidaDatabase.saidaDao().getSaidas();

        double saidaTotal = 0d;
        for (int i = 0; i < saidas.size(); i++) {
            Saida objSaida = saidas.get(i);

            saidaTotal += objSaida.getValor();

            saidaValorTextView.setText("R$ " + String.valueOf(saidaTotal));


            saidaAdapter = new SaidaAdapter(saidas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewSaida = findViewById(R.id.recyclerView_saida_id);
            recyclerViewSaida.setAdapter(saidaAdapter);
            recyclerViewSaida.setLayoutManager(layoutManager);
        }
    }
}