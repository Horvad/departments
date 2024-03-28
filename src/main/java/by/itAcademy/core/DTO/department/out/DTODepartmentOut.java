package by.itAcademy.core.DTO.department.out;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DTODepartmentOut {
    @JsonSetter("id")
    private long id;
    @JsonSetter("name")
    private String name;
    @JsonSetter("phone")
    private String phone;
    @JsonSetter("location")
    private String location;
    @JsonSetter("parent")
    private ChildrenParent parent;
    @JsonSetter("childrens")
    private List<ChildrenParent> dtoChildren;
    @JsonSetter("timeCreate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeCrate;
    @JsonSetter("timeUpdate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeUpdate;

    public DTODepartmentOut() {
    }

    public DTODepartmentOut(long id, String name, String phone, String location, ChildrenParent parent, List<ChildrenParent> dtoChildren, LocalDateTime timeCrate, LocalDateTime timeUpdate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.parent = parent;
        this.dtoChildren = dtoChildren;
        this.timeCrate = timeCrate;
        this.timeUpdate = timeUpdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ChildrenParent getParent() {
        return parent;
    }

    public void setParent(ChildrenParent parent) {
        this.parent = parent;
    }

    public List<ChildrenParent> getDtoChildren() {
        return dtoChildren;
    }

    public void setDtoChildren(List<ChildrenParent> dtoChildren) {
        this.dtoChildren = dtoChildren;
    }

    public LocalDateTime getTimeCrate() {
        return timeCrate;
    }

    public void setTimeCrate(LocalDateTime timeCrate) {
        this.timeCrate = timeCrate;
    }

    public LocalDateTime getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(LocalDateTime timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public void addChildren(ChildrenParent addChildren){
        if(this.dtoChildren==null)
            this.dtoChildren = new ArrayList<>();
        this.dtoChildren.add(addChildren);
    }
}
