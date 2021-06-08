package org.uma.mbd.mdTren.tren;

import java.util.ArrayList;
import java.util.List;

public class Tren {

    int nVagones;
    private static ArrayList<Vagon> tren;


    public Tren(int nVagones, int ton) {
        this.nVagones = nVagones;
        tren = new ArrayList<>();
        addVagon(nVagones, ton);
    }

    private void addVagon(int nVagones, int ton) {
        int i = 0;

        while (i < nVagones) {
            tren.add(new Vagon(ton));
            i++;
        }
    }

    public void carga(int ton) {
        int i = 0;
        int cargaSig = 0;

        while (ton != 0) {
            cargaSig = tren.get(i).carga(ton);
            i++;
            ton = cargaSig;

            if ((ton != 0) && (nVagones == i)) {
                addVagon(1, tren.get(0).getCapacidad());
                nVagones++;
            }
        }
    }

    public void gasta(int ton) {
        int i = 0;
        int descargaSig = 0;

        while (i < nVagones && ton != 0) {
            descargaSig = tren.get(i).descarga(ton);
            i++;
            ton = descargaSig;

            if (i == nVagones && ton != 0) {
                throw new IllegalArgumentException("Toneladas a descargar mayores que las disponibles en el tren");
            }
        }
    }

    public void optimiza() {
        int i = 0;
        while (i < nVagones) {
            if (tren.get(i).getCarga() == 0) {
                tren.remove(i);
                i++;
                nVagones--;
            }
        }
    }

    @Override
    public String toString() {
        String cade = "Tren[";
        for (int i = 0; i < nVagones; i++) {
            if (i < nVagones - 1) {
                cade += tren.get(i).toString() + ", ";
            } else {
                cade += tren.get(i).toString() + "]";
            }
        }
        return cade;
    }

}
