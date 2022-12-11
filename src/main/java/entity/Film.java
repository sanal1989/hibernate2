package entity;

import dateConverter.EnumConverter;
import dateConverter.Feature;
import dateConverter.Rating;
import dateConverter.YearConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Entity
@Table(name = "film", schema = "movie")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "org.hibernate.type.StringType")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    @Convert(converter = YearConverter.class)
    private Year releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguageId;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column
    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;


    @Convert(converter = EnumConverter.class)
    @Column(columnDefinition = "enum ('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name="film_actor",
            joinColumns=  @JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns= @JoinColumn(name="actor_id", referencedColumnName="actor_id") )
    private Set<Actor> actorHashSet = new HashSet<Actor>();

    @ManyToMany
    @JoinTable(name="film_category",
            joinColumns=  @JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns= @JoinColumn(name="category_id", referencedColumnName="category_id") )
    private Set<Category> categoryHashSet = new HashSet<Category>();

    @OneToMany
    @JoinColumn(name = "film_id")
    List<Inventory> inventoryList = new ArrayList<>();

    public Film() {
    }

    public Film(String title, String description, Year releaseYear, Language language, Language originalLanguageId, Byte rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost, Rating rating, String special_features, LocalDateTime last_update, Set<Actor> actorHashSet, Set<Category> categoryHashSet, List<Inventory> inventoryList) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.originalLanguageId = originalLanguageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = special_features;
        this.lastUpdate = last_update;
        this.actorHashSet = actorHashSet;
        this.categoryHashSet = categoryHashSet;
        this.inventoryList = inventoryList;
    }

    public Long getFilm_id() {
        return filmId;
    }

    public void setFilm_id(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year release_year) {
        this.releaseYear = release_year;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Language original_language_id) {
        this.originalLanguageId = original_language_id;
    }

    public Byte getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Byte rental_duration) {
        this.rentalDuration = rental_duration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rental_rate) {
        this.rentalRate = rental_rate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacement_cost) {
        this.replacementCost = replacement_cost;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Feature> getSpecialFeatures() {
        if(isNull(specialFeatures) || specialFeatures.isEmpty()) return null;
        Set<Feature> result = new HashSet<>();
        String[] features = specialFeatures.split(",");
        for (String str: features) {
            result.add(Feature.getFeatureByValue(str));
        }
        result.remove(null);
        return result;
    }

    public void setSpecialFeatures(Set<Feature> special_features) {
        if(isNull(special_features)){
            specialFeatures = null;
        }else {
            specialFeatures = special_features.stream().map(Feature::getValue).collect(Collectors.joining(","));
        }
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime last_update) {
        this.lastUpdate = last_update;
    }

    public Set<Actor> getActorHashSet() {
        return actorHashSet;
    }

    public void setActorHashSet(Set<Actor> actorHashSet) {
        this.actorHashSet = actorHashSet;
    }

    public Set<Category> getCategoryHashSet() {
        return categoryHashSet;
    }

    public void setCategoryHashSet(Set<Category> categoryHashSet) {
        this.categoryHashSet = categoryHashSet;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
