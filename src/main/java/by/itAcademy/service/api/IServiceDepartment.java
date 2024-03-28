package by.itAcademy.service.api;

import by.itAcademy.core.DTO.department.DTODepartmentCreate;
import by.itAcademy.core.DTO.department.DepartPut;
import by.itAcademy.core.DTO.department.DepartmentDelete;
import by.itAcademy.dao.entity.Department;
import by.itAcademy.dao.entity.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceDepartment {
    void add(DTODepartmentCreate department);
    List<Department> getAll();
    Department getById(long id);
    void delete(DepartmentDelete departmentDelete);
    void update(DepartPut departmentPut);
    boolean exist(long id);
    boolean exist(String name);
    boolean existSaveLocation(Location location);

    List<Department> getAllChildren(long id);
}
