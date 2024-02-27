package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Agencia;


public interface AgenciaService {
    List<Agencia> listarAgencias();
    Optional<Agencia> buscarAgenciaPorId(Long id);
    Agencia guardarAgencia(Agencia agencia);
    void eliminarAgencia(Long id);
    Agencia crearAgencia(Agencia agencia);
}
