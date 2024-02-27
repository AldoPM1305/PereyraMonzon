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

import edu.unc.pereyramonzon.domain.Reserva;
import edu.unc.pereyramonzon.services.ReservaService;

@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReservaPorId(@PathVariable Long id) {
        Optional<Reserva> reservaOptional = reservaService.buscarReservaPorId(id);
        if (reservaOptional.isPresent()) {
            return ResponseEntity.ok(reservaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.guardarReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarReserva(@PathVariable Long id, @RequestBody Reserva reservaActualizada) {
        Optional<Reserva> reservaExistente = reservaService.buscarReservaPorId(id);
        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();
            // Actualizar los campos de la reserva con los valores del objeto actualizado
            reserva.setFechaInicio(reservaActualizada.getFechaInicio());
            reserva.setFechaFinal(reservaActualizada.getFechaFinal());
            reserva.setPrecioTotal(reservaActualizada.getPrecioTotal());
            reserva.setEntregado(reservaActualizada.getEntregado());
            // También podrías necesitar actualizar otros campos según tus necesidades
            
            // Guardar la reserva actualizada
            Reserva reservaGuardada = reservaService.guardarReserva(reserva);
            return ResponseEntity.ok(reservaGuardada);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) {
        Optional<Reserva> reservaExistente = reservaService.buscarReservaPorId(id);
        if (reservaExistente.isPresent()) {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
