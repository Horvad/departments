package by.itAcademy.service.fabric;

import by.itAcademy.dao.ds.fabric.DaoDepartmentSingleton;
import by.itAcademy.dao.ds.fabric.DaoLocationSingleton;
import by.itAcademy.service.ServiceDepartment;
import by.itAcademy.service.api.IServiceDepartment;

public class ServiceDepartmentSingleton {
    private volatile static IServiceDepartment instance;

    private ServiceDepartmentSingleton() {
    }

    public static IServiceDepartment getInstance() {
        if(instance==null){
            synchronized (ServiceDepartmentSingleton.class){
                if(instance==null){
                    instance = new ServiceDepartment(
                            DaoDepartmentSingleton.getInstance(),
                            ServiceLocationSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
