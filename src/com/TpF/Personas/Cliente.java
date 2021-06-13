package com.TpF.Personas;

import java.util.Scanner;

public class Cliente extends Persona{

    protected String usuario;
    protected String password;

    public Cliente(){};

    public Cliente(String nombre, String apellido, String DNI){
        super(nombre, apellido, DNI);
    }

    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }

    public void setUsuario(String usuario) { this.usuario = usuario;}
    public void setPassword(String password) { this.password = password;}

    @Override
    public String toString()
    {
        return "Cliente: " +
                "\nUsuario " + usuario + super.toString();

    }

}
