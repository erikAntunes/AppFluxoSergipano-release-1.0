package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.EntradaAdapter;
import com.titanz.fluxosergipano.models.Entrada;
import java.text.DecimalFormat;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EntradaTotalActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEntrada;
    private EntradaAdapter entradaAdapter;
    private TextView entradaValorTextView;
    private ImageView button_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_total);
        getSupportActionBar().hide();

        entradaValorTextView = findViewById(R.id.entrada_valorTotal_TextView_id);

        List<Entrada> entradas = MainActivity.entradaDatabase.entradaDao().getEntradas();

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimaryDark);
        pDialog.setTitleText("Carregando Entradas");

        pDialog.setCancelable(true);

        pDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
            }
        },1100);

        button_voltar = findViewById(R.id.button_voltar_entrada_id);

        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        double entradaTotal = 0d;
        for (int i = 0; i < entradas.size(); i++) {
            Entrada objEntrada = entradas.get(i);
            entradaTotal += objEntrada.getValor();

            DecimalFormat df = new DecimalFormat("##.##");
            entradaValorTextView.setText("R$ "+df.format(entradaTotal));

            entradaAdapter = new EntradaAdapter(entradas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewEntrada = findViewById(R.id.recyclerView_entrada_id);
            recyclerViewEntrada.setAdapter(entradaAdapter);
            recyclerViewEntrada.setLayoutManager(layoutManager);
        }
    }
}
