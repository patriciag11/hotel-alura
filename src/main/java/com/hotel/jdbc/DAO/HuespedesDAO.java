package com.hotel.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.jdbc.modelo.Huesped;
import com.hotel.jdbc.modelo.Reserva;

public class HuespedesDAO {

private Connection con; 
	
	public HuespedesDAO(Connection con) {
		this.con=con;
	}

	public void guardar(Huesped nuevoHuesped)  {

		try {
			String sql= "INSERT INTO huespedes (NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA)"
				       	+"VALUES (?,?,?,?,?,?)";
			
			final PreparedStatement statement = con
                    .prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
    		try(statement) {
    			statement.setString(1, nuevoHuesped.getNombre());
    			statement.setString(2, nuevoHuesped.getApellido());
    			statement.setDate(3, nuevoHuesped.getFecha_nacimiento());
    			statement.setString(4, nuevoHuesped.getNacionalidad());
    			statement.setString(5, nuevoHuesped.getTelefono());
    			statement.setInt(6, nuevoHuesped.getId_reserva());
			
    			statement.executeUpdate();
    			
    			final ResultSet resultset = statement.getGeneratedKeys();
    			
    			try (resultset) {
    				while (resultset.next()) {
                        nuevoHuesped.setId(resultset.getInt(1));
                        
                        System.out.println(String.format("Fue guardada la reserva: %s", nuevoHuesped));
                    }
    			}
    		}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}}
		

public List<Huesped> listar() {
	List<Huesped> huespedes = new ArrayList<Huesped>();

	try {
		String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM HUESPEDES";

		System.out.println(sql);

		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.execute();
			tranformarResultado(huespedes, statement);
		}
		return huespedes;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Huesped> buscarId(String id) {
	List<Huesped> huespedes = new ArrayList<Huesped>();
	      
	
	try {
		String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM HUESPEDES WHERE id = ? OR apellido LIKE ?";

		System.out.println(sql);

		try (PreparedStatement statement = con.prepareStatement(sql)){
		
			statement.setString(1, id);
			statement.setString(2, id);
			statement.execute();
			tranformarResultado(huespedes, statement);
		}
		return huespedes;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}		

	
/*
List<Reserva> reservas = new ArrayList<Reserva>();
    	
    	try {
            String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
            
            //System.out.println(sql);
            
            final PreparedStatement statement = con
                    .prepareStatement(sql);

            try (statement) {
                statement.execute();
                tranformarResultado(reservas, statement);
                }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}}
    
    public List<Reserva> buscarId(String id) {
    	List<Reserva> reservas = new ArrayList<Reserva>();
    	
    	try {
            String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id = ?";
            
            //System.out.println(sql);
            
            final PreparedStatement statement = con
                    .prepareStatement(sql);

            try (statement) {
            	statement.setString(1, id);
                statement.execute();
                tranformarResultado(reservas, statement);
                }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
         //int
    public void Actualizar(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago, Integer id) {
        try {
          
          	String sql = "UPDATE reservas SET "
                    + " fecha_entrada = ?, "
                    + " fecha_salida = ?,"
                    + " valor = ?,"
                    + " forma_de_pago = ?"
                    + " WHERE id = ?";
        	
        	final PreparedStatement statement = con.prepareStatement(sql);

            try (statement) {
                statement.setObject(1, java.sql.Date.valueOf(fechaEntrada));
                statement.setObject(2, java.sql.Date.valueOf(fechaSalida));
                statement.setString(3, valor);
                statement.setString(4, formaPago);
                statement.setInt(5, id);
                statement.execute();
                System.out.println("entrando a la base");
            } 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    public void Eliminar(Integer id) {
		try {
			String sql = "DELETE FROM reservas WHERE id = ?";
        	//try (statement) {
        		java.sql.Statement state = con.createStatement();
        		state.execute("SET FOREIGN_KEY_CHECKS=0");
        		PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, id);
                statement.execute();
                state.execute("SET FOREIGN_KEY_CHECKS=1");
                System.out.println("entrando a la base");
        		
			//} 
		} catch (SQLException e) {
			throw new RuntimeException("Animal" + e.getMessage() +e);
		}
	}
    
   
    


*/

private void tranformarResultado(List<Huesped> huespedes, PreparedStatement statement) throws SQLException {

	try(ResultSet resultSet = statement.getResultSet()){

	
		while (resultSet.next()) {
			
			Huesped nuevoHuesped = new Huesped (resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
			huespedes.add(nuevoHuesped);
		}
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}
}
