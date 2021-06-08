package org.uma.mbd.mdBusV1.buses;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Servicio {
    private String ciudad;
    private List<Bus>buses;

    public Servicio(String ciudad){
        this.ciudad = ciudad;
        buses = new ArrayList<>();
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
    public List<Bus> filtra(Criterio criterio){
        List<Bus>busesCriterio = new LinkedList<>();

        for (int i = 0; i < buses.size(); i++) {
           if(criterio.esSeleccionable(buses.get(i))){
               Bus bus = new Bus(buses.get(i).getCodBus(), buses.get(i).getMatricula());
               busesCriterio.add(bus);
               bus.setCodLinea(buses.get(i).getCodLinea());
           }
        }
        return busesCriterio;
    }

    public void guarda (String file, Criterio criterio) throws FileNotFoundException{
        try(PrintWriter pw = new PrintWriter(file)){
            guarda(pw, criterio);
        }
    }

    public void guarda(PrintWriter pw, Criterio criterio){
       pw.println(criterio);
        List<Bus>busGuarda = filtra(criterio);
        for(Bus bus:busGuarda){
            pw.println(bus);
        }
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<Bus> getBuses() {
        return buses;
    }
}
