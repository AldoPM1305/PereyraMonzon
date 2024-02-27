package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Cliente;
import edu.unc.pereyramonzon.domain.Reserva;
import edu.unc.pereyramonzon.repository.ClienteRepository;
import edu.unc.pereyramonzon.repository.ReservaRepository;

@Service
public class ClienteReservaServiceImpl implements ClienteReservaService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> obtenerReservasDeCliente(Long idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cliente.isPresent()) {
            return cliente.get().getReservas();
        }
        return null; // O manejar de acuerdo a tu lógica de negocio
    }
    @Override
    public Reserva crearReservaParaCliente(Long idCliente, Reserva reserva) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            reserva.setCliente(cliente);
            return reservaRepository.save(reserva);
        }
        return null; // O manejar de acuerdo a tu lógica de negocio
    }
    @Override
    public Optional<Reserva> buscarReservaDeCliente(Long idCliente, Long idReserva) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            return cliente.getReservas().stream()
                    .filter(reserva -> reserva.getIdReserva().equals(idReserva))
                    .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public Cliente asignarReserva(Long idCliente, Long idReserva) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);
        if (optionalCliente.isPresent() && optionalReserva.isPresent()) {
            Cliente cliente = optionalCliente.get();
            Reserva reserva = optionalReserva.get();
            cliente.getReservas().add(reserva);
            return clienteRepository.save(cliente);
        }
        return null; // O manejar de acuerdo a tu lógica de negocio
    }

    @Override
    public void eliminarReservaDeCliente(Long idCliente, Long idReserva) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);
        if (optionalCliente.isPresent() && optionalReserva.isPresent()) {
            Cliente cliente = optionalCliente.get();
            Reserva reserva = optionalReserva.get();
            cliente.getReservas().remove(reserva);
            clienteRepository.save(cliente);
        }
    }
}
