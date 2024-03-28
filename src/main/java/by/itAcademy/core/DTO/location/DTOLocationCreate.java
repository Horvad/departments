package by.itAcademy.core.DTO.location;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

public class DTOLocationCreate {
    @JsonSetter("name")
    private String name;

    public DTOLocationCreate() {
    }

    public DTOLocationCreate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTOLocationCreate that = (DTOLocationCreate) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "LocationCreate{" +
                "name='" + name + '\'' +
                '}';
    }
}
