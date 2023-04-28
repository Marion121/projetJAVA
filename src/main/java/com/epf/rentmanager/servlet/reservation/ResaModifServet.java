package com.epf.rentmanager.servlet.reservation;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/rent/modif")
public class ResaModifServet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    ReservationService reservationService;

    @Autowired
    ClientService clientService;

    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Reservation resaModif = reservationService.findResa(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("clients", clientService.findAll());
            request.setAttribute("vehicles", vehicleService.findAll());
            request.setAttribute("reservation", resaModif);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/rents/modif.jsp")
                .forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int client_id = Integer.parseInt(request.getParameter("clients").toString());
        int vehicle_id = Integer.parseInt(request.getParameter("vehicles").toString());
        LocalDate debut = LocalDate.parse((request.getParameter("begin")), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fin = LocalDate.parse((request.getParameter("end")), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            Reservation resaModif = reservationService.findResa(Integer.parseInt(request.getParameter("id")));
            resaModif.setClient(clientService.findById(client_id));
            resaModif.setVehicle(vehicleService.findById(vehicle_id));
            resaModif.setDebut(debut);
            resaModif.setFin(fin);
            reservationService.update(resaModif);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/rents");
    }


}
