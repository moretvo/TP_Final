package com.TpF;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

import static java.lang.System.exit;

public class Menu {

    public Menu(){};


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
                break;
            case 0:
                System.out.println("Muchas gracias por usar el software!");
                System.exit(0);
                break;

        }

    }
    public void imprimirMenuClientes(){
        Scanner input = new Scanner(System.in);
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

                break;
            case 2:
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
