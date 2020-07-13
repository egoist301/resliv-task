package by.resliv.task.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "city_info")
public class CityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "info")
    private String name;
    @Column(name = "create_date", updatable = false)
    private LocalDate createDate;
    @Column(name = "update_date")
    private LocalDate updateDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public CityInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PrePersist
    private void onCreate() {
        createDate = LocalDate.now();
        updateDate = LocalDate.now();
    }

    @PreUpdate
    private void onUpdate() {
        updateDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfo cityInfo = (CityInfo) o;
        return Objects.equals(id, cityInfo.id) &&
                Objects.equals(name, cityInfo.name) &&
                Objects.equals(createDate, cityInfo.createDate) &&
                Objects.equals(updateDate, cityInfo.updateDate) &&
                Objects.equals(city, cityInfo.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createDate, updateDate, city);
    }
}
