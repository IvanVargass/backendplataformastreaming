package plataformas.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import plataformas.demo.modelos.PeliculaAlquilada;
import plataformas.demo.modelos.PeliculaCatalogo;

public interface PeliculaAlquiladaRepo extends JpaRepository<PeliculaAlquilada, Long> {

    public PeliculaAlquilada findByPeliculaCatalogo(PeliculaCatalogo peliculaCatalogo);
    
}
