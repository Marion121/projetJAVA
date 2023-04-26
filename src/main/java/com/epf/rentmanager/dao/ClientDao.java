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
public class ClientDao {

    private static ClientDao instance = null;

    public ClientDao() {
    }

   /* public static ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDao();
        }
        return instance;
    }*/

    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
    private static final String COUNT_CLIENTS = "SELECT COUNT(id) AS nbrClients FROM Client;";
    public long create(Client client) throws DaoException {
        long id = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setDate(4, Date.valueOf(client.getNaissance()));
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

    public long delete(Client client) throws DaoException {
        long modif;
        try {
            Connection connexion = ConnectionManager.getConnection();
            PreparedStatement ps = connexion.prepareStatement(DELETE_CLIENT_QUERY);
            ps.setInt(1, client.getId());
            modif = ps.executeUpdate();
            ps.close();
            connexion.close();
        } catch (SQLException e) {
            throw new DaoException();
        }
        return modif;
    }

    public Client findById(int id) throws DaoException {
        try {
            String nom ="";
            String prenom = "";
            String email = "";
            LocalDate date = LocalDate.now();
            Connection connexion = ConnectionManager.getConnection();
            PreparedStatement ps = connexion.prepareStatement(FIND_CLIENT_QUERY);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                nom = resultSet.getString("nom");
                prenom = resultSet.getString("prenom");
                email = resultSet.getString("email");
                date = resultSet.getDate("naissance").toLocalDate();
            }
            return new Client(id, nom, prenom, email, date);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<Client>();
        try {
            LocalDate date = LocalDate.now();
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                date = rs.getDate("naissance").toLocalDate();

                clients.add(new Client(id,nom, prenom, email, date));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return clients;

    }

   public long Count() throws DaoException{
        int nbrClient =1;
        try{
            Connection co = ConnectionManager.getConnection();
            PreparedStatement ps = co.prepareStatement(COUNT_CLIENTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                nbrClient= rs.getInt("nbrClients");
            }
            return nbrClient;
        }catch(SQLException e){
            e.printStackTrace();
       }
        return 0;
    }

}
