package org.uma.mbd.mdLibreriaV3L.libreria;

public class Libro {

    private String autor, titulo;
    private double precioBase;
    static private double IVA = 10.0;

    //Constructor
    public Libro(String aut, String tit, double prec){
        autor = aut;
        titulo = tit;
        precioBase = prec;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getPrecioFinal(){
        return (((getIVA()*getPrecioBase()/100)) + getPrecioBase());
    }

    public static double getIVA() {
        return IVA;
    }

    public static void setIVA(double IVA){
        Libro.IVA = IVA;
    }


    public String toString(){
        return ("("+getAutor()+"; "+ getTitulo()+"; "+getPrecioBase()+"; "+getIVA()+ "%; "+getPrecioFinal()+")");
    }
}
