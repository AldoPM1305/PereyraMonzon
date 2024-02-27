package edu.unc.pereyramonzon.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.pereyramonzon.domain.Garaje;
import edu.unc.pereyramonzon.services.GarajeService;

@RestController
@RequestMapping("api/v1/garajes")
public class GarajeController {

    @Autowired
    private GarajeService garajeService;

    @GetMapping
    public List<Garaje> listarGarajes() {
        return garajeService.listarGarajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerGarajePorId(@PathVariable Long id) {
        Optional<Garaje> garajeOptional = garajeService.buscarGarajePorId(id);
        if (garajeOptional.isPresent()) {
            return ResponseEntity.ok(garajeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearGaraje(@RequestBody Garaje garaje) {
        Garaje nuevoGaraje = garajeService.guardarGaraje(garaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGaraje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarGaraje(@PathVariable Long id, @RequestBody Garaje garaje) {
        Optional<Garaje> garajeExistente = garajeService.buscarGarajePorId(id);
        if (garajeExistente.isPresent()) {
            Garaje garajeActualizado = garajeExistente.get();
            // Actualizar los campos del garaje según los datos proporcionados en el cuerpo de la solicitud
            garajeActualizado.setNombre(garaje.getNombre());
            garajeActualizado.setUbicacion(garaje.getUbicacion());

            Garaje garajeGuardado = garajeService.guardarGaraje(garajeActualizado);
            return ResponseEntity.ok(garajeGuardado);
        }
        return ResponseEntity.notFound().build();
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarGaraje(@PathVariable Long id) {
        Optional<Garaje> garajeExistente = garajeService.buscarGarajePorId(id);
        if (garajeExistente.isPresent()) {
            garajeService.eliminarGaraje(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
