package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Reserva;

public interface ReservaService {
    List<Reserva> listarReservas();
    Optional<Reserva> buscarReservaPorId(Long id);
    Reserva guardarReserva(Reserva reserva);
    void eliminarReserva(Long id);
}
