package org.uma.mbd.mdMasterMindL.mdMastermind;

import java.util.Random;

public class MasterMind {
    public static final int TAMANO_POR_DEFECTO = 4;
    private Random alea = new Random();
    private int longitud;
    private String secreto;


    public MasterMind() {
        this(TAMANO_POR_DEFECTO);
    }

    public MasterMind(int longitud) {
        this.longitud = longitud;
        generaCombinacionSecreta(longitud);
    }

    private void generaCombinacionSecreta(int longitud) {

        int num;
        StringBuilder sec = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            num = alea.nextInt(10);

            while (sec.indexOf(Integer.toString(num)) != -1) {
                num = alea.nextInt(10);
            }
            sec.append(num);
        }
        secreto = sec.toString();
    }

    private boolean validaCombinacion(String cifras){
        
        return ((cifras.length() <= 9 && cifras.length() > 0) && (sonNumeros(cifras)) && noRepetidas(cifras));
    }

    private boolean noRepetidas(String s){
        int i = 0;
        String s1 = s.substring(i+1);
        String s2 = s.substring(i,i+1);
        while(!(s.substring(i+1).contains(s.substring(i,i+1))) && (i < s.length()-1)){
            i++;
        }
        return (i == s.length()-1);
    }


    private boolean sonNumeros (String c){
       return c.matches("[0-9]*");
    }

    public Movimiento intento(String cifras){
        Movimiento mov;

        if(validaCombinacion(cifras)){
             mov = new Movimiento(cifras, cifrasColocadas(cifras), cifrasDescolocadas(cifras));
        }else{
            throw new MasterMindException("La cadena tiene que ser numérica de tamaño "+
                    longitud +" y no puede tener números repetidos");
        }
        return mov;
    }

    private int cifrasColocadas(String cad){
        int colocadas = 0;

        for(int i = 0; i < secreto.length(); i++){
            if(secreto.charAt(i) == cad.charAt(i)){
                colocadas++;
            }
        }
        return colocadas;
    }

    private int cifrasDescolocadas(String cad){
        int descolocadas = 0;

       for(int i = 0; i < cad.length(); i++){
           if(secreto.contains(Character.toString(cad.charAt(i)))
                   && !(Character.toString(cad.charAt(i)).equals(Character.toString(secreto.charAt(i))))){
                    descolocadas++;
           }
       }
        return descolocadas;
    }

    public int getLongitud() {
        return longitud;
    }

    public String getSecreto() {
        return secreto;
    }

}