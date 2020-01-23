package com.titanz.fluxosergipano.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.titanz.fluxosergipano.models.Entrada;

import java.util.List;

@Dao
public interface EntradaDao {

    @Insert
    public void addEntrada(Entrada entrada);

    @Query("select * from entradas")
    public List<Entrada> getEntradas();

}
