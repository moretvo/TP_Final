package com.TpF.Personas;

public class Persona {

    protected String nombre;
    protected String apellido;
    protected String DNI;

    public Persona(){};

    public Persona(String nombre, String apellido, String DNI)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public String toString()
    {
        return  "\nNombre: " + apellido + ", " + nombre +
                "\nDNI: " + DNI;

    }

}
