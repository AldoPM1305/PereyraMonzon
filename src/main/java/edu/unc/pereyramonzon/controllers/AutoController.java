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

import edu.unc.pereyramonzon.domain.Auto;
import edu.unc.pereyramonzon.services.AutoService;

@RestController
@RequestMapping("api/v1/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public List<Auto> listarAutos() {
        return autoService.listarAutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAutoPorId(@PathVariable Long id) {
        Optional<Auto> autoOptional = autoService.buscarAutoPorId(id);
        if (autoOptional.isPresent()) {
            return ResponseEntity.ok(autoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearAuto(@RequestBody Auto auto) {
        Auto nuevoAuto = autoService.guardarAuto(auto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAuto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAuto(@PathVariable Long id, @RequestBody Auto auto) {
        Optional<Auto> autoExistente = autoService.buscarAutoPorId(id);
        if (autoExistente.isPresent()) {
            Auto autoActualizado = autoExistente.get();
            // Actualizar los campos del auto según los datos proporcionados en el cuerpo de la solicitud
            autoActualizado.setMatricula(auto.getMatricula());
            autoActualizado.setModelo(auto.getModelo());
            autoActualizado.setColor(auto.getColor());
            autoActualizado.setMarca(auto.getMarca());
            autoActualizado.setPrecioReserva(auto.getPrecioReserva());
            autoActualizado.setPrecioLtGasolina(auto.getPrecioLtGasolina());

            Auto autoGuardado = autoService.guardarAuto(autoActualizado);
            return ResponseEntity.ok(autoGuardado);
        }
        return ResponseEntity.notFound().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAuto(@PathVariable Long id) {
        Optional<Auto> autoExistente = autoService.buscarAutoPorId(id);
        if (autoExistente.isPresent()) {
            autoService.eliminarAuto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
