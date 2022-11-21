package plataformas.demo.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

    

    
}
