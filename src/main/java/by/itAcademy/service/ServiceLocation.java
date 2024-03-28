package by.itAcademy.service;

import by.itAcademy.core.DTO.location.DTOLocationCreate;
import by.itAcademy.core.DTO.location.DTOLocationDelete;
import by.itAcademy.core.DTO.location.DTOLocationOut;
import by.itAcademy.dao.ds.api.IDaoLocation;
import by.itAcademy.dao.entity.Location;
import by.itAcademy.service.api.IServiceLocation;

import java.time.LocalDateTime;
import java.util.List;

public class ServiceLocation implements IServiceLocation {
    private final IDaoLocation daoLocation;

    public ServiceLocation(IDaoLocation daoLocation) {
        this.daoLocation = daoLocation;
    }

    @Override
    public void add(DTOLocationCreate location) {
        if(location==null||location.getName()==null||location.getName().isBlank()){
            throw new IllegalArgumentException("Не корректно введена локация");
        }
        if(daoLocation.exist(location.getName())){
            throw new IllegalArgumentException("Локация с данным именем существует");
        }
        Location locationEntity = new Location();
        locationEntity.setName(location.getName());
        daoLocation.add(locationEntity);
    }

    @Override
    public List<Location> getAll() {
        return daoLocation.getAll();
    }

    @Override
    public Location getById(long id) {
        return daoLocation.getById(id);
    }

    @Override
    public void delete(DTOLocationDelete locationDelete) {
        Location location = daoLocation.getById(locationDelete.getId());
        if(location==null){
            throw new IllegalArgumentException("Локация с данным id не существует");
        }
        LocalDateTime dateUpdate = LocalDateTime.parse(locationDelete.getLocalDateTime());
        if(!location.getDateUpdate().isEqual(dateUpdate)){
            throw new IllegalArgumentException("Не обновлена локация");
        }
        daoLocation.delete(locationDelete.getId(),dateUpdate);
    }

    @Override
    public void update(DTOLocationOut location) {
        Location locationEntity = daoLocation.getById(location.getId());
        if(locationEntity==null){
            throw new IllegalArgumentException("Локации с данным id не существует");
        }
        LocalDateTime dateUpdate = LocalDateTime.parse(location.getDateUpdate());
        if(!locationEntity.getDateUpdate().isEqual(dateUpdate)){
            throw new IllegalArgumentException("Локацию необходимо обновить");
        }
        locationEntity.setName(location.getName());
        daoLocation.update(location.getId(), dateUpdate, locationEntity);
    }

    @Override
    public boolean exist(long id) {
        return daoLocation.exist(id);
    }

    @Override
    public boolean exist(String name) {
        return daoLocation.exist(name);
    }
}
