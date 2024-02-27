package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Agencia;
import edu.unc.pereyramonzon.repository.AgenciaRepository;

@Service
public class AgenciaServiceImpl implements AgenciaService {
    
    @Autowired
    private AgenciaRepository agenciaRepository;

    @Override
    public List<Agencia> listarAgencias() {
        return (List<Agencia>) agenciaRepository.findAll();
    }

    @Override
    public Optional<Agencia> buscarAgenciaPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    @Override
    public Agencia guardarAgencia(Agencia agencia) {
        return agenciaRepository.save(agencia);
    }

    @Override
    public void eliminarAgencia(Long id) {
        agenciaRepository.deleteById(id);
    }

    @Override
    public Agencia crearAgencia(Agencia agencia) {
        return agenciaRepository.save(agencia);
    }
}
