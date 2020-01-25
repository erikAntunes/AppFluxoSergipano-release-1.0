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
import com.titanz.fluxosergipano.models.Saida;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class SaidaFragment extends Fragment {

    private TextInputEditText descricaoSaidaTextInput;
    private  TextInputEditText valorSaidaTextInput;
    private Button addSaidaButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saida,container,false);

        descricaoSaidaTextInput = view.findViewById(R.id.saida_descricao_TextInput_id);
        valorSaidaTextInput = view.findViewById(R.id.saida_valor_TextInput_id);
        addSaidaButton = view.findViewById(R.id.button_add_saida_id);
        addSaidaButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                registrarSaida();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void registrarSaida(){

        if (Objects.requireNonNull(descricaoSaidaTextInput.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            descricaoSaidaTextInput.setError("Digite uma Descrição");

        }
        if (Objects.requireNonNull(valorSaidaTextInput.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            valorSaidaTextInput.setError("Digite um Valor");

        } else{

            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy/HH:mm:ss");

            Date data = new Date();

            String dataFormatada = formataData.format(data);

            String saidaDescricao = descricaoSaidaTextInput.getText().toString();
            Double saidaValor = Double.parseDouble(String.valueOf(valorSaidaTextInput.getText()));

            Saida saida = new Saida();
            saida.setData(dataFormatada);
            saida.setDescricao(saidaDescricao);
            saida.setValor(saidaValor);
            saida.setStatus("Saída");

            MainActivity.saidaDatabase.saidaDao().addSaida(saida);
            Toast.makeText(getActivity(),"Saída Registrada Com Sucesso",Toast.LENGTH_SHORT).show();

            descricaoSaidaTextInput.setText("");
            valorSaidaTextInput.setText("");
        }
    }
}
