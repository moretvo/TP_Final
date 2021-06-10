package com.TpF.Personas;

public class Cliente extends Persona {

    private static int id=0;
    private int propioId;

    public Cliente(){};

    public Cliente(String nombre, String apellido, String DNI){
        super(nombre, apellido, DNI);
        this.propioId = crearNuevoId();
    }

    public int crearNuevoId(){
        return ++id;
    }

    @Override
    public String toString()
    {
        return "Cliente: " +
                "\nID: " + propioId + super.toString();

    }
}
