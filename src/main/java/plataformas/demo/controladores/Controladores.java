package plataformas.demo.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import plataformas.demo.modelos.PeliculaAlquilada;
import plataformas.demo.modelos.PeliculaCatalogo;
import plataformas.demo.modelos.Usuario;
import plataformas.demo.repositorios.PeliculaAlquiladaRepo;
import plataformas.demo.repositorios.PeliculaCatalogoRepo;
import plataformas.demo.repositorios.UsuarioRepo;

import org.json.JSONArray;
import org.json.JSONObject;

@RestController
public class Controladores {

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    PeliculaCatalogoRepo peliculaCatalogoRepo;

    @Autowired
    PeliculaAlquiladaRepo peliculaAlquiladaRepo;
    
    //@PostMapping("/login")

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario usuario) throws Exception{

        try {
            Usuario _usuario = usuarioRepo.findByEmail(usuario.getEmail());

            if(_usuario==null){
                usuarioRepo.save(usuario);
                return new ResponseEntity<>(usuario.getEmail(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Usuario duplicado", HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/alquiladas")
    public ResponseEntity<List<PeliculaAlquilada>> obtenerAlquiladas(@RequestParam String[] body) throws Exception{
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @PostMapping("/alquiladas")
    public ResponseEntity<PeliculaAlquilada> guardarAlquilada(@RequestBody String body){
        try {

            JSONObject json = new JSONObject(body);
            Long idPelicula = Long.parseLong((String) json.get("idPelicula"));
            String email = (String) json.get("email");
            
            PeliculaCatalogo peliculaCatalogo = peliculaCatalogoRepo.findByIdPelicula(idPelicula);
            Usuario usuario = usuarioRepo.findByEmail(email);

            PeliculaAlquilada peliculaAlquilada = new PeliculaAlquilada(peliculaCatalogo, usuario);

            peliculaAlquiladaRepo.save(peliculaAlquilada);

            System.out.println(peliculaAlquilada);

            //peliculaAlquiladaRepo.save(peliculaAlquilada);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    @DeleteMapping("/alquiladas")
    public String borrarAlquilada(){
        return "alquiladas";
    }

    @GetMapping("/pelicula")
    public String obtenerPeliculasCatologo(){
        return "Películas catálogo admin";
    }

    @PostMapping("/pelicula")
    public ResponseEntity<PeliculaCatalogo> agregarPeliculaCatalogo(@RequestBody PeliculaCatalogo peliculaCatalogo){
        try {
            peliculaCatalogoRepo.save(peliculaCatalogo);
            return new ResponseEntity<>(peliculaCatalogo, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception 
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pelicula")
    public String borrarPeliculaCatalogo(@RequestParam long id){
        System.out.println(id);
        try {
            
            peliculaCatalogoRepo.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return "borrada";

    }
}
