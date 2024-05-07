package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;

public class PagoEfectivo implements Pago{
	private Double montoPagado;
	private LocalDate fechaPago;
	
	public PagoEfectivo() {
		
	}
	public PagoEfectivo(Double montoPagado, LocalDate fechaPago) {
		super();
		this.montoPagado = montoPagado;
		this.fechaPago = fechaPago;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	@Override
	public void realizarPago(Double monto) {
		setMontoPagado(monto-monto*0.1);
	}

	@Override
	public void imprimirRecibo() {
		System.out.println("Fecha de pago: "+getFechaPago().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.println("Monto pagado: "+getMontoPagado());
	}

}
