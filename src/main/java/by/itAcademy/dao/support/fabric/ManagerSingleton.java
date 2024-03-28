package by.itAcademy.dao.support.fabric;

import by.itAcademy.dao.support.Manager;
import by.itAcademy.dao.support.api.IManger;

public class ManagerSingleton {
    private static volatile IManger instance;

    private ManagerSingleton() {
    }

    public static IManger getInstance() {
        if(instance==null){
            synchronized (ManagerSingleton.class){
                if(instance==null){
                    instance = new Manager();
                }
            }
        }
        return instance;
    }
}
