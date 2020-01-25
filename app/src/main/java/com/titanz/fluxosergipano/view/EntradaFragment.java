package com.titanz.fluxosergipano.view;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.titanz.fluxosergipano.MainActivity;
import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Entrada;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class EntradaFragment extends Fragment {

    private TextInputEditText descricaoEntradaTextInput;
    private  TextInputEditText valorEntradaTextInput;
    private Button addEntradaButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entrada,container,false);

        descricaoEntradaTextInput = view.findViewById(R.id.entrada_descricao_TextInput_id);
        valorEntradaTextInput = view.findViewById(R.id.entrada_valor_TextInput_id);
        addEntradaButton = view.findViewById(R.id.button_add_entrada_id);
        addEntradaButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                registrarEntrada();
            }
        });


        return view;

    } @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public void registrarEntrada() {

        if (Objects.requireNonNull(descricaoEntradaTextInput.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            descricaoEntradaTextInput.setError("Digite uma Descrição");

        }
        if (Objects.requireNonNull(valorEntradaTextInput.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            valorEntradaTextInput.setError("Digite um Valor");

        } else {
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy/HH:mm:ss");

            Date data = new Date();

            String dataFormatada = formataData.format(data);

            String entradaDescricao = descricaoEntradaTextInput.getText().toString();
            Double entradaValor = Double.parseDouble(String.valueOf(valorEntradaTextInput.getText()));

            Entrada entrada = new Entrada();
            entrada.setData(dataFormatada);
            entrada.setDescricao(entradaDescricao);
            entrada.setValor(entradaValor);
            entrada.setStatus("Entrada");

            MainActivity.entradaDatabase.entradaDao().addEntrada(entrada);
            Toast.makeText(getActivity(),"Entrada Registrada Com Sucesso",Toast.LENGTH_SHORT).show();

            descricaoEntradaTextInput.setText("");
            valorEntradaTextInput.setText("");
        }
    }

}
