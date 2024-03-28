package by.itAcademy.dao.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "id_location")
    private Location location;
    @JoinTable(
            name = "dep_cross",
            joinColumns =
            @JoinColumn(name = "id_parent", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "id_children", referencedColumnName = "ID")
    )
    @ManyToOne
    private Department parent;
    @Column(name = "date_create")
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    public Department() {
    }

    public Department(Long id, String name, String phone, Location location, Department parent, LocalDateTime dateCreate, LocalDateTime dateUpdate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.parent = parent;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(location, that.location) && Objects.equals(parent, that.parent) && Objects.equals(dateCreate, that.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, location, parent, dateCreate);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", location=" + location +
                ", parent=" + parent +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                '}';
    }
}
