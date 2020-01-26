package com.titanz.fluxosergipano.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.titanz.fluxosergipano.models.Saida;

import java.util.List;

@Dao
public interface SaidaDao {

    @Insert
    public void addSaida(Saida saida);

    @Query("select * from saidas")
    public List<Saida> getSaidas();

    @Delete
    public void deleteSaida(Saida saida);

}
