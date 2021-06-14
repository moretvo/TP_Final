package com.TpF;
import com.TpF.Personas.Cliente;
import com.TpF.Personas.Staff;
import com.TpF.Store.Usuarios;
import com.TpF.Store.Ventas;
import com.TpF.Videogame.Consola;

import java.util.Scanner;

public class Menu {

    public Menu(){};

    Usuarios usuario = new Usuarios();
    Staff admin = new Staff();
    Cliente cliente = new Cliente();
    boolean entradaAdmin;
    Ventas venta = new Ventas();



    public void imprimirMenuPrincipal(){
        Scanner input = new Scanner(System.in);
        limpiarConsola();
        System.out.println("\n--------------------------------");
        System.out.println("\t Local de Videojuegos\t");
        System.out.println("\n Presione el numero de acceso e ingrese a la opcion con Enter\n");
        System.out.println("1) Acceso a clientes");
        System.out.println("2) Acceso a staff");
        System.out.println("\n0) Salir");
        int menu = input.nextInt();
        switch(menu){
            case 1:
                imprimirMenuClientes();
                break;
            case 2:
                ///TIENE QUE PEDIR CONTRASEÑA PARA LA PRIMERA VEZ Y DESPUES PEDIRLA PARA ACCEDER
                admin.init();
                if(admin.isPrimeraVez()==true)
                {
                    admin.crearEntidad();
                }
                else
                {
                    entradaAdmin=admin.loginStaff();
                    if(entradaAdmin==true)
                    {
                        imprimirMenuStaff();
                    }
                    else
                    {
                        System.out.println("Acceso denegado. Volver a intentar\n");
                    }
                }
                imprimirMenuPrincipal();
                break;
            case 0:
                System.out.println("Muchas gracias por usar el software!");
                System.exit(0);
                break;

        }

    }
    public void imprimirMenuClientes(){
        Scanner input = new Scanner(System.in);
        int acceso;
        limpiarConsola();
        System.out.println("\n--------------------------------");
        System.out.println("\t CLIENTES\t");
        System.out.println("\n Presione el numero de acceso e ingrese a la opcion con Enter\n");
        System.out.println("1) Login");
        System.out.println("2) Registrar nuevo usuario");
        System.out.println("\n0) Volver");
        int menu = input.nextInt();
        switch(menu){
            case 1:
                cliente=usuario.loginUsuarioCliente();
                if(cliente != null)
                {
                    imprimirMenuClientesLogin(cliente);
                }
                else
                {
                    System.out.println("Uno de los datos ingresados es erroneo. Volver a intentar\n");
                    imprimirMenuClientes();
                }
                break;
            case 2:
                usuario.crearEntidad();
                imprimirMenuPrincipal();
                break;
            case 0:
                imprimirMenuPrincipal();
                break;

        }
    }
    public void imprimirMenuClientesLogin(Cliente cliente){
        Scanner input = new Scanner(System.in);
        limpiarConsola();
        System.out.println("\n--------------------------------");
        cliente.saludoInicial(); ///Para demostrar abstraccion
        System.out.println("\n Presione el numero de acceso e ingrese a la opcion con Enter\n");
        System.out.println("1) Compra de juegos");
        System.out.println("2) Ver catalogo de juegos");
        System.out.println("3) Historial de compras");
        System.out.println("4) Recomendacion de la casa");
        System.out.println("\n0) Volver al menu principal");
        int menu = input.nextInt();
        switch(menu){
            case 1:
                venta.ventaDeJuego(cliente);
                imprimirMenuClientesLogin(cliente);
                break;
            case 2:
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 3:
                usuario.historialDeCompras(cliente);
                imprimirMenuClientesLogin(cliente);
                break;
            case 4:
                usuario.recomendacionDeLaCasa();
                imprimirMenuClientesLogin(cliente);
                break;
            case 0:
                imprimirMenuPrincipal();
                break;

        }
    }

