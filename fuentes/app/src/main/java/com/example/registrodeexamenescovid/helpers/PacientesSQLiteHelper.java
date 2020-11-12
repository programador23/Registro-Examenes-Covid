package com.example.registrodeexamenescovid.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {

    private final String sqlCreate = "CREATE TABLE pacientes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"+
            ",nombre TEXT"+
            ",apellido TEXT"+
            ",rut TEXT"+
            ",fecha_examen TEXT"+
            ",area_trabajo TEXT"+
            ",sintomas TEXT"+
            ",temperatura INTEGER"+
            ",tos TEXT"+
            ",presion INTEGER"+
            ",foto TEXT)";

    public PacientesSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL("INSERT INTO pacientes (nombre,apellido,rut,fecha_examen,area_trabajo,sintomas,temperatura,tos,presion,foto)"+
                " VALUES('Gonzalo','Diaz','9276594-1','04-11-2020','otro','si',30,'si',104"+
                ",'https://image.flaticon.com/icons/png/512/2659/2659980.png')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
    sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL("INSERT INTO pacientes (nombre,apellido,rut,fecha_examen,area_trabajo,sintomas,temperatura,tos,presion)"+
                " VALUES('Gonzalo','Diaz','9276594-1','04-11-2020','otro','si',30,'si',104"+
                ",'https://image.flaticon.com/icons/png/512/2659/2659980.png')");
    }
}
