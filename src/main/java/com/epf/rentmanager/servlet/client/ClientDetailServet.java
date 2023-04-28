package com.epf.rentmanager.servlet.client;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/detail")
public class ClientDetailServet  extends HttpServlet  {
    private static final long serialVersionUID = 1L;
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            final int id = Integer.parseInt(request.getParameter("id"));
            boolean trouve = false;
            List<Reservation> reservations = this.reservationService.findResaByClientId(id);
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            for (Reservation resa : reservations) {
                Vehicle v =vehicleService.findById(resa.getVehicle_id());
                for(Vehicle vehicle : vehicles){
                    if(v.getId()==vehicle.getId()){
                        trouve = true;
                    }
                }
                if(!trouve){
                    vehicles.add(v);
                }
                trouve = false;
            }
            request.setAttribute("client",this.clientService.findById(id));
            request.setAttribute("reservation", reservations );
            request.setAttribute("vehicles",vehicles);
            request.setAttribute("countResa", reservations.size());
            request.setAttribute("countVehicle", vehicles.size());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/users/details.jsp")
                .forward(request, response);
    }
}
