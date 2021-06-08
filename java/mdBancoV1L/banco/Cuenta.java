package org.uma.mbd.mdBancoV1L.banco;

public class Cuenta {
    private int numCuenta;
    private String titular;
    private double saldo;

    public Cuenta(String titular, int numCuenta, double saldo){
        this.titular = titular;
        this.numCuenta = numCuenta;
        this.saldo = saldo;
    }

    public Cuenta(String titular, int numCuenta){
        this(titular, numCuenta, 0);
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void ingreso(double ing){
        saldo += ing;
    }

    public double debito(double deb){
           saldo -= deb;
           return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String toString(){
        return "[(" + titular + "/" + numCuenta +") -> " + saldo + "]";
    }
}
