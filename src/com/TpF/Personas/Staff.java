package com.TpF.Personas;

import com.TpF.Personas.ServiciosCliente;
import com.TpF.Personas.Cliente;
import com.TpF.Store.Usuarios;
import com.TpF.Store.Ventas;
import com.TpF.Videogame.Consola;
import com.TpF.Videogame.Juego;
import com.TpF.misc.Archivos;
import com.TpF.misc.ESRB;
import com.TpF.misc.Genero;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff{
    private List<Juego> juegos = new ArrayList<>();
    private String contraseñaAdmin;
    boolean primeraVez;


    public Staff(){
        cargaListaDeVideojuegos();
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

    public void cargarVideojuegoNuevo(){
        Juego juego = new Juego();

        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese titulo del videojuego: ");
        String titulo = input.nextLine();
        juego.setTitulo(titulo);

        input.reset();
        System.out.println("Ingrese nombre del desarrollador: ");
        String desarrollador = input.nextLine();
        juego.setDesarrollador(desarrollador);

        input.reset();
        System.out.println("Ingrese fecha de lanzamiento (Dia): ");
        String diaLanzamiento = input.nextLine();
        input.reset();
        System.out.println("Ingrese fecha de lanzamiento (Mes): ");
        String mesLanzamiento = input.nextLine();
        input.reset();
        System.out.println("Ingrese fecha de lanzamiento (Año): ");
        String añoLanzamiento = input.nextLine();
        String fechaLanzamiento = diaLanzamiento + "-" + mesLanzamiento + "-" + añoLanzamiento; ///Esto para que el formatter lo deje en buen formato basicamente
        juego.setFechaDeLanzamiento(fechaLanzamiento);

        input.reset();
        System.out.println("Precio: ");
        int numero = input.nextInt();
        juego.setPrecio(numero);


        input.reset();
        System.out.println("¿Qué genero es?");
        System.out.println("1) ACCION");
        System.out.println("2) PLATAFORMAS");
        System.out.println("3) RITMO");
        System.out.println("4) FIGHTING GAME");
        System.out.println("5) PUZZLE");
        System.out.println("6) RPG");
        System.out.println("7) AVENTURA");
        System.out.println("8) TERROR");
        int valor = input.nextInt();
        switch(valor)
        {
            case 1:
                juego.setGenero(Genero.ACCION); break;
            case 2:
                juego.setGenero(Genero.PLATAFORMAS); break;
            case 3:
                juego.setGenero(Genero.RITMO); break;
            case 4:
                juego.setGenero(Genero.FIGHTING_GAME); break;
            case 5:
                juego.setGenero(Genero.PUZZLE); break;
            case 6:
                juego.setGenero(Genero.RPG); break;
            case 7:
                juego.setGenero(Genero.AVENTURA); break;
            case 8:
                juego.setGenero(Genero.TERROR); break;
        }

        input.reset();
        System.out.println("¿Cual es su rating?");
        System.out.println("1) E (For Everyone)");
        System.out.println("2) T (For Teens)");
        System.out.println("3) M (Mature, +18)");
        System.out.println("4) AO (Adults Only)");
        System.out.println("5) RP (Rating Pending)");
        valor = input.nextInt();
        switch(valor)
        {
            case 1:
                juego.setRating(ESRB.E); break;
            case 2:
                juego.setRating(ESRB.T); break;
            case 3:
                juego.setRating(ESRB.M); break;
            case 4:
                juego.setRating(ESRB.AO); break;
            case 5:
                juego.setRating(ESRB.RP); break;
        }

        input.reset();
        System.out.println("¿Para que consola es?");
        System.out.println("1) Sega Genesis");
        System.out.println("2) Nintendo 64");
        System.out.println("3) Playstation 2");
        System.out.println("4) Playstation 3");
        System.out.println("5) Playstation 4");
        System.out.println("6) Nintendo Switch");
        valor = input.nextInt();
        switch(valor)
        {
            case 1:
                juego.setConsola(Consola.SEGA_GENESIS); break;
            case 2:
                juego.setConsola(Consola.NINTENDO_64); break;
            case 3:
                juego.setConsola(Consola.PLAYSTATION_2); break;
            case 4:
                juego.setConsola(Consola.PLAYSTATION_3); break;
            case 5:
                juego.setConsola(Consola.PLAYSTATION_4); break;
            case 6:
                juego.setConsola(Consola.NINTENDO_SWITCH); break;
        }

        ///Acá lo agrego a la lista de juegos
        juegos.add(juego);

        ///Bueno vamo a pasarlo a un archivo, mucho texto

        Archivos<Juego> juegoFile = new Archivos<>();
        juegoFile.guardarArchivoSingular("juegos.json", juegos);

    }
    public void cargaListaDeVideojuegos(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("juegos.json")));
            juegos = gson.fromJson(reader,(new TypeToken<List<Juego>>() {}.getType()));

        }
        catch (IOException e) {
            e.printStackTrace();

        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();

        }
        catch (JsonSyntaxException e) {
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
    }

    public void verListaDeJuegos(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        List<Juego> juegoaux = new ArrayList<>();
        int i=0;
        try {
            reader = new BufferedReader(new FileReader(new File("juegos.json")));
            juegoaux = gson.fromJson(reader,(new TypeToken<List<Juego>>() {}.getType()));

            for(var juegos : juegoaux)
            {
                System.out.println("*------------------------*");
                System.out.println(juegos.toString());
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
    }

    public void verListaDeUsuarios(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        List<Cliente> clientes = new ArrayList<>();
        int i=0;
        try {
            reader = new BufferedReader(new FileReader(new File ("clientes.json")));
            clientes = gson.fromJson(reader,(new TypeToken<List<Cliente>>() {}.getType()));

            for (var aux : clientes){
                System.out.println("*----------------------*");
                System.out.println(aux.toString());
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
    }

    public void verHistorialDeVentas(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        List<Ventas> ventas = new ArrayList<>();
        int gananciasTotales=0;
        try {
            reader = new BufferedReader(new FileReader(new File ("ventas.json")));
            ventas = gson.fromJson(reader,(new TypeToken<List<Ventas>>() {}.getType()));


            for (var aux : ventas){
                gananciasTotales = gananciasTotales + aux.getPrecio();
                System.out.println("*----------------------*");
                System.out.println(aux.toString());
            }
            System.out.println("\nLas ganancias totales del local suman: " + gananciasTotales);
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

    }


}
