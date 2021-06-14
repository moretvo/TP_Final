package com.TpF.Store;

import com.TpF.Personas.Cliente;
import com.TpF.Personas.Staff;
import com.TpF.Videogame.Consola;
import com.TpF.Videogame.Juego;
import com.TpF.misc.Archivos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ventas {


    private List<Juego> jueguitos = new ArrayList<>();
    private List<Ventas> ventas = new ArrayList<>();
    private Juego juego;
    private Cliente cliente;

    ///Para el historial
    private String nombreDelJuego;
    private Consola consola;
    private int precio;
    private String nombredeUsuario;
    private String nombreApellido;


    public Ventas(){

    };

    public Ventas(Juego j, Cliente c){
        this.nombreDelJuego = j.getTitulo();
        this.consola = j.getConsola();
        this.precio = j.getPrecio();

        this.nombredeUsuario = c.getUsuario();
        this.nombreApellido = c.getNombre() + " " + c.getApellido();
    }

    public int getPrecio() { return precio; }

    public void ventaDeJuego(Cliente c){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;

        Juego juegoBuscado = new Juego();


        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese el nombre del juego que desea comprar: ");
        String juegoABuscar = input.nextLine();
        int i = 0;

        try {
            reader = new BufferedReader(new FileReader(new File("juegos.json")));
            jueguitos = gson.fromJson(reader,(new TypeToken<List<Juego>>() {}.getType()));

            while(i<jueguitos.size())
            {
                if(jueguitos.get(i).getTitulo().compareTo(juegoABuscar)==0)
                {
                    juegoBuscado = jueguitos.get(i);
                }
                i++;
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

        if(juegoBuscado.getTitulo()==null)
        {
            System.out.println("El juego ingresado no se encuentra disponible.");
        }
        else
        {

            System.out.println(juegoBuscado.toString());
            System.out.println("El juego tiene un precio de $" + juegoBuscado.getPrecio() + " pesos.");
            System.out.println("¿Comprar?");
            System.out.println("1) Si");
            System.out.println("2) No");
            input.reset();

            int numero =  input.nextInt();
            switch(numero) {
                case 1:

                    Ventas aux = new Ventas(juegoBuscado, c);
                    ventas = cargarVentas();
                    ventas.add(aux);

                    Archivos<Ventas> venta = new Archivos<>();
                    venta.guardarArchivoSingular("ventas.json", ventas);
                    break;
                    case 2:
                        break;
                }
            }

        }



    public List<Ventas> cargarVentas(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File ("ventas.json")));
            ventas = gson.fromJson(reader,(new TypeToken<List<Ventas>>() {}.getType()));
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
        return ventas;
    }
    @Override
    public String toString(){
        return "Juego: " + nombreDelJuego +
                "\nConsola: " + consola +
                "\nPrecio: " + precio +
                "\nA nombre de: " + nombreApellido +
                "\n(Usuario: " + nombredeUsuario + ")";
    }
}






