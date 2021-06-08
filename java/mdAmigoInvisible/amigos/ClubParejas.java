package org.uma.mbd.mdAmigoInvisible.amigos;

import java.util.*;

public class ClubParejas extends Club {
    private Set<Pareja> pareja;

    public ClubParejas() {
        pareja = new HashSet<>();
    }

    @Override
    protected void creaSocioDesdeString(String nombre) {
        String p[];

        p = nombre.split("-");
            Persona p1 = new Persona(p[0]);
            socios.add(p1);

            if (p.length == 2) {
            Persona p2 = new Persona(p[1]);
            
            socios.add(p2);

            Pareja par = new Pareja(p1, p2);
            pareja.add(par);
        }


    }

    @Override
    protected void hacerAmigos() {
        super.hacerAmigos();
        while(coincidePareja()){
            super.hacerAmigos();
        }
    }

    private boolean coincidePareja(){
        boolean coincide = false;
        int i = 0;
        while(!coincide && i < socios.size()){
              Persona p1 = new Persona(socios.get(i).getNombre());
              Persona p2 = new Persona(socios.get(i).getAmigo().getNombre());
              Pareja p = new Pareja(p1, p2);

            if(pareja.contains(p)){
                coincide = true;
            }else{
                i++;
            }
        }
        return coincide;
    }


}
