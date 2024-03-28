package by.itAcademy.core.DTO.department.out;

import com.fasterxml.jackson.annotation.JsonSetter;

public class DTOChildrenParent {
    @JsonSetter("id")
    private long id;
    @JsonSetter("name")
    private String name;

    public DTOChildrenParent() {
    }

    public DTOChildrenParent(long id, String name) {
        this.id = id;
        this.name = name;
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
}
