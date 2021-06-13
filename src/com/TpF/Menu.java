package com.TpF;
import com.TpF.Personas.Staff;
import com.TpF.Store.CompraVideojuegos;
import com.TpF.Store.Usuarios;

import java.io.IOException;
import java.util.Scanner;
import java.io.*;

import static java.lang.System.exit;

public class Menu {

    public Menu(){};

    Usuarios usuario = new Usuarios();
    Staff admin = new Staff();
    CompraVideojuegos compraJueguito = new CompraVideojuegos();
    boolean entradaAdmin;



    public void imprimirMenuPrincipal(){
        Scanner input = new Scanner(System.in);
        limpiarConsola();
        System.out.println("\n--------------------------------");
        System.out.println("\t Local de Videojuegos lmao\t");
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
                    admin.accesoPrimeraVez();
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
                acceso=usuario.loginUsuarioCliente();
                if(acceso==1)
                {
                    imprimirMenuClientesLogin();
                }
                else
                {
                    System.out.println("Uno de los datos ingresados es erroneo. Volver a intentar\n");
                    imprimirMenuClientes();
                }
                break;
            case 2:
                usuario.crearUsuario();
                imprimirMenuPrincipal();
                break;
            case 0:
                imprimirMenuPrincipal();
                break;

        }
    }
    public void imprimirMenuClientesLogin(){
        Scanner input = new Scanner(System.in);
        limpiarConsola();
        System.out.println("\n--------------------------------");
        System.out.println("\t Bienvenido, USUARIO\t");
        System.out.println("\n Presione el numero de acceso e ingrese a la opcion con Enter\n");
        System.out.println("1) Compra de juegos");
        System.out.println("2) Ver catalogo de juegos");
        System.out.println("3) Historial de compras");
        System.out.println("4) Recomendacion de la casa");
        System.out.println("\n0) Volver al menu principal");
        int menu = input.nextInt();
        switch(menu){
            case 1:
                ///En compra que busque un juego por nombre y permita comprarlo
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                imprimirMenuPrincipal();
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
