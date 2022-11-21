package plataformas.demo.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PeliculaCatalogo {

    @Id
    private long idPelicula;

    @Column
    private String poster_path;

    @Column
    private String title;

    @Column
    private String release_date;

    @Column
    private String original_language;

    @Column
    private String vote_average;

    @Column
    private String overview;

    public PeliculaCatalogo(long idPelicula, String poster_path, String title, String release_date,
            String original_language, String vote_average, String overview) {
        this.idPelicula = idPelicula;
        this.poster_path = poster_path;
        this.title = title;
        this.release_date = release_date;
        this.original_language = original_language;
        this.vote_average = vote_average;
        this.overview = overview;
    }
}
