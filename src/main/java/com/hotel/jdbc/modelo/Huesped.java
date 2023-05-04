package com.hotel.jdbc.modelo;

import java.sql.Date;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String nacionalidad;
	private String telefono;
	private int id_reserva;
	
	

	public Huesped(String nombre, String apellido, Date fecha_nacimiento, Object nacionalidad, String telefono, String id_reserva ) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.fecha_nacimiento=fecha_nacimiento;
		this.nacionalidad=(String) nacionalidad;
		this.telefono=telefono;
		this.id_reserva= Integer.parseInt(id_reserva);
		
	}

	public Huesped(Integer id,String nombre, String apellido, Date fecha_nacimiento, Object nacionalidad, String telefono, Integer id_reserva ) {
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.fecha_nacimiento=fecha_nacimiento;
		this.nacionalidad=(String) nacionalidad;
		this.telefono=telefono;
		this.id_reserva= id_reserva;
		
	}
	


	public Integer getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	
	public String getNombre() {
		return nombre;
	}


	
	public String getApellido() {
		return apellido;
	}


	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	
	public String getNacionalidad() {
		return nacionalidad;
	}

	
	public String getTelefono() {
		return telefono;
	}


	public int getId_reserva() {
		return id_reserva;
	}


	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, nombre: %s, apellido: %s, fecha_nacimiento: %s, nacionalidad: %s, telefono: %s, id_reserva:%s }",
				this.id,
				this.nombre,
				this.apellido,
				this.fecha_nacimiento,
				this.nacionalidad,
				this.telefono,
				this.id_reserva);
	}
	
	
}

