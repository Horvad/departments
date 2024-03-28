package by.itAcademy.core.DTO.location;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.Objects;

public class DTOLocationOut {
    @JsonSetter("id")
    private long id;
    @JsonSetter("name")
    private String name;
    @JsonSetter("dateUpdate")
    private String dateUpdate;

    public DTOLocationOut() {
    }

    public DTOLocationOut(long id, String name, String dateUpdate) {
        this.id = id;
        this.name = name;
        this.dateUpdate = dateUpdate;
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

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
