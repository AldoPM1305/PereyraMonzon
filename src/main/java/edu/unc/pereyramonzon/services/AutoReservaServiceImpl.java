package edu.unc.pereyramonzon.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Auto;
import edu.unc.pereyramonzon.domain.Reserva;
import edu.unc.pereyramonzon.repository.AutoRepository;
import edu.unc.pereyramonzon.repository.ReservaRepository;

@Service
public class AutoReservaServiceImpl implements AutoReservaService {

    @Autowired
    private AutoRepository autoRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Auto asignarReserva(Long idAuto, Long idReserva) {
        Optional<Auto> optionalAuto = autoRepository.findById(idAuto);
        Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);
        
        if (optionalAuto.isPresent() && optionalReserva.isPresent()) {
            Auto auto = optionalAuto.get();
            Reserva reserva = optionalReserva.get();
            auto.getReservas().add(reserva);
            return autoRepository.save(auto);
        } else {
            // Manejar la situación donde el auto o la reserva no existen
            return null;
        }
    }

    @Override
    public Auto eliminarReserva(Long idAuto, Long idReserva) {
        Optional<Auto> optionalAuto = autoRepository.findById(idAuto);
        Optional<Reserva> optionalReserva = reservaRepository.findById(idReserva);
        
        if (optionalAuto.isPresent() && optionalReserva.isPresent()) {
            Auto auto = optionalAuto.get();
            Reserva reserva = optionalReserva.get();
            auto.getReservas().remove(reserva);
            return autoRepository.save(auto);
        } else {
            // Manejar la situación donde el auto o la reserva no existen
            return null;
        }
    }
}
