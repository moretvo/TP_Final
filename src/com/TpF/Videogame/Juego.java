package com.TpF.Videogame;

import com.TpF.misc.ESRB;
import com.TpF.misc.Genero;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Juego {
    private String titulo;
    private String desarrollador;
    private Consola consola;
    private String fechaDeLanzamiento;
    private LocalDate releaseDate;
    private Genero genero;
    private ESRB rating;

    public Juego(){};

    public Juego(String titulo, String desarrollador, String fechaDeLanzamiento, Genero genero, ESRB rating, Consola consola)
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public void setConsola(Consola consola) {
        this.consola = consola;
    }

    public void setFechaDeLanzamiento(String fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setRating(ESRB rating) {
        this.rating = rating;
    }

    @Override
    public String toString()
    {
        return "\nTitulo: " + titulo +
                "\nDesarrollado por " + desarrollador +
                "\nConsola: " + consola +
                "\nFecha de Lanzamiento: " + releaseDate +
                "\nGenero: " + genero +
                "\nRating: " + rating;
    }

}

