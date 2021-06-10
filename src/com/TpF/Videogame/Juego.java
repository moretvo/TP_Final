package com.TpF.Videogame;

import com.TpF.misc.ESRB;
import com.TpF.misc.Genero;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Juego {
    private String titulo;
    private Developer desarrollador;
    private Consola consola;
    private String fechaDeLanzamiento;
    private LocalDate releaseDate;
    private Genero genero;
    private ESRB rating;



        public Juego(String titulo, Developer desarrollador, String fechaDeLanzamiento, Genero genero, ESRB rating, Consola consola)
    {
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        releaseDate = LocalDate.parse(fechaDeLanzamiento);
        this.genero = genero;
        this.rating = rating;
        this.consola= consola;
    }


    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public String toString()
    {
        return "\nTitulo: " + titulo +
                "\nDesarrollado por " + desarrollador.toString() +
                "\nConsola: " + consola +
                "\nFecha de Lanzamiento: " + releaseDate +
                "\nGenero: " + genero +
                "\nRating: " + rating;
    }

}

