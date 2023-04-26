package com.epf.rentmanager.validateur;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

public class validateurClient {



    public static ClientDao clientDao;

    public static ClientService clientService ;


    public static boolean isLegal(Client client) {
        return
                client.getAge() >= 18;
    }

    public static boolean tailleNom(Client c ){
        return(c.getNom().length() > 3 & c.getPrenom().length() > 3);
    }

  public static boolean mailOK(Client c  ){
        boolean pastrouve = true;
        try {
            clientDao = new ClientDao();
            clientService = new ClientService(clientDao);
            List<Client> clients = clientService.findAll();
            for(Client client : clients ){
                if(c.getEmail().equals(client.getEmail())){
                    pastrouve = false;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return pastrouve;
    }

}
