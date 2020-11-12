package com.example.registrodeexamenescovid.dto;

import java.io.Serializable;

public class Paciente implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String rut;
    private String nombre;
    private String apellido;
    private String fecha_de_examen;
    private String area_de_trabajo;
    private String sintomas;
    private int temperatura;
    private String presenta_tos;
    private int presion;

    private String foto;

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getPresenta_tos() {
        return presenta_tos;
    }

    public void setPresenta_tos(String presenta_tos) {
        this.presenta_tos = presenta_tos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_de_examen() {
        return fecha_de_examen;
    }

    public void setFecha_de_examen(String fecha_de_examen) {
        this.fecha_de_examen = fecha_de_examen;
    }

    public String getArea_de_trabajo() {
        return area_de_trabajo;
    }

    public void setArea_de_trabajo(String area_de_trabajo) {
        this.area_de_trabajo = area_de_trabajo;
    }





    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }



    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }
}
