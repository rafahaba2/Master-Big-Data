package org.uma.mbd.mdIndicePalabrasv1.indices;

import java.util.*;

public class IndiceLineas extends Indice{
    protected Map<String, Set<Integer>> indice;

    public IndiceLineas(){
        indice = new TreeMap<>();
    }

    public void agregarLinea(String texto){
        super.agregarLinea(texto);
        indice.clear();
    }

    public void resolver(String delimitadores, Collection<String>noSignificativas){
       Set<String> palNoSignificativas  = new HashSet<>();
        for(String p:noSignificativas){
            palNoSignificativas.add(p.toLowerCase());
        }

       int linea = 1;

       for(String l: texto){
           try(Scanner sc = new Scanner(l)){
               sc.useDelimiter(delimitadores);
               while(sc.hasNext()){
                   String pal = sc.next().toLowerCase();
                   if(!palNoSignificativas.contains(pal)){
                       Set<Integer> s = indice.get(pal);

                       if(s == null){
                           s= new TreeSet<>();
                           indice.put(pal, s);
                       }
                       s.add(linea);
                   }
               }
           }
           linea++;
       }

    }

    @Override
    public void presentarIndiceConsola(){
        for(String pal: indice.keySet()){
            System.out.print(pal + "\t");
            for(int i : indice.get(pal)){
                System.out.print(i + ".");
            }
            System.out.println();
        }
    }

}
