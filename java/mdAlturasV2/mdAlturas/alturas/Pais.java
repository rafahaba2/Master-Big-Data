package org.uma.mbd.mdAlturasV2.mdAlturas.alturas;
public class Pais implements Comparable<Pais>{
    private String nombre;
    private String continente;
    private double altura;

    public Pais(String nombre, String continente, double altura){
        this.nombre = nombre;
        this.continente = continente;
        this.altura = altura;
    }


    @Override
    public boolean equals(Object obj){
        boolean res = obj instanceof Pais;
        Pais p = res?(Pais)obj:null;

        return res && (p.nombre.equals(nombre));
    }
    @Override
    public int compareTo(Pais p){
        int r = Double.compare(altura, p.altura);

        if(r == 0){
            r = nombre.compareTo(p.nombre);
        }
        return r;
    }

    @Override
    public int hashCode(){
        return nombre.hashCode();
    }

    public String getNombre() {
        return nombre;
    }

    public String getContinente() {
        return continente;
    }

    public double getAltura() {
        return altura;
    }

    @Override
    public String toString(){
        return "Pais("+nombre + "," + continente + "," + altura +")";
    }
}
