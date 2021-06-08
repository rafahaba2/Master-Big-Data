package org.uma.mbd.mdAmigoInvisible.amigos;

import org.uma.mbd.mdBusV1.buses.Bus;

import java.util.Collections;

public class Persona implements Comparable<Persona>{
    private String nombre;
    private Persona amigo;

    public Persona(String nombre){
        this.nombre = nombre;
    }

    public void setAmigo(Persona am){
        amigo = am;
    }

    public String getNombre(){
        return nombre;
    }

    public Persona getAmigo(){
        return amigo;
    }

    @Override
    public boolean equals(Object obj){
        boolean res = obj instanceof Persona;
        Persona p = res?(Persona)obj:null;

        return res && (p.nombre.equalsIgnoreCase(nombre));
    }

    @Override
    public int hashCode(){
        return nombre.hashCode();
    }

    @Override
    public String toString(){
        return (amigo == null)?nombre + "--> sin amigo": nombre +"-->"+amigo.getNombre()+'\n';
    }

    @Override
    public int compareTo(Persona p) {
        return p.nombre.compareTo(nombre);
    }
}
