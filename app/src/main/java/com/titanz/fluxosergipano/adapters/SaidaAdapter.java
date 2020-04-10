package com.titanz.fluxosergipano.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.titanz.fluxosergipano.R;
import com.titanz.fluxosergipano.models.Saida;
import com.titanz.fluxosergipano.models.SaidaListener;

import java.util.ArrayList;
import java.util.List;

public class SaidaAdapter extends RecyclerView.Adapter<SaidaAdapter.SaidaViewHolder> {

    private List<Saida> listaSaida;
    private SaidaListener saidaListener;

    public SaidaAdapter(SaidaListener saidaListener,List<Saida> listaSaida) {

        this.saidaListener = saidaListener;
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
    public void onBindViewHolder(@NonNull SaidaViewHolder saidaViewHolder, final int index) {
        final  Saida saida = listaSaida.get(index);

        saidaViewHolder.setupSaida(saida);
        saidaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saidaListener.onSaidaClicada(listaSaida.get(index));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaSaida.size();
    }

    public void filterList(ArrayList<Saida> filteredList){

        listaSaida = filteredList;
        notifyDataSetChanged();
    }

    public class SaidaViewHolder extends RecyclerView.ViewHolder {

        private TextView getTextView_pedido_data;
        private TextView textView_pedido_descricao;
        private TextView textView_pedido_valor;
        private TextView textView_pedido_status;


        public SaidaViewHolder(@NonNull View itemView) {
            super(itemView);

            getTextView_pedido_data = itemView.findViewById(R.id.pedido_data_id);
            textView_pedido_descricao = itemView.findViewById(R.id.pedido_descricao_id);
            textView_pedido_valor = itemView.findViewById(R.id.pedido_valor_id);
            textView_pedido_status = itemView.findViewById(R.id.pedido_status_id);


        }

        public void setupSaida(Saida saida) {

            getTextView_pedido_data.setText(saida.getData());
            textView_pedido_descricao.setText(saida.getDescricao());
            textView_pedido_valor.setText("R$ "+String.valueOf(saida.getValor()));
            textView_pedido_status.setText(saida.getStatus());

        }
    }
}
