package com.hotel.test;

import java.sql.Connection;

import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PruebaConeccion {
	
	final DataSource dataSource = null;
	
	
	public static void main(String[] args) throws SQLException{
		
		DataSource datasource;

		
		
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&ServerTimeZone=UTC ");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("guaricela123");
		pooledDataSource.setMaxPoolSize(10);

		datasource = pooledDataSource;
		
		System.out.println("Cerrando la conexion");
	}

	public Connection recuperaConexion() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
