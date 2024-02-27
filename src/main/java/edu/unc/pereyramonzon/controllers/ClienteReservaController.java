package edu.unc.pereyramonzon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.pereyramonzon.domain.Cliente;
import edu.unc.pereyramonzon.services.ClienteReservaService;

@RestController
@RequestMapping("api/v1/garajes/clires")
public class ClienteReservaController {
    
    @Autowired
    private ClienteReservaService clienteReservaService;
    
    @PutMapping("/{idCliente}/{idReserva}")
    public Cliente asignarReserva(@PathVariable Long idCliente, @PathVariable Long idReserva) {
        Cliente clienteActualizado = clienteReservaService.asignarReserva(idCliente, idReserva);
        return clienteActualizado;
    }
}
