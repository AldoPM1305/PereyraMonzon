package edu.unc.pereyramonzon.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.unc.pereyramonzon.domain.Reserva;

import edu.unc.pereyramonzon.services.ReservaAgenciaService;

@RestController
@RequestMapping("/api/reservas/reag")
public class ReservaAgenciaController {
    @Autowired
    private ReservaAgenciaService reservaAgenciaService;
    
    @PostMapping("/{idReserva}/{idAgencia}")
    public ResponseEntity<Reserva> asignarAgenciaAReserva(@PathVariable Long idReserva, @PathVariable Long idAgencia) {
        Reserva reservaActualizada = reservaAgenciaService.asignarReservaAAgencia(idReserva, idAgencia);
        return ResponseEntity.ok(reservaActualizada);
    }
    
    @DeleteMapping("/{idReserva}/agencias/{idAgencia}")
    public ResponseEntity<Reserva> eliminarAgenciaDeReserva(@PathVariable Long idReserva, @PathVariable Long idAgencia) {
        Reserva reservaActualizada = reservaAgenciaService.eliminarAgenciaDeReserva(idReserva, idAgencia);
        return ResponseEntity.ok(reservaActualizada);
    }
}
