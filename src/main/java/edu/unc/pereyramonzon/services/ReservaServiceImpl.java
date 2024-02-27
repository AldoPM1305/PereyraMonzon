package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Reserva;
import edu.unc.pereyramonzon.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> listarReservas() {
        return (List<Reserva>) reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
