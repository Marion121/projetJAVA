package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.validateur.validateurClient;
import org.springframework.stereotype.Service;

import static com.epf.rentmanager.validateur.validateurResa.reservationDao;
import static com.epf.rentmanager.validateur.validateurResa.vehicleDao;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static validateurClient vClient ;
	public static ReservationService reservationService ;
	public static ClientService instance;

	/*public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}*/
	public ClientService(ClientDao clientDao){
		this.clientDao = clientDao;

	}
	
	
	public long create(Client client) throws ServiceException {
		try{
			if( vClient.isLegal(client) & vClient.tailleNom(client) & vClient.mailOK(client)) {
				return this.clientDao.create(client);
			}
			else{
				return 0;
			}
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long update(Client client) throws ServiceException {
		try{
			if( vClient.isLegal(client) & vClient.tailleNom(client) & vClient.mailOK(client)) {
				return this.clientDao.update(client);
			}
			else{
				return 0;
			}
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long delete(Client client) throws ServiceException {
		reservationDao = new ReservationDao();
		clientDao = new ClientDao();
		vehicleDao = new VehicleDao();
		reservationService = new ReservationService(reservationDao,clientDao,vehicleDao);
		reservationService.SuppByClientId(client.getId());
		try{
			return this.clientDao.delete(client);
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Client findById(int id) throws ServiceException {
		if(id<=0){
			throw new ServiceException("ERREUR : ID non valide");
		}
		try{
			return this.clientDao.findById(id);
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		try{
			return this.clientDao.findAll();
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}


	public long Count() throws ServiceException {
		try{
			return this.clientDao.Count();
		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
}
