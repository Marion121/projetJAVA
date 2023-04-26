package com.epf.rentmanager.validateur;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class validateurResa {

    public static ReservationDao reservationDao;
    public static ClientDao clientDao;
    public static VehicleDao vehicleDao;
    public static ReservationService reservationService ;

    public boolean pasReserverAujourdhui(Reservation reservation ){
        boolean dejaAujourdhui = false;
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationDao = new ReservationDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try{
            int id = reservation.getVehicle_id();
        List<Reservation> reservations = reservationService.findResaByVehicleId(id);
        for (Reservation resa : reservations){
                if(reservation.getDebut().isEqual(resa.getDebut())){
                    dejaAujourdhui = true;
                }
                if(reservation.getDebut().isAfter(resa.getDebut()) ){
                    if (reservation.getDebut().isBefore(resa.getFin()) || reservation.getDebut().isEqual(resa.getFin()) ) {
                        dejaAujourdhui = true;
                    }
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return(!dejaAujourdhui);
    }

    public boolean pas7jours(Reservation reservation){
        long diffDay = 0;
        try {
            diffDay =  ChronoUnit.DAYS.between(reservation.getDebut(),reservation.getFin());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (diffDay<7);
    }

    public boolean pas30jours(Reservation reservation ){
        Calendar calendar = Calendar.getInstance();
        long diffDay = 0;
        LocalDate debut = reservation.getDebut();
        LocalDate fin = reservation.getFin();
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationDao = new ReservationDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try{
            int id = reservation.getVehicle_id();
            List<Reservation> reservations = reservationService.findResaByVehicleId(id);
            for (Reservation resa : reservations){
                if(resa.getDebut().isBefore(debut) & resa.getFin().equals(debut.plusDays(-1))){
                    debut = resa.getDebut();
                }
                if(resa.getDebut().equals(fin.plusDays(1)) & resa.getFin().isAfter(fin)){
                    fin = resa.getFin();
                }
            }
            diffDay =  ChronoUnit.DAYS.between(debut,fin);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return(diffDay<30);
    }

    public boolean suppResaClient(int id_client ){
        boolean toutSupp = true;
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationDao = new ReservationDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try{
            List<Reservation> reservations = reservationService.SuppByClientId(id_client);
            for (Reservation resa : reservations){
                if(resa.getClient_id()==id_client){
                    toutSupp = false;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return(toutSupp);
    }

    public boolean suppResaVehicle(int  id_vehicle ){
        boolean toutSupp = true;
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationDao = new ReservationDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try{
            List<Reservation> reservations = reservationService.SuppByVehicleId( id_vehicle);
            for (Reservation resa : reservations){
                if(resa.getVehicle_id()== id_vehicle){
                    toutSupp = false;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return(toutSupp);
    }

}
