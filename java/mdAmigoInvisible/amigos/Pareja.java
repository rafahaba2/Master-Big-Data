package org.uma.mbd.mdAmigoInvisible.amigos;

public class Pareja {
    private Persona uno;
    private Persona otro;

    public Pareja(Persona uno, Persona otro){
        this.uno = uno;
        this.otro = otro;
    }


    @Override
    public boolean equals(Object obj){
        boolean res = obj instanceof Pareja;
        Pareja p = res?(Pareja)obj:null;
        return res && ((uno.equals(p.uno) && otro.equals(p.otro) || (uno.equals(p.otro) && otro.equals(p.uno))));
    }

    @Override
    public int hashCode(){
        return uno.hashCode() + otro.hashCode();
    }

    @Override
    public String toString(){
        return "("+uno.getNombre()+","+otro.getNombre()+")";
    }


}
