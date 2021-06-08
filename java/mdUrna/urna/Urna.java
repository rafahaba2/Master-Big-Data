package org.uma.mbd.mdUrna.urna;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Random;

public class Urna {
     private static Random alea = new Random();
     private int blancas;
     private int negras;

     static public enum ColorBola{Blanca, Negra};

     public Urna(int blancas, int negras){
         if(blancas < 0 || negras < 0){
             throw new IllegalArgumentException("Numero de bolas negativas");
         }
         this.blancas = blancas;
         this.negras = negras;
     }

     public int totalBolas(){
         return this.blancas + this.negras;
     }

     public void ponerBlanca(){
         this.blancas++;
     }

     public void ponerNegra(){
         this.negras++;
     }

     public ColorBola extraerBola(){

         if(totalBolas() == 0){
             throw new NoSuchElementException("No hay bolas en la Urna");
         }

         int n_alea;
         n_alea = alea.nextInt(totalBolas())+1;

         ColorBola bolaSacada = null;

         if(n_alea <= blancas){
             bolaSacada = ColorBola.Blanca;
             blancas--;
         }else{
             bolaSacada = ColorBola.Negra;
             negras--;
         }

         return bolaSacada;
     }

     public String toString(){
         return "(N->" + this.negras + " B->"+this.blancas + ")";
     }

}
