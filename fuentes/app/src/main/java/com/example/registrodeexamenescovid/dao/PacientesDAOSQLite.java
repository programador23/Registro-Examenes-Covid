package com.example.registrodeexamenescovid.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.registrodeexamenescovid.dto.Paciente;
import com.example.registrodeexamenescovid.helpers.PacientesSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class PacientesDAOSQLite implements PacientesDAO {

    private PacientesSQLiteHelper pacieHelper;

    public  PacientesDAOSQLite(Context context){
        this.pacieHelper = new PacientesSQLiteHelper(context,"DBPacientes"
        ,null,3);
    }
    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.pacieHelper.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try {
            if (pacieHelper !=null){
                Cursor c = reader.rawQuery("SELECT id, nombre, apellido, rut, fecha_examen, area_trabajo, sintomas, temperatura, tos, presion, foto"+
                        " FROM pacientes",null);
                if(c.moveToFirst()){
                    do{
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setNombre(c.getString(1));
                        p.setApellido(c.getString(2));
                        p.setRut(c.getString(3));
                        p.setFecha_de_examen(c.getString(4));
                        p.setArea_de_trabajo(c.getString(5));
                        p.setSintomas(c.getString(6));
                        p.setTemperatura(c.getInt(7));
                        p.setPresenta_tos(c.getString(8));
                        p.setPresion(c.getInt(9));
                        p.setFoto(c.getString(10));
                        pacientes.add(p);
                    }while (c.moveToNext());
                }

            }
            pacieHelper.close();
        }catch (Exception ex){
            Log.e("PACIENTESDAO",ex.toString());
            pacientes = null;
        }

    return  pacientes;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public Paciente save(Paciente p) {
        try {


            SQLiteDatabase writer = this.pacieHelper.getWritableDatabase();
            writer.execSQL(String.format("INSERT into pacientes(nombre,apellido,rut,fecha_examen,area_trabajo,sintomas,temperatura,tos,presion,foto)" +
                            " VALUES('%s','%s','%s','%s','%s','%s','%d','%s','%d','%s')",p.getNombre()
                    , p.getApellido(),p.getRut(), p.getFecha_de_examen(),p.getArea_de_trabajo(), p.getSintomas(),p.getTemperatura(), p.getPresenta_tos(),p.getPresion(), p.getFoto()));

            writer.close();

        }catch (Exception ex){
            Log.e("erroresdf",ex.toString());

        }
        return p;
    }
}
