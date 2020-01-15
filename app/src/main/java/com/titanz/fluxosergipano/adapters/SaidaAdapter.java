package com.titanz.fluxosergipano.adapters;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SaidaAdapter extends RecyclerView.Adapter<SaidaAdapter.SaidaViewHolder> {
    @NonNull
    @Override
    public SaidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SaidaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SaidaViewHolder extends RecyclerView.ViewHolder {
        public SaidaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
