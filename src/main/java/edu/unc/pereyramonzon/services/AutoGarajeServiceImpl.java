package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Auto;
import edu.unc.pereyramonzon.domain.Garaje;
import edu.unc.pereyramonzon.repository.AutoRepository;
import edu.unc.pereyramonzon.repository.GarajeRepository;

@Service
public class AutoGarajeServiceImpl implements AutoGarajeService {
    
    @Autowired
    private AutoRepository autoRepository;
    
    @Autowired
    private GarajeRepository garajeRepository;

    @Override
    public Optional<Auto> buscarAutoDeGaraje(Long idGaraje, Long idAuto) {
        Optional<Garaje> optionalGaraje = garajeRepository.findById(idGaraje);
        if (optionalGaraje.isPresent()) {
            return optionalGaraje.get().getAutos().stream()
                    .filter(auto -> auto.getIdAuto().equals(idAuto))
                    .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public Auto asignarAutoAGaraje(Long idGaraje, Long idAuto) {
        Optional<Garaje> optionalGaraje = garajeRepository.findById(idGaraje);
        Optional<Auto> optionalAuto = autoRepository.findById(idAuto);
        
        if (optionalGaraje.isPresent() && optionalAuto.isPresent()) {
            Garaje garaje = optionalGaraje.get();
            Auto auto = optionalAuto.get();
            
            auto.setGaraje(garaje);
            return autoRepository.save(auto);
        } else {
            // Manejar caso en que el garaje o el auto no existan
            return null;
        }
    }

    @Override
    public void eliminarAutoDeGaraje(Long idGaraje, Long idAuto) {
        Optional<Garaje> optionalGaraje = garajeRepository.findById(idGaraje);
        Optional<Auto> optionalAuto = autoRepository.findById(idAuto);
        
        if (optionalGaraje.isPresent() && optionalAuto.isPresent()) {
            Garaje garaje = optionalGaraje.get();
            Auto auto = optionalAuto.get();
            
            auto.setGaraje(null);
            autoRepository.save(auto);
        }
    }

    @Override
    public List<Auto> obtenerAutosDeGaraje(Long idGaraje) {
        Optional<Garaje> optionalGaraje = garajeRepository.findById(idGaraje);
        if (optionalGaraje.isPresent()) {
            return optionalGaraje.get().getAutos();
        }
        return null; // O manejar de acuerdo a tu lógica de negocio
    }
}
