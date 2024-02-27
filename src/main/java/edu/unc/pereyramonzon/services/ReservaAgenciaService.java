package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Reserva;

public interface ReservaAgenciaService {
    List<Reserva> obtenerReservasDeAgencia(Long idAgencia);
    Optional<Reserva> buscarReservaDeAgencia(Long idAgencia, Long idReserva);
    Reserva asignarReservaAAgencia(Long idAgencia, Long idReserva);
    Reserva eliminarAgenciaDeReserva(Long idReserva, Long idAgencia);
}
