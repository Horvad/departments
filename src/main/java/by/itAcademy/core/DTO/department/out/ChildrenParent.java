package by.itAcademy.core.DTO.department.out;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ChildrenParent {
    @JsonSetter("id")
    private Long id;
    @JsonSetter("name")
    private String name;

    public ChildrenParent(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ChildrenParent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
