package edu.unc.pereyramonzon.services;

import java.util.List;

import java.util.Optional;



import edu.unc.pereyramonzon.domain.Cliente;


public interface ClienteService {
    List<Cliente> listarClientes();
    Optional<Cliente> buscarClientePorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    void eliminarCliente(Long id);
}
