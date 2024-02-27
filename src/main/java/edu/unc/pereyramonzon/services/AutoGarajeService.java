package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Auto;


public interface AutoGarajeService {

    Optional<Auto> buscarAutoDeGaraje(Long idGaraje, Long idAuto);

	Auto asignarAutoAGaraje(Long idGaraje, Long idAuto);
	void eliminarAutoDeGaraje(Long idGaraje, Long idAuto);
	List<Auto> obtenerAutosDeGaraje(Long idGaraje);

}
