package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

    private static ReservationDao instance = null;

    public ReservationDao() {
    }

   /* public static ReservationDao getInstance() {
        if (instance == null) {
            instance = new ReservationDao();
        }
        return instance;
    }*/

    private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
    private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
    private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
    private static final String FIND_RESERVATIONS_BY_RESA_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE id_R=?;";
    private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
    private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
    private static final String COUNT_RESA = "SELECT COUNT(id) AS nbrResas FROM Reservation;";


    public long create(Reservation reservation) throws DaoException {
        long id = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reservation.getClient_id());
            ps.setInt(2, reservation.getVehicle_id());
            ps.setDate(3, Date.valueOf(reservation.getDebut()));
            ps.setDate(4, Date.valueOf(reservation.getFin()));
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



    public long delete(int id_R) throws DaoException {
        long modif;
        try {
            Connection connexion = ConnectionManager.getConnection();
            PreparedStatement ps = connexion.prepareStatement(DELETE_RESERVATION_QUERY);
            ps.setInt(1, id_R);
            modif = ps.executeUpdate();
            ps.close();
            connexion.close();
        } catch (SQLException e) {
            throw new DaoException();
        }
        return modif;
    }

    public List<Reservation> findAll() throws DaoException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try {
            LocalDate date_debut = LocalDate.now();
            LocalDate date_fin = LocalDate.now();
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_client = rs.getInt("client_id");
                int id_vehicle = rs.getInt("vehicle_id");
                date_debut = rs.getDate("debut").toLocalDate();
                date_fin = rs.getDate("fin").toLocalDate();
                reservations.add(new Reservation(id, id_client, id_vehicle, date_debut, date_fin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public long count() throws DaoException {
        int nbrResa = 1;
        try {
            Connection co = ConnectionManager.getConnection();
            PreparedStatement ps = co.prepareStatement(COUNT_RESA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbrResa = rs.getInt("nbrResas");
            }
            return nbrResa;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Reservation findResa(int id) throws DaoException {
        try {
            int id_vehicle = 0;
            int id_client = 0;
            LocalDate date_debut = LocalDate.now();
            LocalDate date_fin = LocalDate.now();
            Reservation resa = new Reservation(id, id_client, id_vehicle, date_debut, date_fin);
            Connection connexion = ConnectionManager.getConnection();
            PreparedStatement ps = connexion.prepareStatement(FIND_RESERVATIONS_BY_RESA_QUERY);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                id_vehicle = resultSet.getInt("id_vehicle");
                id_client = resultSet.getInt("client_id");
                date_debut = resultSet.getDate("debut").toLocalDate();
                date_fin = resultSet.getDate("fin").toLocalDate();
            }
            return new Reservation(id, id_client, id_vehicle, date_debut, date_fin);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public List<Reservation> findResaByVehicleId(int id_vehicle) throws DaoException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try {
            int id = 0;
            int id_client = 0;
            LocalDate date_debut = LocalDate.now();
            LocalDate date_fin = LocalDate.now();
            Connection connexion = ConnectionManager.getConnection();
            PreparedStatement ps = connexion.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
            ps.setLong(1, id_vehicle);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                id_client = resultSet.getInt("client_id");
                date_debut = resultSet.getDate("debut").toLocalDate();
                date_fin = resultSet.getDate("fin").toLocalDate();
                reservations.add(new Reservation(id, id_client, id_vehicle, date_debut, date_fin));
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public List<Reservation> findResaByClientId(int id_client) throws DaoException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try {
            int id = 0;
            int id_vehicle = 0;
            LocalDate date_debut = LocalDate.now();
            LocalDate date_fin = LocalDate.now();
            Connection connexion = ConnectionManager.getConnection();
            PreparedStatement ps = connexion.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
            ps.setLong(1, id_client);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                id_vehicle = resultSet.getInt("vehicle_id");
                date_debut = resultSet.getDate("debut").toLocalDate();
                date_fin = resultSet.getDate("fin").toLocalDate();
                reservations.add(new Reservation(id, id_client, id_vehicle, date_debut, date_fin));
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

}
