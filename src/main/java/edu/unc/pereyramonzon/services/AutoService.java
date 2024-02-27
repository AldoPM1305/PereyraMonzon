package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Auto;

public interface AutoService {
    List<Auto> listarAutos();
    Optional<Auto> buscarAutoPorId(Long id);
    Auto guardarAuto(Auto auto);
    void eliminarAuto(Long id);
}
