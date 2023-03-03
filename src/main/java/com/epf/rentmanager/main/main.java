package com.epf.rentmanager.main;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;
import java.util.List;

public class main {

    public static void main(String[] arg) {

        try {
            //Client c  = new Client(5, "CHINEAUD", "Marion", "m.c@gmail.com", LocalDate.now());
            //ClientDao.getInstance().delete(c);
            System.out.println(ClientService.getInstance().findAll());
            System.out.println("ici : " + VehicleService.getInstance().findAll());
        }catch(ServiceException e){
            e.printStackTrace();
        }
    }
}
