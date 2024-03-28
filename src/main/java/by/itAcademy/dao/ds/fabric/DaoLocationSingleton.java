package by.itAcademy.dao.ds.fabric;

import by.itAcademy.dao.ds.DaoLocation;
import by.itAcademy.dao.ds.api.IDaoLocation;
import by.itAcademy.dao.support.fabric.ManagerSingleton;

public class DaoLocationSingleton {
    private static volatile IDaoLocation instance;

    private DaoLocationSingleton() {
    }

    public static IDaoLocation getInstance() {
        if(instance==null){
            synchronized (DaoLocationSingleton.class){
                if(instance==null){
                    instance = new DaoLocation(
                            ManagerSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
