package br.mil.defesa.sisgeodef.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccessService {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;   

	@Value("${spring.datasource.username}")
	private String dbUserName;   
	
	@Value("${spring.datasource.password}")
	private String dbPassword;   	
	

	public void createTables() {
		
		String sql = "CREATE TABLE POINT_TABLE (Id integer PRIMARY KEY, "
				+ "x double, "
				+ "y double, "
				+ "z double, "
				+ "r int, "
				+ "g int, "
				+ "b int)";
		
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword); PreparedStatement preparedStatement = connection.prepareStatement( sql )) {
        	/*
            preparedStatement.setString(1, poiRequest.getRoutegeom());
            preparedStatement.setString(2, poiRequest.getCriteria() );
            preparedStatement.setString(3, poiRequest.getSource() );
            preparedStatement.setInt(4, Integer.parseInt( poiRequest.getDistance() ) );
        	*/
        	
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }		
	}

}
