package org.uma.mbd.mdUrna;

import org.uma.mbd.mdUrna.urna.Urna;

public class Main {
    public static void main(String[] args) {
        int nBlancas = Integer.parseInt(args[0]);
        int nNegras  = Integer.parseInt(args[1]);

        Urna urna = new Urna(nBlancas, nNegras);
        Urna.ColorBola bolaSacada1 = null;
        Urna.ColorBola bolaSacada2 = null;
        System.out.println("Urna inicio: "+urna);

        while(urna.totalBolas() > 1){
             bolaSacada1 = urna.extraerBola();
             bolaSacada2 = urna.extraerBola();

            if(bolaSacada1 == Urna.ColorBola.Blanca){
                System.out.println("Sacamos blanca");
            }else{
                System.out.println("Sacamos negra");
            }

            if(bolaSacada2 == Urna.ColorBola.Blanca){
                System.out.println("Sacamos blanca");
            }else{
                System.out.println("Sacamos negra");
            }

             if(bolaSacada1 == bolaSacada2){
                 System.out.println("Ponemos blanca");
                 urna.ponerBlanca();
             }else{
                 System.out.println("Ponemos negra");
                 urna.ponerNegra();
             }

            System.out.println(urna.toString());
            System.out.println();
        }

        System.out.println(urna.toString());

        bolaSacada1 = urna.extraerBola();

        if(bolaSacada1 == Urna.ColorBola.Blanca){
            System.out.println("La ultima bola es blanca");
        }else{
            System.out.println("La ultima bola es negra");
        }
    }
}
