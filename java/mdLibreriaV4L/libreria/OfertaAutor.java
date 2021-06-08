package org.uma.mbd.mdLibreriaV4L.libreria;

import java.util.Arrays;

public class OfertaAutor implements OfertaFlex {
    private String[]autores;
    private double porDescuento;

    public OfertaAutor(double porDescuento, String[]autores){
        this.porDescuento = porDescuento;
        this.autores = autores;
    }

    public double getDescuento(Libro libro){
        return esAutorEnOferta(libro.getAutor())?porDescuento:0;
    }

    private boolean esAutorEnOferta(String autor){
        int pos = 0;
        while(pos < autores.length && autores[pos].equalsIgnoreCase(autor)){
            pos++;
        }

        return pos < autores.length;
    }

    @Override
    public String toString(){
        return porDescuento +"%" + Arrays.toString(autores);
    }
}
