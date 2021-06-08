package org.uma.mbd.mdIndicePalabrasv1.indices;

import java.util.*;

public class Indice1aLinea extends Indice{
    private Map<String, Integer>indice;

    public Indice1aLinea(){
        super();
        indice = new TreeMap<>();
    }

    public void agregarLinea(String texto){
        super.agregarLinea(texto);
        indice.clear();
    }

    public void resolver(String delimitadores, Collection<String> noSignificativas){
        Set<String>palNoSignificativas = new HashSet<>();

        for(String p:noSignificativas){
            palNoSignificativas.add(p.toLowerCase());
        }

        int linea = 1;

        for(String l : texto){
            try(Scanner sc = new Scanner(l)){
                sc.useDelimiter(delimitadores);

                while(sc.hasNext()){
                    String pal = sc.next().toLowerCase();
                    if(!palNoSignificativas.contains(pal) && !indice.containsKey(pal)){
                            indice.put(pal, linea);
                    }
                }
            }
            linea++;
        }

    }


    @Override
    public void presentarIndiceConsola() {
        for(String i : indice.keySet()){
            System.out.println(i + "\t" + indice.get(i));
        }
    }

}
