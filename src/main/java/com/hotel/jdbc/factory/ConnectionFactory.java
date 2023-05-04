package com.hotel.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource datasource;
	
	public ConnectionFactory() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&ServerTimeZone=UTC ");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("guaricela123");
		
		
		this.datasource = pooledDataSource;
	}

	public Connection recuperaConexion(){
		try {
			return this.datasource.getConnection();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
			
	}

}


