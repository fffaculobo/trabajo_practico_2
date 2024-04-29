package ar.edu.unju.fi.ejercicio3.main;

import ar.edu.unju.fi.ejercicio3.constantes.Provincia;

public class Main {
    public static void main(String[] args) {
        Provincia[] provincias = Provincia.values();
        for (Provincia provincia : provincias) {
            System.out.println(provincia);
            System.out.println();
        }
    }
}
