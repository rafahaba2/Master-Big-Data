package org.uma.mbd.mdJarras;

import org.uma.mbd.mdJarras.jarras.Mesa;

public class Main {
    public static void main(String[] args) {

        Mesa mesa = new Mesa(5, 7);

        mesa.llenaA();
        System.out.println(mesa);

        mesa.vuelcaAsobreB();
        System.out.println(mesa);

        mesa.llenaA();
        System.out.println(mesa);

        mesa.vuelcaAsobreB();
        System.out.println(mesa);

        mesa.vaciaB();
        System.out.println(mesa);

        mesa.vuelcaAsobreB();
        System.out.println(mesa);

        mesa.llenaA();
        System.out.println(mesa);

        mesa.vuelcaAsobreB();
        System.out.println(mesa);
    }
}