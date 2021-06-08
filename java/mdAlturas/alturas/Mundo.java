package org.uma.mbd.mdAlturas.alturas;

import org.uma.mbd.mdBusV1.buses.Bus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Mundo {
    private List<Pais> paises;

    public Mundo(){
        paises = new ArrayList<>();
    }

    public List<Pais> getPaises(){
        return paises;
    }

    public void leePaises(String file) throws FileNotFoundException{
        try (Scanner sc = new Scanner(new File(file))) {
            leePaises(sc);
        }
    }

    private void leePaises(Scanner sc){
        while (sc.hasNextLine()) {
            String datosPais = sc.nextLine();
            try {
                Pais pais = stringToPais(datosPais);
                paises.add(pais);
            } catch (InputMismatchException e) {
                System.err.println("Error en dato numerico en "+datosPais);
            } catch (NoSuchElementException e) {
                System.err.println("Error, faltan datos en"+datosPais);
            }
        }
    }

    private Pais stringToPais(String datosPais) {
        try (Scanner sc = new Scanner(datosPais)) {
            sc.useDelimiter("[,]+");
            String pai = sc.next();
            sc.useLocale(Locale.ENGLISH);
            String cont = sc.next();
            double alt = sc.nextDouble();
            Pais pais = new Pais(pai, cont, alt);
            return pais;
        }

    }

    public List<Pais> selecciona (Seleccion sel){
        List<Pais>paisSel = new ArrayList<>();

        for(Pais pais:paises){
            if(sel.test(pais)){
                paisSel.add(pais);
            }
        }
        return paisSel;
    }

}
