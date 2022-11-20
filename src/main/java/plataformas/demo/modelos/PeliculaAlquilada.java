package plataformas.demo.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ch.qos.logback.core.subst.Token.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"peliculaCatalogo"})
@NoArgsConstructor
public class PeliculaAlquilada {

        @Id 
        @GeneratedValue(strategy=GenerationType.AUTO)
        private long idAlquilada;

        @ManyToOne
        private PeliculaCatalogo peliculaCatalogo;

        @ManyToOne
        private Usuario usuario;  
        
        public PeliculaAlquilada(PeliculaCatalogo peliculaCatalogo, Usuario usuario){
                this.peliculaCatalogo = peliculaCatalogo;
                this.usuario = usuario;
        }

}