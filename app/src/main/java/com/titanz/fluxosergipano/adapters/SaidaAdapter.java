package com.titanz.fluxosergipano.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Saida;

import java.util.List;

public class SaidaAdapter extends RecyclerView.Adapter<SaidaAdapter.SaidaViewHolder> {

    private List<Saida> listaSaida;

    public SaidaAdapter(List<Saida> listaSaida) {


        this.listaSaida = listaSaida;
    }

    @NonNull
    @Override
    public SaidaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_pedido,viewGroup, false);

        SaidaAdapter.SaidaViewHolder saidaViewHolder = new SaidaViewHolder(view);

        return saidaViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SaidaViewHolder saidaViewHolder, int index) {
        final  Saida saida = listaSaida.get(index);

        saidaViewHolder.setupSaida(saida);

    }

    @Override
    public int getItemCount() {
        return listaSaida.size();
    }

    public class SaidaViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_pedido_descricao;
        private TextView textView_pedido_valor;
        private TextView textView_pedido_status;


        public SaidaViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_pedido_descricao = itemView.findViewById(R.id.pedido_descricao_id);
            textView_pedido_valor = itemView.findViewById(R.id.pedido_valor_id);
            textView_pedido_status = itemView.findViewById(R.id.pedido_status_id);


        }

        public void setupSaida(Saida saida) {

            textView_pedido_descricao.setText(saida.getDescricao());
            textView_pedido_valor.setText(String.valueOf(saida.getValor()));
            textView_pedido_status.setText(saida.getStatus());

        }
    }
}
