package by.itAcademy.dao.ds.api;

import by.itAcademy.dao.entity.Location;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface IDaoLocation {
    void add(Location location);
    List<Location> getAll();
    Location getById(Long id);
    void delete(Long id, LocalDateTime dayUpdate);
    void update(Long id, LocalDateTime dayUpdate, Location location);
    boolean exist(Long id);
    boolean exist(String name);
}
