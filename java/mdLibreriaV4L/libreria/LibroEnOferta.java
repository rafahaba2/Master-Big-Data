package org.uma.mbd.mdLibreriaV4L.libreria;

public class LibroEnOferta extends Libro {

    private double descuento;

    public LibroEnOferta(String autor, String titulo, double precioBase, double porcDescuento){
        super(autor, titulo, precioBase);
        descuento = porcDescuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getPrecioFinal(){
        return super.getPrecioFinal()-(super.getPrecioFinal()*descuento/100);
    }

    public String toString(){
        return "("+getAutor()+"; "+getTitulo()+"; "+super.getPrecioFinal()+"; "+getIVA()+"%; "+getPrecioBase()+"; "+descuento+"%; "+getPrecioFinal()+")";
    }
}
