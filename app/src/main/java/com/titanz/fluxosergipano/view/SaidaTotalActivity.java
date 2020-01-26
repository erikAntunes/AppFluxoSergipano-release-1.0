package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.SaidaAdapter;
import com.titanz.fluxosergipano.models.Saida;
import com.titanz.fluxosergipano.models.SaidaListener;

import java.text.DecimalFormat;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SaidaTotalActivity extends AppCompatActivity implements SaidaListener {

    private RecyclerView recyclerViewSaida;
    private SaidaAdapter saidaAdapter;
    private TextView saidaValorTextView;
    private ImageView button_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saida_total);
        getSupportActionBar().hide();

        saidaValorTextView = findViewById(R.id.saida_valorTotal_TextView_id);

        List<Saida> saidas = MainActivity.saidaDatabase.saidaDao().getSaidas();

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

        button_voltar = findViewById(R.id.button_voltar_saida_id);

        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        double saidaTotal = 0d;
        for (int i = 0; i < saidas.size(); i++) {
            Saida objSaida = saidas.get(i);
            saidaTotal += objSaida.getValor();

            DecimalFormat df = new DecimalFormat("##.##");
            saidaValorTextView.setText("R$ "+df.format(saidaTotal));

            saidaAdapter = new SaidaAdapter(this,saidas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewSaida = findViewById(R.id.recyclerView_saida_id);
            recyclerViewSaida.setAdapter(saidaAdapter);
            recyclerViewSaida.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onSaidaClicada(final Saida saida) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SaidaTotalActivity.this);

        builder.setMessage("Deseja Excluir Saída?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.saidaDatabase.saidaDao().deleteSaida(saida);
                        Toast.makeText(getApplicationContext(),"Saída Excluida com Sucesso",Toast.LENGTH_SHORT);
                        irParaMain();
                        irParaSaidaTotal();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));

    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    private void irParaMain() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void irParaSaidaTotal(){

        Intent intent = new Intent(getApplicationContext(), SaidaTotalActivity.class);
        startActivity(intent);

    }
}
