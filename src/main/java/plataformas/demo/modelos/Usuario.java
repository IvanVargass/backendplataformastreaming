package plataformas.demo.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUsuario;

    @Column
    private String nombre;

    @Id
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String role;

    public Usuario(String nombre, String email, String password, String role){
        this.nombre = nombre; 
        this.email = email; 
        this.nombre = password; 
        this.nombre = role; 
    }

    @OneToMany(mappedBy = "usuario", orphanRemoval = true,cascade = CascadeType.ALL)
    List<PeliculaAlquilada> peliculasAlquiladas;
}
