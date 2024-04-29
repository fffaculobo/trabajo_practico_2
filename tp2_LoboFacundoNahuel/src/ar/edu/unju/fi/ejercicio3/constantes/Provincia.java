package ar.edu.unju.fi.ejercicio3.constantes;

public enum Provincia {
	JUJUY(188077, 95146),
    SALTA(569879, 753159),
    TUCUMAN(56598, 85246),
    CATAMARCA(1328160, 654789),
    LA_RIOJA(649495, 85213),
    SANTIAGO_DEL_ESTERO(119597, 15963);

    private int cantidadPoblacion;
    private int superficie;

    Provincia(int cantidadPoblacion, int superficie) {
        this.cantidadPoblacion = cantidadPoblacion;
        this.superficie = superficie;
    }

    public int getCantidadPoblacion() {
        return cantidadPoblacion;
    }

    public void setCantidadPoblacion(int cantidadPoblacion) {
        this.cantidadPoblacion = cantidadPoblacion;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double calcularDensidadPoblacional() {
        return (double) cantidadPoblacion / superficie;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "nombre=" + name() +
                ", cantidadPoblacion=" + cantidadPoblacion +
                ", superficie=" + superficie +
                ", densidadPoblacional=" + calcularDensidadPoblacional() +
                '}';
    }
}
