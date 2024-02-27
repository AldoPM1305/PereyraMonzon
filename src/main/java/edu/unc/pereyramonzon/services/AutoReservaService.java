package edu.unc.pereyramonzon.services;

import edu.unc.pereyramonzon.domain.Auto;

public interface AutoReservaService {
    Auto asignarReserva(Long idAuto, Long idReserva);

	Auto eliminarReserva(Long idAuto, Long idReserva);
}
