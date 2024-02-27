package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Garaje;
import edu.unc.pereyramonzon.repository.GarajeRepository;

@Service
public class GarajeServiceImpl implements GarajeService {
    
    @Autowired
    private GarajeRepository garajeRepository;

    @Override
    public List<Garaje> listarGarajes() {
        return (List<Garaje>) garajeRepository.findAll();
    }

    @Override
    public Optional<Garaje> buscarGarajePorId(Long id) {
        return garajeRepository.findById(id);
    }

    @Override
    public Garaje guardarGaraje(Garaje garaje) {
        return garajeRepository.save(garaje);
    }

    @Override
    public void eliminarGaraje(Long id) {
        garajeRepository.deleteById(id);
    }
}
