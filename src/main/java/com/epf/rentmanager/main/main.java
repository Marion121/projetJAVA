package com.epf.rentmanager.main;

import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class main {

    public static void main(String[] arg) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);
        Reservation resa = new Reservation(27,4, 4, LocalDate.of(2023,12,12), LocalDate.of(2023,12,18));

        //Client c  = new Client(5, "CHINEAUD", "Ma", "m.c@gmail.com", LocalDate.of(2002,1,12));
            //Reservation r = new Reservation(1,1,1, LocalDate.of(2011,11,11), LocalDate.of(2012,12,12));
        try{
            Reservation r = new Reservation(8,1, LocalDate.of(2011,10,11), LocalDate.of(2011,10,13));
            reservationService.create(r);

            System.out.println(r);
        List<Reservation> reservations = reservationService.findAll();
        for (int i=0; i<reservations.size();i++ ){
            Reservation re  = reservations.get(i);
        }
        System.out.println("reservation : "+reservations);
        }catch(ServiceException e){
            System.out.println("erreur");
        }
       // System.out.println(c.tailleNom());
       /* try {

        }catch(ServiceException e){
            System.out.println("erreur");
        }*/
           // System.out.println("ici : " + VehicleService.getInstance().findAll());

    }
}
