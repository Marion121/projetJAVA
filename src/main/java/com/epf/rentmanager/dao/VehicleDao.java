package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,  nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public Vehicle findById(int id) throws DaoException {
		try {
			String constructeur ="";
			int nbrPlaces = 0;
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement ps = connexion.prepareStatement(FIND_VEHICLE_QUERY);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				 constructeur = rs.getString("constructeur");
				nbrPlaces = rs.getInt("nb_Places");
			}
			return new Vehicle(id, constructeur, nbrPlaces);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);
			while(rs.next()){
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbrPlaces = rs.getInt("nb_Places");

				vehicles.add(new Vehicle(id, constructeur, nbrPlaces));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return vehicles;
		
	}
	

}
