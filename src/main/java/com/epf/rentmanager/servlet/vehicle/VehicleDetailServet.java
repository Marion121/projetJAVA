package com.epf.rentmanager.servlet.vehicle;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
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

@WebServlet("/vehicles/detail")
public class VehicleDetailServet extends HttpServlet {
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
            List<Reservation> reservations = this.reservationService.findResaByVehicleId(id);
            System.out.println("reservation : " +reservations);
            List<Client> clients = new ArrayList<Client>();
            boolean trouve = false;
            for (Reservation resa : reservations) {
                Client c =clientService.findById(resa.getClient_id());
                for(Client client : clients){
                    if(c.getId()==client.getId()){
                        trouve = true;
                    }
                }
                if(!trouve){
                    clients.add(c);
                }
            }
            request.setAttribute("vehicle",this.vehicleService.findById(id));
            request.setAttribute("reservations", reservations );
            request.setAttribute("clients",clients);
            request.setAttribute("countResa", reservations.size());
            request.setAttribute("countClient", clients.size());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp")
                .forward(request, response);
    }
}

