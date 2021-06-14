package com.TpF.Store;

import com.TpF.Personas.ServiciosCliente;
import com.TpF.Personas.Cliente;
import com.TpF.Personas.Staff;
import com.TpF.Videogame.Consola;
import com.TpF.Videogame.Juego;
import com.TpF.misc.Archivos;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.Random;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuarios implements ServiciosCliente {
    private List<Cliente> clientes = new ArrayList<>();
    public Usuarios(){
        cargarEntidad();
    };

    @Override
    public void cargarEntidad(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File ("clientes.json")));
            clientes = gson.fromJson(reader,(new TypeToken<List<Cliente>>() {}.getType()));
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

    @Override
    public void crearEntidad() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nombre de usuario: ");
        String usuario = input.nextLine();
        System.out.println("Contraseña: ");
        input.reset();
        String password = input.nextLine();

        System.out.println("Ingrese su nombre: ");
        input.reset();
        String nombre = input.nextLine();
        System.out.println("Ingrese su apellido");
        input.reset();
        String apellido = input.nextLine();
        System.out.println("Ingrese su DNI: ");
        input.reset();
        String DNI = input.nextLine();

        System.out.println("Son estos datos correctos?\n");
        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("DNI: " + DNI);

        input.reset();
        System.out.println("1) Si, son correctos.");
        System.out.println("2) No, deseo volver.");
        int numero = input.nextInt();

        switch(numero) {
            case 1:
                Cliente cliente = new Cliente(nombre, apellido, DNI);
                cliente.setUsuario(usuario);
                cliente.setPassword(password);
                clientes.add(cliente);

                Archivos<Cliente> usuariosFile = new Archivos<>();
                usuariosFile.guardarArchivoSingular("clientes.json", clientes);
                break;
            case 2:
                break;
        }
    }

    public Cliente loginUsuarioCliente() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario: ");
        String usuario = input.nextLine();
        System.out.println("Ingrese su contraseña: ");
        input.reset();
        String password = input.nextLine();

        //Bueno vamo a comparar si es lo mismo que lo que esta en el archivo eh
        int rtaUsuario=2; //Le pongo dos para que sea desigual a 0 basicamente y no sea null
        int rtapassword=2;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader reader = null;
        int i=0;

        Cliente clienteIngresado = new Cliente();
        List<Cliente> clientesaux = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(new File ("clientes.json")));
            clientesaux = gson.fromJson(reader,(new TypeToken<List<Cliente>>() {}.getType()));

            while(i < (clientesaux.size()))
            {
                if(clientesaux.get(i).getUsuario().compareTo(usuario)==0 && clientesaux.get(i).getPassword().compareTo(password)==0) {
                    clienteIngresado = clientesaux.get(i);
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
        return clienteIngresado;
}


    public void mostrarJuegosPorConsola(Consola consola) {
        Staff admin = new Staff();
        List<Juego> lista = new ArrayList<>();
        List<Juego> listaDeConsola = new ArrayList<>();
        lista = admin.cargaListaDeVideojuegos();
        int i=0;
        boolean flag=false;
        while(i<lista.size())
        {
            if(lista.get(i).getConsola().equals(consola))
            {
                System.out.println(lista.get(i).toString());
                flag=true;
            }
            i++;
        }
        if(flag==false){
            System.out.println("No hay titulos disponibles para esa consola :(  (" + consola +")");
        }
    }


    public void buscarPorPrecio(int precio){
        Staff admin = new Staff();
        List<Juego> lista = new ArrayList<>();
        List<Juego> listaOferta = new ArrayList<>();
        lista = admin.cargaListaDeVideojuegos();
        int i=0;
        boolean flag=false;
        while(i<lista.size())
        {
            if(lista.get(i).getPrecio() < precio)
            {
                System.out.println(lista.get(i).toString());
                flag=true;
            }
            i++;
        }
        if(flag==false){
            System.out.println("No hay titulos disponibles que sean mas baratos a ese valor (" +precio+")");
        }
    }


    public void historialDeCompras(Cliente cliente) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Ventas> ventas = new ArrayList<>();
        BufferedReader reader = null;
        int i=0;
        try {
            reader = new BufferedReader(new FileReader(new File ("ventas.json")));
            ventas = gson.fromJson(reader,(new TypeToken<List<Ventas>>() {}.getType()));

            while(i<ventas.size())
            {
                if(ventas.get(i).getNombredeUsuario().equals(cliente.getUsuario()))
                {
                    System.out.println(ventas.get(i).toString());
                    System.out.println("*--------------------*");
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
    }

    public void recomendacionDeLaCasa() {
            Staff admin = new Staff();
            List<Juego> lista = new ArrayList<>();
            List<Juego> listaOferta = new ArrayList<>();
            lista = admin.cargaListaDeVideojuegos();

            Random rnd = new Random();
            int tamaño = lista.size()-1;

            int valor = (int) ((Math.random() * tamaño) + 1);
            System.out.println();

            System.out.println("La casa recomienda el siguiente titulo: ");
            System.out.println(lista.get(valor).toString());

        }



}

