package org.uma.mbd.mdAlturas.alturas;

public class MenoresQue implements Seleccion{

    private double alturaMinima;

    public MenoresQue(double alturaMinima){
        this.alturaMinima = alturaMinima;
    }

    @Override
    public boolean test(Pais pais) {
        return this.alturaMinima < pais.getAltura();
    }
}
