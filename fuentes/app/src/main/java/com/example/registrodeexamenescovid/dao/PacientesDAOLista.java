package com.example.registrodeexamenescovid.dao;

import androidx.annotation.DrawableRes;

import com.example.registrodeexamenescovid.R;
import com.example.registrodeexamenescovid.dto.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacientesDAOLista implements PacientesDAO {

    private List<Paciente>pacientes=new ArrayList<>();
    private static PacientesDAOLista instancia;

    private PacientesDAOLista(){
    Paciente p = new Paciente();
    p.setRut("19974303-1");
    p.setNombre("Christian");
    p.setApellido("Angulo");
    p.setFecha_de_examen("01-11-2020");
    p.setArea_de_trabajo("Atencion a publico");
    p.setSintomas("si");
    p.setTemperatura(29);
    p.setPresenta_tos("si");
    p.setPresion(120);

    p.setFoto("https://image.flaticon.com/icons/png/512/2659/2659980.png");
    pacientes.add(p);
   }

   public static PacientesDAOLista getInstance(){
        if(instancia==null){
            instancia= new PacientesDAOLista();
        }
        return instancia;
   }

    @Override
    public List<Paciente> getAll() {
        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
        pacientes.add(p);
        return p;
    }
}
