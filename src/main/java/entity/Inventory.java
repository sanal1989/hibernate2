package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="inventory", schema = "movie")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    //
    @OneToMany
    @JoinColumn(name = "inventory_id")
    List<Rental> rentalList = new ArrayList<>();
//
    public Inventory() {
    }

    public Inventory(Long inventory_id, Film film, Store store, LocalDateTime last_update, List<Rental> rentalList) {
        this.inventoryId = inventory_id;
        this.film = film;
        this.store = store;
        this.lastUpdate = last_update;
        this.rentalList = rentalList;
    }


    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventory_id) {
        this.inventoryId = inventory_id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime last_update) {
        this.lastUpdate = last_update;
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }
}
