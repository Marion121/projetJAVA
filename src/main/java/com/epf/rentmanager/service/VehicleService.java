package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.validateur.validateurClient;
import com.epf.rentmanager.validateur.validateurVehicle;
import org.springframework.stereotype.Service;

import static com.epf.rentmanager.validateur.validateurResa.reservationDao;
import static com.epf.rentmanager.validateur.validateurResa.vehicleDao;

@Service
public class VehicleService {

	public static validateurVehicle vVehicle ;

	public static ReservationService reservationService ;
	private VehicleDao vehicleDao;
	private ClientDao clientDao;
	public static VehicleService instance;
	
	public VehicleService(VehicleDao vehicleDao) {
		vVehicle = new validateurVehicle();
		this.vehicleDao = vehicleDao;
	}
	
	/*public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}*/
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		try{
			if(vVehicle.Nb_placesOK(vehicle)) {
				return this.vehicleDao.create(vehicle);
			}else {
				return 0;
			}
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long upadate(Vehicle vehicle) throws ServiceException {
		try{
			if(vVehicle.Nb_placesOK(vehicle)) {
				return this.vehicleDao.update(vehicle);
			}else {
				return 0;
			}
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		reservationDao = new ReservationDao();
		clientDao = new ClientDao();
		vehicleDao = new VehicleDao();
		reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
		reservationService.SuppByVehicleId(vehicle.getId());
		try{
			return this.vehicleDao.delete(vehicle);
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Vehicle findById(int id) throws ServiceException {
		if(id<=0){
			throw new ServiceException("ERREUR : ID non valide");
		}
		try{
			return this.vehicleDao.findById(id);
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}


	public List<Vehicle> findAll() throws ServiceException {
		try{
			return this.vehicleDao.findAll();
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long Count() throws ServiceException {
		try{
			return this.vehicleDao.Count();
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
}
