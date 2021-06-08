package org.uma.mbd.mdLibreriaV1L.libreria;

import java.util.Arrays;

public class LibreriaOferta extends Libreria{
    private String[]autoresOferta;
    private double descuento;

    public LibreriaOferta(double descuento, String[]autoresOferta){
        this.descuento = descuento;
        this.autoresOferta = autoresOferta;
    }

   public void setOferta(double porDescuento, String[]autores){
        descuento = porDescuento;
        autoresOferta = autores;
   }

   public String[] getOferta(){
        return autoresOferta;
   }

   public double getDescuento(){
        return descuento;
   }

   public void addLibro(String autor, String titulo, double precioBase){

        if(esAutorEnOferta(autor)){
            Libro libro = new LibroEnOferta(autor, titulo, precioBase, descuento);
            addLibro(libro);
        }else{
            Libro libro = new Libro(autor, titulo, precioBase);
            addLibro(libro);
        }
   }

   private boolean esAutorEnOferta(String autor){
        int pos = 0;
        while(pos < autoresOferta.length && autoresOferta[pos].equalsIgnoreCase(autor)){
            pos++;
        }

        return pos < autoresOferta.length;
   }

   @Override
    public String toString(){
        return descuento + "% " + Arrays.toString(autoresOferta) + super.toString();
   }
}
