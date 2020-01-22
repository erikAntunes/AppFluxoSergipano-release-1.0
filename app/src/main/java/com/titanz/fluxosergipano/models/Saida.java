package com.titanz.fluxosergipano.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saidas")
public class Saida {

    @NonNull
    @PrimaryKey
    private String data;

    @ColumnInfo(name = "saida_descricao")
    private String descricao;

    @ColumnInfo(name = "saida_valor")
    private double valor;

    private String status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;

    }
}
