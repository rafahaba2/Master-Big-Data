package org.uma.mbd.mdBusV2.buses;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Servicio {
    private String ciudad;
    private Set<Bus>buses;

    public Servicio(String ciudad){
        this.ciudad = ciudad;
        buses = new HashSet<>();
    }

    public void leeBuses(String file)throws FileNotFoundException{
        try (Scanner sc = new Scanner(new File(file))) {
            leeBuses(sc);
        }
    }

    public void leeBuses(Scanner sc) {
        while (sc.hasNextLine()) {
            String datosBus = sc.nextLine();
            try {
                Bus bus = stringToBus(datosBus);
                buses.add(bus);
            } catch (InputMismatchException e) {
                System.err.println("Error en dato numerico en "+datosBus);
            } catch (NoSuchElementException e) {
                System.err.println("Error, faltan datos en"+datosBus);
            }
        }
    }

    private Bus stringToBus(String datosBus) {
        try (Scanner sc = new Scanner(datosBus)) {
            sc.useDelimiter("[,+]+");
            int codB = sc.nextInt();
            String mat = sc.next();
            int codLin = sc.nextInt();
            Bus bus = new Bus(codB, mat);
            bus.setCodLinea(codLin);
            return bus;
        }
    }
    public Set<Bus> filtra(Criterio criterio, Comparator<Bus>c){
        Set<Bus>busesCriterio = new TreeSet<>(c);

        for (Bus bus:buses) {
           if(criterio.esSeleccionable(bus)){
               busesCriterio.add(bus);
           }
        }
        return busesCriterio;
    }

    public void guarda (String file, Criterio criterio, Comparator<Bus>c) throws FileNotFoundException{
        try(PrintWriter pw = new PrintWriter(file)){
            guarda(pw, criterio, c);
        }
    }

    public void guarda(PrintWriter pw, Criterio criterio, Comparator<Bus>c){
       pw.println(criterio);
        Set<Bus>busGuarda = filtra(criterio, c);
        for(Bus bus:busGuarda){
            pw.println(bus);
        }
    }

    public String getCiudad() {
        return ciudad;
    }

    public Set<Bus> getBuses() {
        return buses;
    }
}
