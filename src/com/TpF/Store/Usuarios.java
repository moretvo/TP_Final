package com.TpF.Store;

import com.TpF.Personas.ServiciosCliente;
import com.TpF.Personas.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuarios implements ServiciosCliente {
    private List<Cliente> clientes = new ArrayList<>();

    public Usuarios(){};

    @Override
    public void crearUsuario() {
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

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                //Acá lo paso a un archivo
                BufferedWriter fArchivo = null;

                try {
                    fArchivo = new BufferedWriter(new FileWriter(new File("clientes.json")));
                    String json = gson.toJson(clientes, clientes.getClass());
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
                break;
            case 2:
                break;
        }
    }

    @Override
    public void loginUsuarioCliente() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario: ");
        String usuario = input.nextLine();
        System.out.println("Ingrese su contraseña: ");
        input.reset();
        String password = input.nextLine();






    }

    @Override
    public void compraDeJuegos() {

    }

    @Override
    public void mostrarCatalogoDeJuegos() {

    }

    @Override
    public void historialDeCompras() {

    }

    @Override
    public void recomendacionDeLaCasa() {

    }
}
