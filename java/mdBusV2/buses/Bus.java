package org.uma.mbd.mdBusV2.buses;

public class Bus implements Comparable<Bus>{
    private String matricula;
    private int codBus;
    private int codLinea;

    public Bus(int codBus, String matricula){
        this.codBus = codBus;
        this.matricula = matricula;
    }

    public void setCodLinea(int codLinea){
        this.codLinea = codLinea;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getCodBus() {
        return codBus;
    }

    public int getCodLinea() {
        return codLinea;
    }

    @Override
    public boolean equals(Object obj){
        boolean res = obj instanceof Bus;
        Bus b = res?(Bus)obj:null;

        return res && (codBus == b.codBus && b.matricula.equals(matricula));
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(codBus) + matricula.hashCode();
    }

    @Override
    public String toString(){
        return "Bus(" + codBus +","+matricula+","+codLinea+")";
    }

    @Override
    public int compareTo(Bus b){
        int i = 0;
        if(b.codLinea < this.codLinea){
            i = 1;
        }else if(b.codLinea == this.codLinea){
            i = 0;
        }else if(b.codLinea > this.codLinea){
            i = -1;
        }
        return i;
    }
}
