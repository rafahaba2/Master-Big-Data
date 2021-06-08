package org.uma.mbd.mdBusV2;


import org.uma.mbd.mdBusV2.buses.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MainPrueba {
    public static void main(String[] args) {
        try {
            Servicio emt = new Servicio("Málaga");
            emt.leeBuses("recursos/mdBusV1/buses.txt");


            Comparator<Bus> cbon = Comparator.naturalOrder();

            Set<Bus> set = emt.filtra(new PorLinea(21), cbon);

            emt.guarda(new PrintWriter(System.out, true),
                    new EnMatricula("A"),
                    cbon);

        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado " + e.getMessage());
        }

    }
}
