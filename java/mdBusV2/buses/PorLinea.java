package org.uma.mbd.mdBusV2.buses;

public class PorLinea implements Criterio {
    private int codLinea;
    public PorLinea(int codLinea){
        this.codLinea = codLinea;
    }
    public boolean esSeleccionable(Bus bus){
        return bus.getCodLinea() == codLinea;
    }
    @Override
    public String toString(){
        return "Autobuses de la linea " +codLinea;
    }
}
