package apidiasfestivos.apidiasfestivos.presentacion;

import org.springframework.web.bind.annotation.RestController;

import apidiasfestivos.apidiasfestivos.core.interfaces.servicio.IFestivoServicio;
import apidiasfestivos.apidiasfestivos.dominio.entidades.Festivo;

import java.time.DateTimeException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/diasFestivos")
@CrossOrigin(origins = "http://localhost:4200/")
public class FestivosControlador {
    //inyeccion//
    private IFestivoServicio servicio;
    public FestivosControlador(IFestivoServicio servicio) {
        this.servicio = servicio;
    }
    //fin de inyeccion//
        @GetMapping("/listar")    
        public List<Festivo> listar() {
        return servicio.listar();
    }
    @RequestMapping(value = "/EsFestivo/{year}/{month}/{day}", method = RequestMethod.GET)
    public ResponseEntity<String> EsFestivo(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        try {
            String resultado = servicio.EsFestivo(year, month, day);
            return ResponseEntity.ok(resultado); 
        } catch (DateTimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("La fecha ingresada no es válida. Por favor, verifica los valores proporcionados.");
        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado. Por favor intenta nuevamente.");
        }
    }
    @GetMapping("/test-conexion")
    public ResponseEntity<String> pruebaConexion() {
        try {
            // Intentar acceder a la base de datos para verificar la conexión
            List<Festivo> festivos = servicio.listar(); // Esto forzará una consulta a la base de datos
            return ResponseEntity.ok("Conexión exitosa, se han encontrado " + festivos.size() + " festividades.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error de conexión a la base de datos: " + e.getMessage());
        }
    }
    
}

    

