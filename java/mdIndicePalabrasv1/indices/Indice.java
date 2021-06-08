package org.uma.mbd.mdIndicePalabrasv1.indices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class Indice {
    protected List<String> texto;

    public Indice(){
        texto = new ArrayList<>();
    }

    public void agregarLinea(String texto){
        this.texto.add(texto);
    }

    public abstract void resolver(String delimitadores, Collection<String> noSignificativas);

    public abstract void presentarIndiceConsola();
}
