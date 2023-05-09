package com.hotel.jdbc.controller;



import com.hotel.jdbc.factory.ConnectionFactory;

import java.time.LocalDate;
import java.util.List;

import com.hotel.jdbc.DAO.HuespedesDAO;
import com.hotel.jdbc.modelo.Huesped;



public class huespedesController {
	
	private HuespedesDAO huespedesDAO;
	private Object reservaDAO;
	
	public huespedesController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedesDAO = new HuespedesDAO (factory.recuperaConexion());
	}

	public void guardar (Huesped nuevoHuesped) {
		
		this.huespedesDAO.guardar(nuevoHuesped);
		
	}

	public void actualizarH(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva, Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	public List<Huesped> listar() {
		return this.huespedesDAO.listar();
	}
	
	public List<Huesped> buscarId(String id) {
		return this.huespedesDAO.buscarId(id);
	}

	public void Eliminar(Integer id) {
		this.huespedesDAO.Eliminar(id);
	}

	
	
}
	
/*	public void actualizarReserva(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago, Integer id) {
        this.reservaDAO.Actualizar(fechaEntrada, fechaSalida, valor, formaPago, id);
    }
	
	public void Eliminar(Integer id) {
		this.reservaDAO.Eliminar(id);
	}

	public List<Huesped> mostrarHuesped() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Huesped> buscarHuesped(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}*/

	

	
	
