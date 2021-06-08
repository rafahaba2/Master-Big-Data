package org.uma.mbd.mdMasterMindL;

import org.uma.mbd.mdMasterMindL.mdMastermind.MasterMind;
import org.uma.mbd.mdMasterMindL.mdMastermind.MasterMindException;
import org.uma.mbd.mdMasterMindL.mdMastermind.MasterMindMemoria;
import org.uma.mbd.mdMasterMindL.mdMastermind.Movimiento;

import java.util.Scanner;

public class TestMasterMind {

    public static void main(String[] args) throws MasterMindException {
		//MasterMind juego = new MasterMind();
        //System.out.println("Secreto: " + juego.getSecreto());
      MasterMindMemoria juego = new MasterMindMemoria();
      System.out.println("Secreto: " + juego.getSecreto());
//		System.out.println(juego.getSecreto());
        try (Scanner sc = new Scanner(System.in)) {
            boolean acertado = false;
            String cifras = null;
            int intento = 1;
            while (!acertado) {
                try {
                    System.out.print("Intento " + intento
                            + ". Introduce cifra: ");
                    cifras = sc.next();
                    Movimiento mov = juego.intento(cifras);
                    System.out.println("Intento " + intento + " " + mov);
                    acertado = mov.getColocadas() == juego.getLongitud();
                    intento++;
                } catch (MasterMindException e) {
                    System.out.println(cifras + " no validas: "
                            + e.getMessage());
                }
            }
            System.out.println("Correcto en " + (intento - 1) + " intentos");
			System.out.println("Movimientos :" + juego.movimientos());
        }
    }
}