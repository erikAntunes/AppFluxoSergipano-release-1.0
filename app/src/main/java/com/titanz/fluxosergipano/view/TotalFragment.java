package com.titanz.fluxosergipano.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.titanz.fluxosergipano.R;


public class TotalFragment extends Fragment {

    Button buttonEntrada;
    Button buttonSaida;
    Button buttonBalanco;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total,container,false);

        buttonEntrada = view.findViewById(R.id.button_entradaTotal_id);
        buttonEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EntradaTotalActivity.class);
                startActivity(intent);
            }
        });
        buttonSaida = view.findViewById(R.id.button_saidaTotal_id);
        buttonSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SaidaTotalActivity.class);
                startActivity(intent);
            }
        });

        buttonBalanco = view.findViewById(R.id.button_balancoTotal_id);
        buttonBalanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BalancoTotalActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }

}
