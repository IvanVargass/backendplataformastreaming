package plataformas.demo.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class PeliculaAlquilada {
        @Id 
        private long idAlquilada;

        @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private PeliculaCatalogo peliculaCatalogo;

        @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Usuario usuario;   
}