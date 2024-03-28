package by.itAcademy.service;

import by.itAcademy.core.DTO.department.DTODepartmentCreate;
import by.itAcademy.core.DTO.department.DepartPut;
import by.itAcademy.core.DTO.department.DepartmentDelete;
import by.itAcademy.dao.ds.api.IDaoDepartment;
import by.itAcademy.dao.entity.Department;
import by.itAcademy.dao.entity.Location;
import by.itAcademy.service.api.IServiceDepartment;
import by.itAcademy.service.api.IServiceLocation;

import java.time.LocalDateTime;
import java.util.List;

public class ServiceDepartment implements IServiceDepartment {
    private final IDaoDepartment daoDepartment;
    private final IServiceLocation serviceLocation;

    public ServiceDepartment(IDaoDepartment daoDepartment, IServiceLocation serviceLocation) {
        this.daoDepartment = daoDepartment;
        this.serviceLocation = serviceLocation;
    }

    @Override
    public void add(DTODepartmentCreate department) {
        Location location = null;
        if(department.getLocation_id()!=null) {
            location = serviceLocation.getById(department.getLocation_id());
            if (department.getLocation_id() != null) {
                if (daoDepartment.exist(department.getLocation_id())) {
                    throw new IllegalArgumentException("Не верно введена локация");
                }
            }
        }
        Department parent = null;
        if(department.getParent_id()!=null){
             parent = daoDepartment.getById(department.getParent_id());
            if(parent==null||parent.getId()==null){
                throw new IllegalArgumentException("Не верно указан родительский департамент");
            }
        }
        Department departmentEntity = new Department();
        departmentEntity.setName(department.getName());
        departmentEntity.setPhone(department.getPhone());
        departmentEntity.setLocation(location);
        departmentEntity.setParent(parent);
        daoDepartment.add(departmentEntity);
        if(department.getChildren_id()!=null){
            for(long id : department.getChildren_id()){
                Department departmentChild = daoDepartment.getById(id);
                departmentChild.setParent(departmentEntity);
                daoDepartment.update(departmentChild.getId(), departmentChild.getDateUpdate(),departmentChild);
            }
        }
    }

    @Override
    public List<Department> getAll() {
        return daoDepartment.getAll();
    }

    @Override
    public Department getById(long id) {
        Department department = daoDepartment.getById(id);
        if(department==null){
            throw new IllegalArgumentException("Данного id не существует");
        }
        return department;
    }

    @Override
    public void delete(DepartmentDelete departmentDelete) {
        Department department = daoDepartment.getById(departmentDelete.getId());
        if(department==null){
            throw new IllegalArgumentException("Данного id не существует");
        }
        if(department.getLocation()!=null){
            throw new IllegalArgumentException("Удалите локацию");
        }
        if(!department.getDateUpdate().isEqual(departmentDelete.getDateUpdate())){
            throw new IllegalArgumentException("Не совпадает версия пожалуйста обновите");
        }
        List<Department> child = daoDepartment.getChild(department);
        if(child!=null&&child.size()!=0) {
            throw new IllegalArgumentException("Удалите подчиненных");
        }
        daoDepartment.delete(departmentDelete.getId(), departmentDelete.getDateUpdate());
    }

    @Override
    public void update(DepartPut departmentPut) {
        Department departmentEntity = daoDepartment.getById(departmentPut.getId());
        if(departmentEntity==null){
            throw new IllegalArgumentException("Данного id не существует");
        }
        if(!departmentEntity.getDateUpdate().isEqual(departmentPut.getTimeUpdate())){
            throw new IllegalArgumentException("Не верно введана версия, пожалейста обновите");
        }
        if(departmentPut.getLocation_id()!=null){
            if(!daoDepartment.exist(departmentPut.getLocation_id())){
                throw new IllegalArgumentException("Не верно введена локация");
            }
        }
        if(departmentPut.getParent_id()!=null){
            if(!daoDepartment.exist(departmentPut.getParent_id())){
                throw new IllegalArgumentException("Данного родителя не существует");
            }
            departmentEntity.setParent(daoDepartment.getById(departmentPut.getParent_id()));
        } else {
            departmentEntity.setParent(null);
        }
        departmentEntity.setPhone(departmentPut.getPhone());
        departmentEntity.setName(departmentPut.getName());
        departmentEntity.setLocation(serviceLocation.getById(departmentPut.getLocation_id()));
        daoDepartment.update(departmentPut.getId(),departmentPut.getTimeUpdate(), departmentEntity);
        Department department = daoDepartment.getById(departmentEntity.getId());
        if(department.getDateUpdate().isEqual(departmentPut.getTimeUpdate())){
            throw new IllegalArgumentException("Обновление не прошло");
        }
        if(departmentPut.getChildren_id()!=null){
            for(Long idChild : departmentPut.getChildren_id()){
                if(idChild!=null){
                    Department departmentChild = daoDepartment.getById(idChild);
                    departmentChild.setParent(departmentEntity);
                    daoDepartment.update(idChild,departmentChild.getDateUpdate(),departmentChild);
                }
            }
        }


    }

    @Override
    public boolean exist(long id) {
        return daoDepartment.exist(id);
    }

    @Override
    public boolean exist(String name) {
        return daoDepartment.exist(name);
    }

    @Override
    public boolean existSaveLocation(Location location) {
        return daoDepartment.exitSaveLocation(location);
    }

    @Override
    public List<Department> getAllChildren(long id) {
        Department department = daoDepartment.getById(id);
        return daoDepartment.getChild(department);
    }
}
