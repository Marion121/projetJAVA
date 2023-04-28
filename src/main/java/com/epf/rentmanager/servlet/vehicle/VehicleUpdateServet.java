package com.epf.rentmanager.servlet.vehicle;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
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

@WebServlet("/vehicle/modif")
public class VehicleUpdateServet extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
            Vehicle vehicleModif = vehicleService.findById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("vehicle", vehicleModif);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/vehicles/modif.jsp")
                .forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String constructeur = request.getParameter("manufacturer");
        int nbrPlace = Integer.parseInt(request.getParameter("seats"));
        System.out.println(constructeur);
        System.out.println(nbrPlace);
        try {
            Vehicle vehicleModif = vehicleService.findById(Integer.parseInt(request.getParameter("id")));
            vehicleModif.setConstructeur(constructeur);
            vehicleModif.setNb_places(nbrPlace);
            vehicleService.upadate(vehicleModif);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/vehicles");
    }


}
