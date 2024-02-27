package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Cliente;
import edu.unc.pereyramonzon.domain.Reserva;

public interface ClienteReservaService {
    List<Reserva> obtenerReservasDeCliente(Long idCliente);
    Optional<Reserva> buscarReservaDeCliente(Long idCliente, Long idReserva);
    Reserva crearReservaParaCliente(Long idCliente, Reserva reserva);
    void eliminarReservaDeCliente(Long idCliente, Long idReserva);
    Cliente asignarReserva(Long idCliente, Long idReserva);
}
