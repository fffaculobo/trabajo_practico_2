package ar.edu.unju.fi.ejercicio5.model;
import ar.edu.unju.fi.ejercicio5.interfaces.Pago	;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PagoTarjeta implements Pago {
	private String numeroTarjeta;
	private LocalDate fechaDePago;
	private Double montoPagado;
	
    public PagoTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

	public PagoTarjeta(String numeroTarjeta, LocalDate fechaDePago, Double montoPagado) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.fechaDePago = fechaDePago;
		this.montoPagado = montoPagado;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public LocalDate getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(LocalDate fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	@Override
	public void realizarPago(Double monto) {
		setMontoPagado(monto+monto*0.15);
	}

	@Override
	public void imprimirRecibo() {
		System.out.println("Numero de tarjeta: "+getNumeroTarjeta());
		System.out.println("Fecha de pago: "+getFechaDePago().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.println("Monto pagado: "+getMontoPagado());
	}

}