    public void imprimirMenuClientesCatalogo(Cliente cliente){
        Scanner input = new Scanner(System.in);
        limpiarConsola();
        System.out.println("\n--------------------------------");
        System.out.println("\t CATALOGO DE JUEGOS");
        System.out.println("\n Presione el numero de acceso e ingrese a la opcion con Enter\n");
        System.out.println("1) Ver lista de juegos");
        System.out.println("2) Ver juegos por consola");
        System.out.println("3) Buscar por precio");
        System.out.println("\n0) Volver");
        int menu = input.nextInt();
        switch(menu){
            case 1:
                admin.verListaDeJuegos();
                System.out.println("\nPara comprar uno de los juegos, vaya a la seccion de compra y escriba el titulo del juego que quiera comprar.\n");
                imprimirMenuClientesLogin(cliente);
                break;
            case 2:
                imprimirMenuClientesConsola();
                break;
            case 3:
                System.out.println("Ingrese un precio (Mostrará los juegos mas baratos a ese valor)\n");
                input.reset();
                int precio = input.nextInt();
                usuario.buscarPorPrecio(precio);
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 0:
                imprimirMenuClientesLogin(cliente);
                break;

        }
    }

    public void imprimirMenuClientesConsola(){
        Scanner input = new Scanner(System.in);
        limpiarConsola();
        System.out.println("\n--------------------------------");
        System.out.println("\t CATALOGO DE JUEGOS");
        System.out.println("\n Presione el numero de consola del que desee explorar sus titulos disponibles.\n");
        System.out.println("1) Sega Genesis");
        System.out.println("2) Nintendo 64");
        System.out.println("3) Playstation 2");
        System.out.println("4) Playstation 3");
        System.out.println("5) Playstation 4");
        System.out.println("6) Nintendo Switch");
        System.out.println("\n0) Volver");
        int menu = input.nextInt();
        switch(menu){
            case 1:
                usuario.mostrarJuegosPorConsola(Consola.SEGA_GENESIS);
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 2:
                usuario.mostrarJuegosPorConsola(Consola.NINTENDO_64);
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 3:
                usuario.mostrarJuegosPorConsola(Consola.PLAYSTATION_2);
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 4:
                usuario.mostrarJuegosPorConsola(Consola.PLAYSTATION_3);
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 5:
                usuario.mostrarJuegosPorConsola(Consola.PLAYSTATION_4);
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 6:
                usuario.mostrarJuegosPorConsola(Consola.NINTENDO_SWITCH);
                imprimirMenuClientesCatalogo(cliente);
                break;
            case 0:
                imprimirMenuClientesCatalogo(cliente);
                break;

        }
    }

    ///STAFF
    public void imprimirMenuStaff(){
        Scanner input = new Scanner(System.in);
        limpiarConsola();
        System.out.println("\n--------------------------------");
        System.out.println("\t Menu de Staff\t");
        System.out.println("\n Presione el numero de acceso e ingrese a la opcion con Enter\n");
        System.out.println("1) Registrar nuevo videojuego");
        System.out.println("2) Ver lista de juegos");
        System.out.println("3) Ver lista de usuarios");
        System.out.println("4) Ver historial de ventas");
        System.out.println("\n0) Volver");
        int menu = input.nextInt();
        switch(menu){
            case 1:
                admin.cargarVideojuegoNuevo();
                imprimirMenuStaff();
                break;
            case 2:
                admin.verListaDeJuegos();
                imprimirMenuStaff();
                break;
            case 3:
                admin.verListaDeUsuarios();
                imprimirMenuStaff();
                break;
            case 4:
                admin.historialDeVentas();
                imprimirMenuStaff();
                break;
            case 0:
                imprimirMenuPrincipal();
                break;

        }
    }




    public static void limpiarConsola() {
        for (int i = 0; i < 20; ++i)
        {System.out.println();}
    }

}
