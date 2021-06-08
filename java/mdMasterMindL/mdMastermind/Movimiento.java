package org.uma.mbd.mdMasterMindL.mdMastermind;

public class Movimiento {
    private int colocadas;
    private int descolocadas;
    private String cifras;

    public Movimiento(String cifras, int colocadas, int descolocadas){
        this.cifras = cifras;
        this.colocadas = colocadas;
        this.descolocadas = descolocadas;
    }

    public int getColocadas() {
        return colocadas;
    }

    public int getDescolocadas() {
        return descolocadas;
    }

    public String getCifras() {
        return cifras;
    }

    @Override
    public boolean equals(Object obj){
        boolean res = obj instanceof  Movimiento;
        Movimiento m = res?(Movimiento)obj:null;

        return res && (colocadas == m.colocadas);
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(colocadas);
    }

    @Override
    public String toString(){
        return "[" + cifras + ", " + colocadas + ", " + descolocadas + "]";
    }
}
