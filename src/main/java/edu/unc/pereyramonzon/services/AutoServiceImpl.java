package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Auto;
import edu.unc.pereyramonzon.repository.AutoRepository;

@Service
public class AutoServiceImpl implements AutoService {
    
    @Autowired
    private AutoRepository autoRepository;

    @Override
    public List<Auto> listarAutos() {
        return (List<Auto>) autoRepository.findAll();
    }

    @Override
    public Optional<Auto> buscarAutoPorId(Long id) {
        return autoRepository.findById(id);
    }

    @Override
    public Auto guardarAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    @Override
    public void eliminarAuto(Long id) {
        autoRepository.deleteById(id);
    }
}
