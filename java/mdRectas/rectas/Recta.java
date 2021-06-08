package org.uma.mbd.mdRectas.rectas;

public class Recta {
    private Vector direccion;
    private Punto pto;

    public Recta(Vector direccion, Punto pto){
        this.direccion = direccion;
        this.pto = pto;
    }

    public Recta(Punto pto1, Punto pto2){
        Vector v = new Vector(pto1, pto2);
        this.direccion = v;
        this.pto = pto1;
    }


    public boolean pasaPor(Punto pto){
        Vector v = new Vector(pto.getX()-this.pto.getX(),pto.getY()-this.pto.getY());
        return (v.paraleloA(direccion))?true:false;
    }

    public boolean pararalelaA(Recta recta){
        return (direccion.paraleloA(recta.direccion))?true:false;
    }

    public Recta paralelaPor(Punto pto){

        Recta recta = new Recta(direccion, pto);
        return recta;
    }

    public Recta perpendicularPor(Punto pto){
        Recta recta = new Recta(direccion.ortogonal(),pto);
        return recta;
    }

    public Recta interseccionPor(Punto pto){
        Recta recta = new Recta(direccion, pto);
        return recta;
    }

    public Punto interseccionCon(Recta recta){
        if(this.pararalelaA(recta)){
            throw new RuntimeException("Rectas paralelas");
        }

        double d = recta.direccion.getComponenteX()
                * this.direccion.getComponenteY()
                - this.direccion.getComponenteX()
                * recta.direccion.getComponenteY();

        double d1 = recta.direccion.getComponenteX()
                * recta.pto.getY()
                - recta.direccion.getComponenteY()
                * recta.pto.getX();

        double d2 = this.direccion.getComponenteX()
                * this.pto.getY()
                - this.direccion.getComponenteY()
                * this.pto.getX();

        double ox = (d1 * this.direccion.getComponenteX()
                - d2 * recta.direccion.getComponenteX())/d;
        double oy = (d1 * this.direccion.getComponenteY()
                - d2 * recta.direccion.getComponenteY())/d;

        Punto pto = new Punto(ox, oy);
        return pto;
    }

    public double distanciaDesde(Punto pto){
        Recta recta = perpendicularPor(pto);
        Punto punto;

        punto = interseccionCon(recta);

        return punto.distancia(pto);
    }
    public String toString(){
        return "R("+direccion.toString()+", "+pto.toString()+")";
    }
}
