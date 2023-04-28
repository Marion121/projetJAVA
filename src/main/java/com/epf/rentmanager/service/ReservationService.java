package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.validateur.validateurResa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReservationService {
    private ReservationDao reservationDao;
    private ClientDao clientDao;
    public static validateurResa vResa ;
    private VehicleDao vehicleDao;
    @Autowired
    VehicleService vehicleService;
    public static ReservationService instance;

    public ReservationService(ReservationDao reservationDao, ClientDao clientDao, VehicleDao vehicleDao) {
        this.clientDao = clientDao;
        this.reservationDao= reservationDao;
        this.vehicleDao = vehicleDao;
        vResa = new validateurResa();
    }

   /* public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }*/

    public long create(Reservation reservation) throws ServiceException {
        try{
            if(vResa.pasReserverAujourdhui(reservation) & vResa.pas7jours(reservation) & vResa.pas30jours(reservation)){
                return reservationDao.create(reservation);
            }
            return 0;
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long update(Reservation reservation) throws ServiceException {
        try{
            if(vResa.pasReserverAujourdhui(reservation) & vResa.pas7jours(reservation) & vResa.pas30jours(reservation)){
                return reservationDao.update(reservation);
            }
            return 0;
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(int id_R) throws ServiceException {
        try{
            return reservationDao.delete(id_R);
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{
            reservations = reservationDao.findAll();
            for(int i =0; i<reservations.size(); i++){
                Client client = this.clientDao.findById(reservations.get(i).getClient_id());
                reservations.get(i).setClient(client);
                Vehicle vehicle = this.vehicleDao.findById(reservations.get(i).getVehicle_id());
                reservations.get(i).setVehicle(vehicle);
            }
            return reservations ;
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }


    public Reservation findResa(int id) throws ServiceException {
        try{
            Reservation reservation = reservationDao.findResa(id);
            return reservation ;
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    public List<Reservation>  findResaByClientId(int id_client) throws ServiceException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{
            reservations = reservationDao.findResaByClientId(id_client);
            for(int i =0; i<reservations.size(); i++){
                Client client = this.clientDao.findById(reservations.get(i).getClient_id());
                reservations.get(i).setClient(client);
                Vehicle vehicle = this.vehicleDao.findById(reservations.get(i).getVehicle_id());
                reservations.get(i).setVehicle(vehicle);
            }
            return reservations ;
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation>  SuppByClientId(int id_client) throws ServiceException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{
            reservations = reservationDao.findResaByClientId(id_client);
            for(int i =0; i<reservations.size(); i++){
                reservationDao.delete(reservations.get(i).getId_R());
            }
            return reservations ;
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation>  SuppByVehicleId(int id_vehicle) throws ServiceException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{
            reservations = reservationDao.findResaByVehicleId(id_vehicle);
            for(int i =0; i<reservations.size(); i++){
                reservationDao.delete(reservations.get(i).getId_R());
            }
            return reservations ;
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation>  findResaByVehicleId(int id_vehicle) throws ServiceException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{
            reservations = reservationDao.findResaByVehicleId(id_vehicle);
            for(int i =0; i<reservations.size(); i++){
                Client client = this.clientDao.findById(reservations.get(i).getClient_id());
                reservations.get(i).setClient(client);
                Vehicle vehicle = this.vehicleDao.findById(reservations.get(i).getVehicle_id());
                reservations.get(i).setVehicle(vehicle);
            }
            return reservations ;
        }catch (DaoException e ){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long count() throws ServiceException {
        try{
            return this.reservationDao.count();
        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
}
