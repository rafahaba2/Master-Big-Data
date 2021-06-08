package org.uma.mbd.mdJarras.jarras;

public class Jarra {
    private final int capacidad;
    private int contenido;

    public Jarra(int capacidad){

        if(capacidad < 0){
            throw new RuntimeException("Jarra con capacidad negativa");
        }

        this.capacidad = capacidad;
        this.contenido = 0;
    }

    public void llena(){
        this.contenido = capacidad;
    }

    public void vacia(){
        this.contenido = 0;
    }

    public void llenaDesde(Jarra jarra){
        while((this.contenido < this.capacidad) && (jarra.contenido > 0)){
            this.contenido++;
            jarra.contenido--;
        }
    }

    public int getCapacidad(){
        return this.capacidad;
    }

    public int getContenido(){
        return this.contenido;
    }

    public String toString(){
        return ("Contenido: "+this.contenido+" Capacidad: "+this.capacidad);
    }

}
