package org.uma.mbd.mdLibreriaV4L.libreria;

import java.util.ArrayList;
import java.util.List;

public class Libreria {

    protected List<Libro>libros;

    public Libreria(){ libros = new ArrayList<>();}

    private int posicionLibro(String aut, String tit){

        Libro libro = new Libro(aut, tit, 0);
        return libros.indexOf(libro);

    }

    public void addLibro(String aut, String tit, double pb){
        addLibro(new Libro(aut, tit, pb));
    }

    public void addLibro(Libro libro){
        int pos = posicionLibro(libro.getAutor(), libro.getTitulo());

        if(pos >= 0){
            libros.add(libro);
        }else{
            libros.add(libro);
        }
    }

    public void remLibro(String autor, String titulo){
        int pos = posicionLibro(autor, titulo);

        if(pos >= 0){
            libros.remove(pos);
        }
    }

    public double getPrecioBase(String autor, String titulo){
        int pos = posicionLibro(autor, titulo);
        return (pos >= 0) ? libros.get(pos).getPrecioBase():0;
    }

    public double getPrecioFinal(String autor, String titulo){
        int pos = posicionLibro(autor, titulo);
        return (pos >= 0) ? libros.get(pos).getPrecioFinal():0;
    }

    @Override
    public String toString(){
        return libros.toString();
    }

}