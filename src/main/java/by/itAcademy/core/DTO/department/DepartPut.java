package by.itAcademy.core.DTO.department;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.List;

public class DepartPut {
    @JsonSetter("id")
    private long id;
    @JsonSetter("name")
    private String name;
    @JsonSetter("phone")
    private String phone;
    @JsonSetter("location_id")
    private Long location_id;
    @JsonSetter("parent_id")
    private Long parent_id;
    @JsonSetter("children_id")
    private List<Long> children_id;
    @JsonSetter("timeUpdate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeUpdate;

    public DepartPut() {
    }

    public DepartPut(long id, String name, String phone, Long location_id, Long parent_id, List<Long> children_id, LocalDateTime timeUpdate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location_id = location_id;
        this.parent_id = parent_id;
        this.children_id = children_id;
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

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public List<Long> getChildren_id() {
        return children_id;
    }

    public void setChildren_id(List<Long> children_id) {
        this.children_id = children_id;
    }

    public LocalDateTime getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(LocalDateTime timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
