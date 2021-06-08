package org.uma.mbd.mdCoches.coches;

public class Coche {
    private static double PIVA = 0.16;
    private final String nombre;
    private final double precio;

    public Coche(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public static void setPiva(double PIVA) {
        Coche.PIVA = PIVA;
    }

    public double precioTotal() {
        return precio + (precio * PIVA);
    }

    public String toString() {
        return nombre + " -> " + precioTotal();
    }
}
