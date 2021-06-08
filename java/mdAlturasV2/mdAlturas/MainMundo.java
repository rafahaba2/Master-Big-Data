package org.uma.mbd.mdAlturasV2.mdAlturas;

import org.uma.mbd.mdAlturasV2.mdAlturas.alturas.EnContinente;
import org.uma.mbd.mdAlturasV2.mdAlturas.alturas.MenoresQue;
import org.uma.mbd.mdAlturasV2.mdAlturas.alturas.Mundo;
import org.uma.mbd.mdAlturasV2.mdAlturas.alturas.Pais;

import java.io.FileNotFoundException;
import java.util.Comparator;

public class MainMundo {
    public static void main(String args[]) throws FileNotFoundException {
        Mundo paises = new Mundo();
        paises.leePaises("recursos/mdAlturas/alturas.txt");

        Comparator<Pais> ordenAlturas = Comparator.comparingDouble(Pais::getAltura);
        Comparator<Pais>ordenAlfabetico = Comparator.comparing(Pais::getNombre);
        Comparator<Pais>ordenContinente = Comparator.comparing(Pais::getContinente).thenComparing(Pais::getNombre);
        Comparator<Pais>ordenNatural = Comparator.naturalOrder();
        Comparator<Pais> ordenaContNat = Comparator.comparing(Pais::getContinente).thenComparing(ordenNatural);

        for(Pais p:paises.comparaPais(ordenaContNat)){
            System.out.println(p);
        }

    }
}
