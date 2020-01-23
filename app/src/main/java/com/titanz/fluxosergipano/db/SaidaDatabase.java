package com.titanz.fluxosergipano.db;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.titanz.fluxosergipano.models.Saida;

@Database(entities = {Saida.class}, version = 1)
public abstract class SaidaDatabase extends RoomDatabase {

    public abstract SaidaDao saidaDao();
}
