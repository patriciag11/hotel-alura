package com.hotel.jdbc.modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Reserva {
	
	private Integer id;
	private Date fechae;
	private Date fechas;
	private LocalDate fechaentrada;
	private LocalDate fechasalida;
	private String valor;
	private String formaPago;
	
	

	public Reserva(Date fechae, Date fechas, String valor, Object formaPago) {
		this.fechae= fechae;
		this.fechas= fechas;
		this.valor= valor;
		this.formaPago= (String) formaPago;
		
	}

	public Reserva(Integer id,Date fechae, Date fechas, String valor, Object formaPago) {
		this.id=id;
		this.fechae= fechae;
		this.fechas= fechas;
		this.valor= valor;
		this.formaPago= (String) formaPago;
		
	}

	public Reserva(int id, LocalDate fechae, LocalDate fechas, String valor, String formaPago) {
		this.id=id;
		this.fechaentrada= fechae;
		this.fechasalida= fechas;
		this.valor= valor;
		this.formaPago= formaPago;
	}

	public Integer getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public Date getFechae() {
		return fechae;
	}

	public Date getFechas() {
		return fechas;
	}

	public String getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, fecha entrada: %s, fecha salida: %s, valor: %s, forma de pago: %s}",
				this.id,
				this.fechae,
				this.fechas,
				this.valor,
				this.formaPago);
	}
	
	
}

