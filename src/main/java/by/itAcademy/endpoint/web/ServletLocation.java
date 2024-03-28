package by.itAcademy.endpoint.web;

import by.itAcademy.core.DTO.location.DTOLocationCreate;
import by.itAcademy.core.DTO.location.DTOLocationDelete;
import by.itAcademy.core.DTO.location.DTOLocationOut;
import by.itAcademy.dao.entity.Location;
import by.itAcademy.service.api.IServiceLocation;
import by.itAcademy.service.fabric.ServiceLocationSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/location")
public class ServletLocation extends HttpServlet {
    private final ObjectMapper mapper;
    private final IServiceLocation serviceLocation;

    public ServletLocation() {
        this.serviceLocation = ServiceLocationSingleton.getInstance();
        this.mapper = new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        List<Location> locations = serviceLocation.getAll();
        List<DTOLocationOut> locationOuts = new ArrayList<>();
        for(Location location : locations){
            locationOuts.add(new DTOLocationOut(
                    location.getId(),
                    location.getName(),
                    location.getDateUpdate().toString()
            ));
        }
        Writer writer = resp.getWriter();
        writer.write(mapper.writeValueAsString(locationOuts));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DTOLocationCreate locationCreate = this.mapper.readValue(
                req.getInputStream(), DTOLocationCreate.class);
        serviceLocation.add(locationCreate);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DTOLocationOut locationUpdate = this.mapper.readValue(
                req.getInputStream(), DTOLocationOut.class);
        serviceLocation.update(locationUpdate);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DTOLocationDelete locationDelete = this.mapper.readValue(
                req.getInputStream(), DTOLocationDelete.class);
        serviceLocation.delete(locationDelete);
    }
}


