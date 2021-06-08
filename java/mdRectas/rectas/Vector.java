package org.uma.mbd.mdRectas.rectas;

public class Vector {
    private Punto extremo;

    public Vector(double x, double y){
        extremo = new Punto(x, y);
    }
    public Vector(Punto pto){
        extremo = new Punto(pto.getX(), pto.getY());
    }

    public Vector(Punto pto1, Punto pto2){
        extremo = new Punto(pto2.getX() - pto1.getX(), pto2.getY()-pto1.getY());
    }

    public double getComponenteX(){
        return extremo.getX();
    }

    public double getComponenteY(){
        return extremo.getY();
    }

    public double modulo(){
        return Math.sqrt(Math.pow(extremo.getX(),2) + Math.pow(extremo.getY(), 2));
    }

    public Vector ortogonal(){
        Vector v = new Vector(-extremo.getY(), extremo.getX());
        return v;
    }

    public boolean paraleloA(Vector v){
        return (v.getComponenteX()* extremo.getY() == v.getComponenteY()*extremo.getX())?true:false;
    }

    public Punto extremoDesde(Punto org){
        Punto pto = new Punto(extremo.getX(), extremo.getY());
        pto.trasladar(0 - org.getX(), 0);
        pto.trasladar(0, 0-org.getY());

        return pto;
    }

    public String toString(){
        return "V("+extremo.getX()+", "+extremo.getY()+")";
    }
}
