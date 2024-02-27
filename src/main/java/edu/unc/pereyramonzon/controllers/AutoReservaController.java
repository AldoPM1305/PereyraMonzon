package edu.unc.pereyramonzon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.pereyramonzon.domain.Auto;
import edu.unc.pereyramonzon.services.AutoReservaService;

@RestController
@RequestMapping("/api/v1/auto-reservas")
public class AutoReservaController {
    
    @Autowired
    private AutoReservaService autoReservaService;
    
    @PutMapping("/{idAuto}/{idReserva}")
    public Auto asignarReserva(@PathVariable Long idAuto, @PathVariable Long idReserva) {
        Auto autoReservaActualizada = autoReservaService.asignarReserva(idAuto, idReserva);
        return autoReservaActualizada;
    }
}
