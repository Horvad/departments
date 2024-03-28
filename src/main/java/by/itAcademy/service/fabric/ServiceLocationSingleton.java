package by.itAcademy.service.fabric;

import by.itAcademy.dao.ds.fabric.DaoLocationSingleton;
import by.itAcademy.service.ServiceLocation;
import by.itAcademy.service.api.IServiceLocation;

public class ServiceLocationSingleton {
    private volatile static IServiceLocation instance;

    private ServiceLocationSingleton() {
    }

    public static IServiceLocation getInstance() {
        if(instance==null){
            synchronized (ServiceLocationSingleton.class){
                if(instance==null){
                    instance = new ServiceLocation(
                            DaoLocationSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
