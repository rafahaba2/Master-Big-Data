package org.uma.mbd.mdLibreriaV3L.libreria;

import java.util.ArrayList;
import java.util.List;

public class Libreria {

    protected List<Libro>libros;

    public Libreria(){ libros = new ArrayList<>();}

    private int posicionLibro(String aut, String tit){
        int pos = 0;

        while(pos < libros.size() && !((libros.get(pos).getAutor().equals(aut)) && libros.get(pos).getTitulo().equalsIgnoreCase(tit))){
            pos++;
        }

        return (pos < libros.size())?pos:-1;
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