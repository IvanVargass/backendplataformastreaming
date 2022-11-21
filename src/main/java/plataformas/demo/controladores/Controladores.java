package plataformas.demo.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("http://localhost:3000")
public class Controladores {

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    PeliculaCatalogoRepo peliculaCatalogoRepo;

    @Autowired
    PeliculaAlquiladaRepo peliculaAlquiladaRepo;


    @GetMapping("/")
    public String homePruebas(){
        return "Hola mundo";
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario usuario) throws Exception{
        try {
            Usuario _usuario = usuarioRepo.findByEmail(usuario.getEmail());

            if(_usuario==null){
                usuarioRepo.save(usuario);
                return new ResponseEntity<>(usuario.getEmail(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("usuario duplicado", HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public String holaLogin(){
        return "Hola Login";
    }

    /* @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String body) throws Exception{
        try {
            Usuario _usuario = usuarioRepo.findByEmail(usuario.getEmail());

            if(_usuario==null){
                usuarioRepo.save(usuario);
                return new ResponseEntity<>(usuario.getEmail(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("usuario duplicado", HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */

    @GetMapping("/alquilada")
    public ResponseEntity<List<PeliculaAlquilada>> obtenerAlquiladas(@RequestParam String email) throws Exception{
        try {

            List<PeliculaAlquilada> peliculasAlquiladas = new ArrayList<PeliculaAlquilada>();
            peliculasAlquiladas = peliculaAlquiladaRepo.findByUsuarioEmail(email);

            if(peliculasAlquiladas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(peliculasAlquiladas, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/alquilada")
    public ResponseEntity<?> guardarAlquilada(@RequestBody String body){
        try {
            JSONObject json = new JSONObject(body);
            Long idPelicula = Long.parseLong((String) json.get("idPelicula"));
            String email = (String) json.get("email");
            
            PeliculaCatalogo peliculaCatalogo = peliculaCatalogoRepo.findByIdPelicula(idPelicula);
            Usuario usuario = usuarioRepo.findByEmail(email);

            PeliculaAlquilada peliculaAlquilada = new PeliculaAlquilada(peliculaCatalogo, usuario);
            
            if(peliculaAlquiladaRepo.findByPeliculaCatalogoAndUsuario(peliculaCatalogo, usuario)==null){
                peliculaAlquiladaRepo.save(peliculaAlquilada);
                return new ResponseEntity<>(peliculaAlquilada.getPeliculaCatalogo().getIdPelicula(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("duplicada",HttpStatus.OK);
            }            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/alquilada")
    public ResponseEntity<HttpStatus> borrarAlquilada(@RequestParam long id ){
        try {
            peliculaAlquiladaRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pelicula")
    public ResponseEntity<List<PeliculaCatalogo>> obtenerPeliculasCatalogo(){
        try {
            List<PeliculaCatalogo> peliculas = peliculaCatalogoRepo.findAll();

            if(peliculas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(peliculas, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pelicula")
    public ResponseEntity<PeliculaCatalogo> agregarPeliculaCatalogo(@RequestBody PeliculaCatalogo peliculaCatalogo){
        try {
            peliculaCatalogoRepo.save(peliculaCatalogo);
            return new ResponseEntity<>(peliculaCatalogo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pelicula")
    public ResponseEntity<HttpStatus> borrarPeliculaCatalogo(@RequestParam long id){
        try {
            peliculaCatalogoRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
