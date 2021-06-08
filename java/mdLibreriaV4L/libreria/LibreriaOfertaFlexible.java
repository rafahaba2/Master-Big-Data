package org.uma.mbd.mdLibreriaV4L.libreria;

public class LibreriaOfertaFlexible extends Libreria {
    protected OfertaFlex ofertaFlexible;

    public LibreriaOfertaFlexible(OfertaFlex oFlex){
        ofertaFlexible = oFlex;
    }

    public void setOferta(OfertaFlex oFlex){
        ofertaFlexible = oFlex;

        for(Libro libro : libros){
            String autor = libro.getAutor();
            String titulo = libro.getTitulo();
            double pb = libro.getPrecioBase();
            this.addLibro(autor, titulo, pb);
        }
    }

    @Override
    public void addLibro(String autor, String titulo, double precioBase){
        Libro libro = new Libro(autor, titulo, precioBase);

        double descuento = ofertaFlexible.getDescuento(libro);

        if(descuento > 0){
            libro = new LibroEnOferta(autor, titulo, precioBase, descuento);
        }

        addLibro(libro);
    }

    @Override
    public String toString(){
        return ofertaFlexible.toString() + super.toString();
    }
}
