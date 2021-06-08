package org.uma.mbd.mdIndicePalabrasv1.indices;

import java.util.*;

public class IndicePosicionesEnLineas extends Indice{
    public Map<String, Map<Integer, Set<Integer>>>indice;

    public IndicePosicionesEnLineas(){
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
                int pos = 1;

                while(sc.hasNext()){
                    String p = sc.next().toLowerCase();
                    if(!palNoSignificativas.contains(p)){
                        Map<Integer, Set<Integer>>m = indice.get(p);
                        if(m == null){
                            m = new TreeMap<>();
                            indice.put(p, m);
                        }
                        Set<Integer> set = m.get(linea);
                        if(set == null){
                            set = new TreeSet<>();
                            m.put(linea, set);
                        }
                        set.add(pos);
                    }
                    pos++;
                }
            }
            linea++;
        }
    }

    @Override
    public void presentarIndiceConsola(){
        for(String i : indice.keySet()){
            System.out.println(i);
            for(int j : indice.get(i).keySet()){
                System.out.print("\t\t"+  j + "\t");
                for(int p: indice.get(i).get(j)){
                    System.out.print(p + ".");
                }
                System.out.println();
            }
        }
    }

}
