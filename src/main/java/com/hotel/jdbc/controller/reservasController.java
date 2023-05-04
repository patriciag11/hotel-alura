package com.hotel.jdbc.controller;



import com.hotel.jdbc.factory.ConnectionFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hotel.jdbc.DAO.ReservaDAO;
import com.hotel.jdbc.modelo.Reserva;


public class reservasController {
	
	private ReservaDAO reservaDAO;
	
	public reservasController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO (factory.recuperaConexion());
	}

	public void guardar (Reserva nuevaReserva) {
		
		this.reservaDAO.guardar(nuevaReserva);
		
	}
	
	public List<Reserva> listar() {
		return this.reservaDAO.listar();
	}

	public List<Reserva> buscarId(String id) {
		
		return this.reservaDAO.buscarId(id);
	}

	
	
	public void actualizarReserva(String fechae, String fechas, String valor, String formaPago, Integer id) {
		  this.reservaDAO.Actualizar(fechae, fechas, valor, formaPago, id);
		
	}
	
	public void Eliminar(Integer id) {
		this.reservaDAO.Eliminar(id);
	}

}

	

	
	
