package com.TpF.Personas;

import java.util.Scanner;

public class Cliente extends Persona{

    private static int id=0;
    private int propioId;
    protected String usuario;
    protected String password;

    public Cliente(){};

    public Cliente(String nombre, String apellido, String DNI){
        super(nombre, apellido, DNI);
        this.propioId = crearNuevoId();
    }


    public int crearNuevoId(){
        return ++id;
    }

    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }

    public void setUsuario(String usuario) { this.usuario = usuario;}
    public void setPassword(String password) { this.password = password;}

    @Override
    public String toString()
    {
        return "Cliente: " +
                "\nID: " + propioId + super.toString();

    }

}
