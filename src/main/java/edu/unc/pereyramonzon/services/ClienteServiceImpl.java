package edu.unc.pereyramonzon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unc.pereyramonzon.domain.Cliente;
import edu.unc.pereyramonzon.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository; // Asumiendo que tienes un ClienteRepository

    @Override
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteRepository.findAll(); // Método para listar todos los clientes
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id); // Método para buscar un cliente por su ID
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente); // Método para guardar un cliente
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id); // Método para eliminar un cliente por su ID
    }
}
