package by.itAcademy.core.DTO.location;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public class DTOLocationDelete {
    @JsonSetter("id")
    private long id;
    @JsonSetter("dateUpdate")
    private String dateUpdate;

    public DTOLocationDelete(long id, String dateUpdate) {
        this.id = id;
        this.dateUpdate = dateUpdate;
    }

    public DTOLocationDelete() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalDateTime() {
        return dateUpdate;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.dateUpdate = dateUpdate;
    }
}
