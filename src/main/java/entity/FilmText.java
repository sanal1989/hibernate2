package entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "film_text", schema = "movie")
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Long filmId;

    @Column
    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public FilmText() {
    }

    public FilmText(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long film_id) {
        this.filmId = film_id;
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
}
