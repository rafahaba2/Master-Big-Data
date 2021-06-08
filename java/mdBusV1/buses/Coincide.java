package org.uma.mbd.mdBusV1.buses;

public class Coincide implements Criterio{
    private Bus bus;

    public Coincide(Bus bus){
        this.bus = bus;
    }

    public boolean esSeleccionable(Bus bus){
        return this.bus.equals(bus);
    }

    @Override
    public String toString(){
        return "Autobus "+ bus;
    }
}
