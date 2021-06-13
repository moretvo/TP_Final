package com.TpF.Store;

import com.TpF.Personas.Cliente;
import com.TpF.Videogame.Consola;
import com.TpF.Videogame.Developer;
import com.TpF.Videogame.Juego;
import com.TpF.misc.ESRB;
import com.TpF.misc.Genero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CompraVideojuegos {
    private List<Juego> juegos = new ArrayList<>();
    private List<Developer> devs = new ArrayList<>();

    public CompraVideojuegos(){
        cargaListaDeVideojuegos();
    };





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
        System.out.println("¿Qué genero es?");
        System.out.println("1) ACCION");
        System.out.println("2) PLATAFORMAS");
        System.out.println("3) RITMO");
        System.out.println("4) FIGHTING GAME");
        System.out.println("5) PUZZLE");
        System.out.println("6) RPG");
        System.out.println("7) AVENTURA");
        System.out.println("8) TERROR");
        int numero = input.nextInt();
        switch(numero)
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
        numero = input.nextInt();
        switch(numero)
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
        numero = input.nextInt();
        switch(numero)
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter fArchivo = null;
        try {
            fArchivo = new BufferedWriter(new FileWriter(new File("juegos.json")));
            String json = gson.toJson(juegos, juegos.getClass());
            fArchivo.write(json);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fArchivo != null) {
                try {
                    fArchivo.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void cargaListaDeVideojuegos(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        int i=0;
        try {
            reader = new BufferedReader(new FileReader(new File("juegos.json")));
            juegos = gson.fromJson(reader,(new TypeToken<List<Juego>>() {}.getType()));
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


