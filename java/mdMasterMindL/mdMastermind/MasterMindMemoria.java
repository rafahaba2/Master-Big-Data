package org.uma.mbd.mdMasterMindL.mdMastermind;

import java.util.ArrayList;
import java.util.List;

public class MasterMindMemoria extends MasterMind{
    private List<Movimiento> movimientos;

    public MasterMindMemoria(){
        super(TAMANO_POR_DEFECTO);
        movimientos = new ArrayList<>();
    }

    public MasterMindMemoria(int longitud){
        super(longitud);
        movimientos = new ArrayList<>();
    }

    @Override
    public Movimiento intento(String cifras){
        Movimiento mov = super.intento(cifras);
        if(movimientos.contains(mov)){
            throw new MasterMindException("Cifra ya introducida");
        }else{
            movimientos.add(mov);
        }
        return mov;
    }

    public List<Movimiento> movimientos(){
        return movimientos;
    }


}
