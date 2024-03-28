package by.itAcademy.dao.ds.api;

import by.itAcademy.dao.entity.Department;
import by.itAcademy.dao.entity.Location;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface IDaoDepartment {
    void add(Department department);
    List<Department> getAll();
    Department getById(Long id);
    void delete(Long id, LocalDateTime dateUpdate);
    void update(Long id, LocalDateTime dateUpdate, Department department);
    boolean exist(Long id);
    boolean exist(String name);
    List<Department> getChild(Department department);
    boolean exitSaveLocation(Location location);
    public List<Location> getSaveLocation();
}
