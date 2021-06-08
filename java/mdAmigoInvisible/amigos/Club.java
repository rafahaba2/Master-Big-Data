package org.uma.mbd.mdAmigoInvisible.amigos;

import org.uma.mbd.mdBusV1.buses.Bus;
import org.uma.mbd.mdBusV1.buses.Criterio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Club{
    protected List<Persona> socios;

    public Club(){ socios = new ArrayList<>();
    }

    public void lee(String fEntrada, String delim) throws FileNotFoundException{
        try(Scanner sc = new Scanner(new File(fEntrada))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                leeSocios(linea, delim);
            }
        }
    }

    private void leeSocios(String linea, String delim){
        try (Scanner sc = new Scanner(linea)) {
            while(sc.hasNext()) {
                sc.useDelimiter(delim);
                String nombre = sc.next();
                creaSocioDesdeString(nombre);
            }
        }
    }

    protected void creaSocioDesdeString(String nombre){
        Persona p = new Persona(nombre);
        socios.add(p);
    }

    protected void hacerAmigos(){
        List<Integer> amigos = new ArrayList<>();
        for(int i = 0; i < socios.size(); i++) {
            amigos.add(i);
        }

        while(hayCoincidencias(amigos)) {
            Collections.shuffle(amigos);
        }

        for(int i = 0; i < socios.size(); i++) {
            socios.get(i).setAmigo(socios.get(amigos.get(i)));
        }
    }

    private static boolean hayCoincidencias(List<Integer> am) {

        boolean ok = false;
        int i = 0;

        while(!ok && i < am.size()){
             if(am.get(i)==i){
                 ok = true;
             }else{
                 i++;
             }
        }
        return ok;
    }

    public void presentaAmigos(String fSalida)throws FileNotFoundException{
        try(PrintWriter pw = new PrintWriter(fSalida)){
            presentaAmigos(pw);
        }
    }

    private void presentaAmigos(PrintWriter pw){
        Collections.sort(socios);
        for(Persona per: socios) {
            pw.println(per);
        }
    }

}
