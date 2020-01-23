package com.titanz.fluxosergipano.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Entrada;

import java.util.List;

public class EntradaAdapter extends RecyclerView.Adapter<EntradaAdapter.EntradaViewHolder> {

    private List<Entrada> listaEntrada;

    public EntradaAdapter(List<Entrada> listaEntrada) {


        this.listaEntrada = listaEntrada;
    }

    @NonNull
    @Override
    public EntradaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_pedido,viewGroup, false);

        EntradaViewHolder entradaViewHolder = new EntradaViewHolder(view);

        return entradaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntradaViewHolder entradaViewHolder, int index) {
        final Entrada entrada = listaEntrada.get(index);

        entradaViewHolder.setupEntrada(entrada);

    }


    @Override
    public int getItemCount() {
        return listaEntrada.size();
    }



    public class EntradaViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_pedido_data;
        private TextView textView_pedido_descricao;
        private TextView textView_pedido_valor;
        private TextView textView_pedido_status;


        public EntradaViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_pedido_data = itemView.findViewById(R.id.pedido_data_id);
            textView_pedido_descricao = itemView.findViewById(R.id.pedido_descricao_id);
            textView_pedido_valor = itemView.findViewById(R.id.pedido_valor_id);
            textView_pedido_status = itemView.findViewById(R.id.pedido_status_id);



        }

        public void setupEntrada(Entrada entrada){

            textView_pedido_data.setText(entrada.getData());
            textView_pedido_descricao.setText(entrada.getDescricao());
            textView_pedido_valor.setText("R$ "+String.valueOf(entrada.getValor()));
            textView_pedido_status.setText(entrada.getStatus());
        }
    }
}
