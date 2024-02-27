package edu.unc.pereyramonzon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.pereyramonzon.domain.Auto;
import edu.unc.pereyramonzon.services.AutoGarajeService;

@RestController
@RequestMapping("api/v1/autos/auga")
public class AutoGarajeController {

    @Autowired
    private AutoGarajeService autoGarajeService;

    @PutMapping("/{idAuto}/{idGaraje}")
    public ResponseEntity<Auto> asignarGaraje(@PathVariable Long idAuto, @PathVariable Long idGaraje) {
        Auto auto = autoGarajeService.asignarAutoAGaraje(idGaraje, idAuto);
        if (auto != null) {
            return ResponseEntity.ok(auto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAuto}/{idGaraje}")
    public ResponseEntity<Void> eliminarGaraje(@PathVariable Long idAuto, @PathVariable Long idGaraje) {
        autoGarajeService.eliminarAutoDeGaraje(idGaraje, idAuto);
        return ResponseEntity.noContent().build();
    }
}
