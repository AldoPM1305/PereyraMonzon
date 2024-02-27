package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Garaje;

public interface GarajeService {
    List<Garaje> listarGarajes();
    Optional<Garaje> buscarGarajePorId(Long id);
    Garaje guardarGaraje(Garaje garaje);
    void eliminarGaraje(Long id);
}
