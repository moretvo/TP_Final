package com.TpF.misc;

import com.TpF.Personas.Cliente;
import com.TpF.Videogame.Juego;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Archivos <T> {
    private List<T> elementos = new ArrayList<>();

    public Archivos() {

    }


    public boolean agregarElementos(T elemento) {
        if(elementos.size()>100) {
            elementos.add(elemento);
            return true;
        }
        return false;
    }

    public void guardarArchivoSingular(String filename, List<T> elementos)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter fArchivo = null;

        try {
            fArchivo = new BufferedWriter(new FileWriter(new File(filename)));
            String json = gson.toJson(elementos, elementos.getClass());
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
}
