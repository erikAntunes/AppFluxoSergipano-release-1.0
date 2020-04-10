package com.titanz.fluxosergipano.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.adapters.EntradaAdapter;
import com.titanz.fluxosergipano.models.Entrada;
import com.titanz.fluxosergipano.models.EntradaListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EntradaTotalActivity extends AppCompatActivity implements EntradaListener {

    private RecyclerView recyclerViewEntrada;
    private EntradaAdapter entradaAdapter;
    private TextView entradaValorTextView;
    private ImageView button_voltar;
    private EditText entradaSearchTextInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_total);
        getSupportActionBar().hide();

        entradaValorTextView = findViewById(R.id.entrada_valorTotal_TextView_id);


        entradaSearchTextInput = findViewById(R.id.entrada_search_id);
        entradaSearchTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());

            }
        });

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
                irParaMain();
            }
        });

        double entradaTotal = 0d;
        for (int i = 0; i < entradas.size(); i++) {
            Entrada objEntrada = entradas.get(i);
            entradaTotal += objEntrada.getValor();

            DecimalFormat df = new DecimalFormat("##.##");
            entradaValorTextView.setText("R$ "+df.format(entradaTotal));

            entradaAdapter = new EntradaAdapter(this,entradas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewEntrada = findViewById(R.id.recyclerView_entrada_id);
            recyclerViewEntrada.setAdapter(entradaAdapter);
            recyclerViewEntrada.setLayoutManager(layoutManager);
        }
    }

    private void filter(String text) {
        ArrayList<Entrada> filteredList = new ArrayList<>();

        List<Entrada> entradas = MainActivity.entradaDatabase.entradaDao().getEntradas();

        for (Entrada entrada : entradas) {
            if(entrada.getData().substring(0,2).toLowerCase().contains(text.toLowerCase())){

                filteredList.add(entrada);

            }
        }
        double entradaTotal = 0d;
        for (int i = 0; i < filteredList.size(); i++) {
            Entrada objEntrada = filteredList.get(i);
            entradaTotal += objEntrada.getValor();

            DecimalFormat df = new DecimalFormat("##.##");
            entradaValorTextView.setText("R$ "+df.format(entradaTotal));
        }

        entradaAdapter.filterList(filteredList);
    }

    @Override
    public void onEntradaClicada(final Entrada entrada) {

        AlertDialog.Builder builder = new AlertDialog.Builder(EntradaTotalActivity.this);

        builder.setMessage("Deseja Excluir Entrada?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.entradaDatabase.entradaDao().deleteEntrada(entrada);
                        Toast.makeText(getApplicationContext(),"Entrada Excluida com Sucesso",Toast.LENGTH_SHORT);
                        irParaEntradaTotal();
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

    public void irParaEntradaTotal(){

        Intent intent = new Intent(getApplicationContext(), EntradaTotalActivity.class);
        startActivity(intent);

    }

    public void irParaMain(){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
