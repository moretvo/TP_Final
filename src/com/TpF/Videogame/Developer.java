package com.TpF.Videogame;

public class Developer {
    private String nombre;
    private String location;

    public Developer(){};

    public Developer(String nombre, String location)
    {
        this.nombre = nombre;
        this.location = location;
    }

    @Override
    public String toString(){
        return nombre + ", de " + location;
    }

}
