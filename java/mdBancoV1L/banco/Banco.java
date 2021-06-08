package org.uma.mbd.mdBancoV1L.banco;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Banco {
    private static final int PRIMER_NUM_CTA = 1001;
    private String nombre;
    private int snc;
    private List<Cuenta> ctas;

    public Banco(String nombre){
        this.nombre = nombre;
        ctas = new LinkedList<>();
        snc = PRIMER_NUM_CTA;
    }



    public int abrirCuenta(String nom, int saldo){
        int nc = snc;
        Cuenta cuenta = new Cuenta(nom,snc ,saldo);
        snc++;
        ctas.add(cuenta);

        return nc;
    }

    public int abrirCuenta(String nom) {
        return abrirCuenta(nom, 0);
    }

    public void cerrarCuenta(int nc){
        ctas.remove(posicionCuenta(nc));
    }

    private int posicionCuenta(int nc){

        int i = 0;

       while((i < ctas.size()) && (nc != ctas.get(i).getNumCuenta())){
           i++;
       }

       if(i ==  ctas.size()){
           throw new RuntimeException("No existe la cuenta dada");
       }

       return i;
    }

    public void ingreso(int nc, double ingreso){
        ctas.get(posicionCuenta(nc)).ingreso(ingreso);
    }

    public void debito(int nc, double debito){

        ctas.get(posicionCuenta(nc)).debito(debito);

        if(ctas.get(posicionCuenta(nc)).getSaldo() < 0){
            ctas.get(posicionCuenta(nc)).ingreso(-ctas.get(posicionCuenta(nc)).getSaldo());
        }
    }

    public double saldo(int nc){
        return ctas.get(posicionCuenta(nc)).getSaldo();
    }

    public double transferencia(int nc1, int nc2, double trans){
        if(ctas.get(posicionCuenta(nc1)).getSaldo() < trans){
            throw new RuntimeException("La cuenta: "+nc1+" no tiene suficiente saldo");
        }

        ctas.get(posicionCuenta(nc1)).debito(trans);
        ctas.get(posicionCuenta(nc2)).ingreso(trans);

        return trans;
    }

    @Override
    public String toString(){
        return nombre + ": " + ctas;
    }
}

