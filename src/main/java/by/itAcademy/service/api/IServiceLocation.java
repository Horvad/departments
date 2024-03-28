package by.itAcademy.service.api;

import by.itAcademy.core.DTO.location.DTOLocationCreate;
import by.itAcademy.core.DTO.location.DTOLocationDelete;
import by.itAcademy.core.DTO.location.DTOLocationOut;
import by.itAcademy.dao.entity.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceLocation {
    void add(DTOLocationCreate location);
    List<Location> getAll();
    Location getById(long id);
    void delete(DTOLocationDelete locationDelete);
    void update(DTOLocationOut locationUpdate);
    boolean exist(long id);
    boolean exist(String name);
}
