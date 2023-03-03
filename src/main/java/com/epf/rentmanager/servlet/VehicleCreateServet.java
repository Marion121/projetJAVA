package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/vehicles/create")
public class VehicleCreateServet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp")
                .forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String constructeur = request.getParameter("manufacturer");
            int nbrPlace = Integer.parseInt(request.getParameter("seats"));
            VehicleService.getInstance().create(new Vehicle(constructeur,nbrPlace ));

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/vehicles");
        /*this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp")
                .forward(request, response);*/
    }


}