package org.uma.mbd.mdCoches.coches;

public class CocheImportado extends Coche{
    private double homologacion;

    public CocheImportado(String nombre, double precio, double homologacion){
        super(nombre, precio);
        this.homologacion = homologacion;
    }

    public double precioTotal(){
        return super.precioTotal() + homologacion;
    }
}
