package com.titanz.fluxosergipano.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.titanz.fluxosergipano.models.Entrada;

@Database(entities = {Entrada.class}, version = 1)
public abstract class EntradaDatabase extends RoomDatabase {

    public abstract EntradaDao entradaDao();
}
