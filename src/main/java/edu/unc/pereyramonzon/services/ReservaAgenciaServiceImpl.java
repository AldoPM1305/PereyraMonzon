package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Agencia;
import edu.unc.pereyramonzon.domain.Reserva;
import edu.unc.pereyramonzon.repository.AgenciaRepository;
import edu.unc.pereyramonzon.repository.ReservaRepository;

@Service
public class ReservaAgenciaServiceImpl implements ReservaAgenciaService {
    
    @Autowired
    private AgenciaRepository agenciaRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> obtenerReservasDeAgencia(Long idAgencia) {
        Optional<Agencia> optionalAgencia = agenciaRepository.findById(idAgencia);
        return optionalAgencia.map(Agencia::getReservas).orElse(null);
    }

    @Override
    public Optional<Reserva> buscarReservaDeAgencia(Long idAgencia, Long idReserva) {
        Optional<Agencia> optionalAgencia = agenciaRepository.findById(idAgencia);
        if (optionalAgencia.isPresent()) {
            return optionalAgencia.get().getReservas().stream()
                    .filter(reserva -> reserva.getIdReserva().equals(idReserva))
                    .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public Reserva asignarReservaAAgencia(Long idAgencia, Long idReserva) {
        Optional<Agencia> optionalAgencia = agenciaRepository.findById(idAgencia);
        Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);
        
        if (optionalAgencia.isPresent() && optionalReserva.isPresent()) {
            Agencia agencia = optionalAgencia.get();
            Reserva reserva = optionalReserva.get();
            reserva.setAgencia(agencia);
            return reservaRepository.save(reserva);
        }
        return null;
    }

    @Override
    public Reserva eliminarAgenciaDeReserva(Long idReserva, Long idAgencia) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            reserva.setAgencia(null);
            return reservaRepository.save(reserva);
        }
        return null;
    }
}
