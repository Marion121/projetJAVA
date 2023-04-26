package com.epf.rentmanager.util;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validateur.validateurClient;
import com.epf.rentmanager.validateur.validateurResa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class reservationTest {

    public static validateurResa vResa ;
    public static ReservationDao reservationDao;
    public static ClientDao clientDao;
    public static VehicleDao vehicleDao;
    public static ClientService clientService ;
    public static VehicleService vehicleService;
    public static ReservationService reservationService ;

    @Test
    void pasReserverAujourdhui_should_return_true_when_pas_encore_reservé() {
        // Given
        vResa = new validateurResa();
       /*Reservation resa = new Reservation( 4, 1, LocalDate.of(2023,12,8), LocalDate.of(2023,12,12));
        reservationDao = new ReservationDao();
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try {
            reservationService.create(resa);
        }catch(ServiceException e){

        }*/
        Reservation isLegal = new Reservation( 4, 1, LocalDate.of(2023,12,22), LocalDate.of(2023,12,27));
        // Then
        assertTrue(vResa.pasReserverAujourdhui(isLegal));
    }

    @Test
    void pasReserverAujourdhui_should_return_false_when_deja_reservé_la_veille() {
        // Given
        vResa = new validateurResa();
        /*Reservation resa = new Reservation( 7, 7, LocalDate.of(2023,12,8), LocalDate.of(2023,12,12));
        reservationDao = new ReservationDao();
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try {
            reservationService.create(resa);
        }catch(ServiceException e){

        }*/
        Reservation isilLegal = new Reservation( 7, 7, LocalDate.of(2023,12,11), LocalDate.of(2023,12,17));
        // Then
        assertFalse(vResa.pasReserverAujourdhui(isilLegal));
    }

    @Test
    void pasReserverAujourdhui_should_return_false_when_deja_reservé() {
        // Given
        vResa = new validateurResa();
        /*Reservation resa = new Reservation( 3, 5, LocalDate.of(2023,12,8), LocalDate.of(2023,12,12));
        reservationDao = new ReservationDao();
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try {
            reservationService.create(resa);
        }catch(ServiceException e){
        }
         */
        Reservation isilLegal = new Reservation( 3, 5, LocalDate.of(2023,12,12), LocalDate.of(2023,12,17));
        // Then
        assertFalse(vResa.pasReserverAujourdhui(isilLegal));
    }

    @Test
    void pas7jours_should_return_false_when_resa_moins_de_7_jours() {
        // Given
        vResa = new validateurResa();
        Reservation isLegal = new Reservation( 3, 6, LocalDate.of(2023,12,12), LocalDate.of(2023,12,17));
        // Then
        assertTrue(vResa.pas7jours(isLegal));
    }
    @Test
    void pas7jours_should_return_false_when_resa_trop_long() {
        // Given
        vResa = new validateurResa();
        Reservation isilLegal = new Reservation( 3, 6, LocalDate.of(2023,12,12), LocalDate.of(2023,12,27));
        // Then
        assertFalse(vResa.pas7jours(isilLegal));
    }

    @Test
    void pas30jours_should_return_true_when_moins_de_30_jours() {
        // Given
        vResa = new validateurResa();
        /*Reservation resa = new Reservation( 3, 3, LocalDate.of(2023,12,6), LocalDate.of(2023,12,12));
        Reservation resa1 = new Reservation( 4, 3, LocalDate.of(2023,12,12), LocalDate.of(2023,12,18));
        Reservation resa2 = new Reservation( 5, 3, LocalDate.of(2023,12,18), LocalDate.of(2023,12,24));
        reservationDao = new ReservationDao();
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try {
            reservationService.create(resa);
            reservationService.create(resa1);
            reservationService.create(resa2);
        }catch(ServiceException e){

        }*/
        Reservation isLegal = new Reservation( 6, 3, LocalDate.of(2023,12,24), LocalDate.of(2023,12,27));
        // Then
        assertTrue(vResa.pas30jours(isLegal));
    }

    @Test
    void pas30jours_should_return_false_when_plus_de_30_jours() {
        // Given
        vResa = new validateurResa();
        /*Reservation resa = new Reservation( 5, 4, LocalDate.of(2023,12,19), LocalDate.of(2023,12,23));
        Reservation resa1 = new Reservation( 4, 4, LocalDate.of(2023,12,13), LocalDate.of(2023,12,18));
        Reservation resa2 = new Reservation( 3, 4, LocalDate.of(2023,12,6), LocalDate.of(2023,12,12));
        Reservation resa3 = new Reservation( 6, 4, LocalDate.of(2023,12,28), LocalDate.of(2023,12,30));
        Reservation resa4 = new Reservation( 7, 4, LocalDate.of(2023,12,2), LocalDate.of(2023,12,5));
        Reservation resa5 = new Reservation( 7, 4, LocalDate.of(2023,11,25), LocalDate.of(2023,12,1));
        reservationDao = new ReservationDao();
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
        reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
        try {
            reservationService.create(resa);
            reservationService.create(resa1);
            reservationService.create(resa2);
            reservationService.create(resa3);
            reservationService.create(resa4);
            reservationService.create(resa5);
        }catch(ServiceException e){

        }*/
        Reservation isilLegal = new Reservation( 6, 4, LocalDate.of(2023,12,24), LocalDate.of(2023,12,27));
        // Then
        assertFalse(vResa.pas30jours(isilLegal));
    }

    @Test
    void SuppByClientId_should_return_true_when_tout_est_supp() {
        // Given
        vResa = new validateurResa();
        /*clientDao = new ClientDao();
        clientService = new ClientService(clientDao);
        try {
            Client c = clientService.findById(2);
            clientService.delete(c);
        }catch ( ServiceException e){

        }*/
        // Then
        assertTrue(vResa.suppResaClient(2));
    }
    @Test
    void SuppByVehicleId_should_return_true_when_tout_est_supp() {
        // Given
        vResa = new validateurResa();
        /*vehicleDao = new VehicleDao();
        vehicleService = new VehicleService(vehicleDao);
        try {
            Vehicle v = vehicleService.findById(2);
            vehicleService.delete(v);
        }catch ( ServiceException e){

        }*/
        // Then
        assertTrue(vResa.suppResaVehicle(2));
    }
}
