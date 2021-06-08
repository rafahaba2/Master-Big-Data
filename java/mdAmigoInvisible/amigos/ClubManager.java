package org.uma.mbd.mdAmigoInvisible.amigos;

import org.uma.mbd.mdMasterMindL.mdMastermind.MasterMindException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ClubManager{
    private String fEntrada;
    private String fSalida;
    private boolean consola;
    private String delimitadores;
    private Club club;

    public ClubManager(Club club){
        this.club = club;
    }

    public ClubManager setEntrada(String fEntrada, String delimitadores){
        this.fEntrada = fEntrada;
        this.delimitadores = delimitadores;
        return this;
    }

    public ClubManager setSalida(String salida){
        this.fSalida = salida;
        return this;
    }

    public ClubManager setConsola(boolean consola){
        this.consola = consola;
        return this;
    }

    private void verify(){
        if(fEntrada == null){
            throw new MasterMindException("No hay fichero de entrada");
        }else if(fSalida == null && !consola){
            throw new MasterMindException("No hay ninguna salida disponible");
        }
    }

    public boolean isConsola() {
        return consola;
    }

    public String getSalida() {
        return fSalida;
    }

    public void build() throws FileNotFoundException {
        verify();
        club.lee(this.fEntrada, this.delimitadores);

        club.hacerAmigos();

        if(consola && fSalida != null){
            System.out.println(club.socios);
            try {
                club.presentaAmigos(this.fSalida);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else if(!consola && fSalida != null){
            try {
                club.presentaAmigos(this.fSalida);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(consola && fSalida == null){
            System.out.println(club.socios);
        }
    }
}

