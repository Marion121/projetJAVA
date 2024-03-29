package com.epf.rentmanager.servlet.client;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
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

@WebServlet("/users/modif")
public class ClientModifServet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
           Client clientModif = clientService.findById(Integer.parseInt(request.getParameter("id")));
           request.setAttribute("client", clientModif);
       } catch (ServiceException e) {
           e.printStackTrace();
       }
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/users/modif.jsp")
                .forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("last_name");
        String prenom = request.getParameter("first_name");
        String email = request.getParameter("email");
        LocalDate naissance = LocalDate.parse((request.getParameter("naissance")), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            Client clientModif = clientService.findById(Integer.parseInt(request.getParameter("id")));
            clientModif.setEmail(email);
            clientModif.setNom(nom);
            clientModif.setPrenom(prenom);
            clientModif.setNaissance(naissance);
            clientService.update(clientModif);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/users");
    }


}
