package by.itAcademy.core.DTO.department.out;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

public class DTODepartmentOutAll {
    @JsonSetter("id")
    private long id;
    @JsonSetter("name")
    private String name;
    @JsonSetter("phone")
    private String phone;
    @JsonSetter("location")
    private String location;


    public DTODepartmentOutAll() {
    }

    public DTODepartmentOutAll(long id, String name, String phone, String location) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "DTODepartmentOut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTODepartmentOutAll that = (DTODepartmentOutAll) o;
        return Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, location);
    }
}
