package com.example.registrodeexamenescovid.dao;

import com.example.registrodeexamenescovid.dto.Paciente;

import java.util.List;

public interface PacientesDAO {

    List<Paciente> getAll();
    Paciente save(Paciente p);
}
