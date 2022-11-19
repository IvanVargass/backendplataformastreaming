package plataformas.demo.controladores;

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

import plataformas.demo.modelos.Usuario;
import plataformas.demo.repositorios.UsuarioRepo;

@RestController
public class Controladores {

    @Autowired
    UsuarioRepo usuarioRepo;
    
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
    public String obtenerAlquiladas(){
        return "alquiladas";
    }

    @PostMapping("/alquiladas")
    public String guardarAlquilada(){
        return "alquiladas";
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
    public String agregarPeliculasCatologo(){
        return "Agregar pelis al catálogo";
    }


}
