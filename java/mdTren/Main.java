package org.uma.mbd.mdTren;

import org.uma.mbd.mdTren.tren.Tren;

public class Main {
    public static void main(String[] args) {
        Tren tren = new Tren(5, 20);
        System.out.println("Se crea tren con  5 vagones de 20 ton "+ tren);
        tren.carga(71);
        System.out.println("Se cargan 71 ton " + tren);
        tren.gasta(63);
        System.out.println("Se gastan 63 ton " + tren);
        tren.carga(38);
        System.out.println("Se cargan 38 ton " + tren);
        tren.gasta(21);
        System.out.println("Se gastan 21 ton " + tren);
        tren.optimiza();
        System.out.println("Se optimiza el número de vagones " + tren);
        tren.carga(21);
        System.out.println("Se cargan 21 ton " + tren);
    }
}