package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	
	private static VehicleDao instance = null;
	public VehicleDao() {}
	/*public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}*/

	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, nb_places=? WHERE id = ?;";
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,  nb_places FROM Vehicle;";

	private static final String COUNT_VEHICLE = "SELECT COUNT(id) AS nbrVehicles FROM Vehicle;";
	public long create(Vehicle vehicle) throws DaoException {
		long id = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, vehicle.getConstructeur());
			ps.setInt(2, vehicle.getNb_places());
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException();
		}
		return id;
	}


	public long update( Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE_QUERY);
			ps.setString(1, vehicle.getConstructeur());
			ps.setInt(2, vehicle.getNb_places());
			ps.setInt(3, vehicle.getId());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException();
		}
		return  vehicle.getId();
	}

	public long delete(Vehicle vehicle) throws DaoException {
		long modif;
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement ps = connexion.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setInt(1, vehicle.getId());
			modif = ps.executeUpdate();
			ps.close();
			connexion.close();
		} catch (SQLException e) {
			throw new DaoException();
		}
		return modif;
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

	public long Count() throws DaoException{
		int nbrVehicle =1;
		try{
			Connection co = ConnectionManager.getConnection();
			PreparedStatement ps = co.prepareStatement(COUNT_VEHICLE);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				nbrVehicle = rs.getInt("nbrVehicles");
			}
			return nbrVehicle;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	

}
