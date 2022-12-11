package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city", schema = "movie")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column
    private String city;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private List<Address> addressList = new ArrayList<>();

    public City() {
    }

    public City(Country country, String city, LocalDateTime last_update) {
        this.country = country;
        this.city = city;
        this.lastUpdate = last_update;
    }

    public List<Address> getAddressList() {
        return addressList;
    }


    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long city_id) {
        this.cityId = city_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime last_update) {
        this.lastUpdate = last_update;
    }
}
