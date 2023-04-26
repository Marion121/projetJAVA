package com.epf.rentmanager.validateur;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class validateurVehicle {


    public boolean Nb_placesOK(Vehicle vehicle ){

        return(vehicle.getNb_places()>2 && vehicle.getNb_places()<10);
    }
}
