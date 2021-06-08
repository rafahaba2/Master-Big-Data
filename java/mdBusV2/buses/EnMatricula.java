package org.uma.mbd.mdBusV2.buses;


public class EnMatricula implements Criterio {
    private String dato;
    public EnMatricula(String dato){
        this.dato = dato;
    }
    public boolean esSeleccionable(Bus bus){
        return bus.getMatricula().contains(dato);
    }

    @Override
    public String toString(){
        return "Autobuses cuya matricula contiene "+dato;
    }

}
