package org.uma.mbd.mdTren.tren;

public class Vagon {
    private final int ton;
    private int carga;

    public Vagon(int ton){
        this.ton = ton;
        carga = 0;
    }

    public int carga(int ton){
        int c = 0;
        if(ton > this.ton - this.carga){
            c = ton - (this.ton - this.carga);
            this.carga = this.ton;
        }else{
            this.carga = this.carga + ton;
        }
        return c;
    }

    public int descarga(int ton){
        int c = 0;

        if(ton > this.ton){
            c = ton - this.ton;
            this.carga = 0;
        }else{
            this.carga = this.carga - ton;
        }

        return c;

    }

    public int getCarga(){
        return carga;
    }

    public Integer getCapacidad(){
        return ton;
    }

    @Override
    public String toString(){
        return "V(" + carga + "/" + ton +")";
    }
}
