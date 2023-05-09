package com.hotel.jdbc.DAO;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTextField;

import com.hotel.jdbc.factory.ConnectionFactory;

import com.hotel.jdbc.modelo.Reserva;

public class ReservaDAO {

	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva nuevaReserva) {

		try {
			String sql = "INSERT INTO reservas (FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO)" + "VALUES (?,?,?,?)";

			final PreparedStatement statement = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setDate(1, nuevaReserva.getFechae());
				statement.setDate(2, nuevaReserva.getFechas());
				statement.setString(3, nuevaReserva.getValor());
				statement.setString(4, nuevaReserva.getFormaPago());

				statement.executeUpdate();

				final ResultSet resultset = statement.getGeneratedKeys();

				try (resultset) {
					while (resultset.next()) {
						nuevaReserva.setId(resultset.getInt(1));

						// System.out.println(String.format("Fue guardada la reserva: %s",
						// nuevaReserva));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<Reserva>();

		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";

			System.out.println(sql);

			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.execute();
				tranformarResultado(reservas, statement);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		try {
			String sql = "select id, fecha_entrada, fecha_salida, valor, forma_pago from reservas where id=? ";

			System.out.println(sql);

			try (PreparedStatement statement = con.prepareStatement(sql)){
			
				statement.setString(1, id);
				statement.execute();
				tranformarResultado(reservas, statement);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	 public void Actualizar(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago, Integer id) {
	        try {
	          
	          	String sql = "UPDATE reservas SET "
	                    + " fecha_entrada = ?, "
	                    + " fecha_salida = ?,"
	                    + " valor = ?,"
	                    + " forma_pago = ?"
	                    + " WHERE id = ?";
	        	
	        	final PreparedStatement statement = con.prepareStatement(sql);

	            try (statement) {
	                statement.setObject(1, java.sql.Date.valueOf(fechaEntrada));
	                statement.setObject(2, java.sql.Date.valueOf(fechaSalida));
	                statement.setString(3, valor);
	                statement.setString(4, formaPago);
	                statement.setInt(5, id);
	                statement.execute();
	                System.out.println("modificando elemento en la base");
	            } 
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	    }
	


	
	
	

	public void Eliminar(Integer id) {
		try {
			String sql = "DELETE FROM reservas WHERE id = ?";
			// try (statement) {
			java.sql.Statement state = con.createStatement();
			state.execute("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			state.execute("SET FOREIGN_KEY_CHECKS=1");
			System.out.println("entrando a la base");

			// }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void tranformarResultado(List<Reserva> reservas, PreparedStatement statement) throws SQLException {

		
		
		try(ResultSet resultSet = statement.getResultSet()){

		
			while (resultSet.next()) {
				
				Reserva nuevaReserva = new Reserva(resultSet.getInt(1),resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5));
				reservas.add(nuevaReserva);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	

}
