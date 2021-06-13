package com.TpF.Personas;

import com.TpF.Personas.ServiciosCliente;
import com.TpF.Personas.Cliente;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff{

    private String contraseñaAdmin;
    boolean primeraVez;


    public Staff(){
    };


    public void init(){
        if(comprobarSiExisteAccesoMaestro()==1)
        {
            this.primeraVez = false;
        }
        else
        {
            this.primeraVez = true;
        }
    }

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setContraseñaAdmin(String contraseñaAdmin) {
        this.contraseñaAdmin = contraseñaAdmin;
    }

    public int comprobarSiExisteAccesoMaestro(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        int existeContraseña=0;
        try{
            reader = new BufferedReader(new FileReader(new File ("Contraseña_maestra_del_Staff.json")));
            Staff aux = gson.fromJson(reader, Staff.class);
            if(aux.contraseñaAdmin != null) {
                existeContraseña = 1;
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
            catch (NullPointerException e) {
            e.printStackTrace();

        }

        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return existeContraseña;
    }

    public void accesoPrimeraVez(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner input = new Scanner(System.in);
        System.out.println("\nComo es su primera vez accediendo al sistema, ingrese una contraseña maestra de administracion.");
        String pass =  input.nextLine();
        Staff staff = new Staff();
        staff.setContraseñaAdmin(pass);
        BufferedWriter fArchivo = null;
        try {
            fArchivo = new BufferedWriter(new FileWriter(new File("Contraseña_maestra_del_Staff.json")));

            gson.toJson(staff, staff.getClass(), fArchivo);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if(fArchivo != null) {
                try {
                    fArchivo.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   public boolean loginStaff(){
       Scanner input = new Scanner(System.in);
       System.out.println("Ingrese la contraseña maestra del STAFF: ");
       String aux = input.nextLine();

       boolean acceso=false;

       Gson gson = new GsonBuilder().setPrettyPrinting().create();
       BufferedReader reader = null;

       try {
           reader = new BufferedReader(new FileReader(new File ("Contraseña_maestra_del_Staff.json")));
           Staff staff = gson.fromJson(reader, Staff.class);
           if(aux.compareTo(staff.contraseñaAdmin)==0)
           {
               acceso = true;
           }

       }
       catch (IOException e) {
           e.printStackTrace();

       }
       catch (NullPointerException e) {
           e.printStackTrace();

       }finally {
           try {
               if (reader != null) {
                   reader.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       return acceso;

   }

}
