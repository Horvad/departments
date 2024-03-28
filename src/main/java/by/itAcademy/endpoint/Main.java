package by.itAcademy.endpoint;

import by.itAcademy.dao.ds.api.IDaoLocation;
import by.itAcademy.dao.ds.fabric.DaoLocationSingleton;
import by.itAcademy.dao.entity.Location;
import by.itAcademy.service.api.IServiceDepartment;
import by.itAcademy.service.api.IServiceLocation;
import by.itAcademy.service.fabric.ServiceDepartmentSingleton;
import by.itAcademy.service.fabric.ServiceLocationSingleton;



public class Main {
    private static IServiceLocation serviceLocation = ServiceLocationSingleton.getInstance();
    private static IServiceDepartment serviceDepartment = ServiceDepartmentSingleton.getInstance();
    public static void main(String[] args){
        IDaoLocation daoLocation = DaoLocationSingleton.getInstance();
        Location location = daoLocation.getById(2L);
        daoLocation.delete(location.getId(),location.getDateUpdate());
    }
}