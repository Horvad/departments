package by.itAcademy.dao.ds.fabric;

import by.itAcademy.dao.ds.DaoDepartment;
import by.itAcademy.dao.ds.api.IDaoDepartment;
import by.itAcademy.dao.support.fabric.ManagerSingleton;

public class DaoDepartmentSingleton {
    private static volatile IDaoDepartment instance;

    private DaoDepartmentSingleton() {
    }

    public static IDaoDepartment getInstance() {
        if(instance==null){
            synchronized (DaoDepartmentSingleton.class){
                if(instance==null){
                    instance = new DaoDepartment(
                            ManagerSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
