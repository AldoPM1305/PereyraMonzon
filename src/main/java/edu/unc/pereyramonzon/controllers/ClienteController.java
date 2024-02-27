package edu.unc.pereyramonzon.controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.pereyramonzon.domain.Cliente;

import edu.unc.pereyramonzon.services.ClienteService;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteService.buscarClientePorId(id);
        if (clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistenteOptional = clienteService.buscarClientePorId(id);
        if (clienteExistenteOptional.isPresent()) {
            Cliente clienteExistente = clienteExistenteOptional.get();
            clienteExistente.setDni(cliente.getDni());
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteExistente.setTelefono(cliente.getTelefono());
            
            // Verificar si se proporciona un cliente avalador en la solicitud
            if (cliente.getClienteAvalador() != null) {
                Optional<Cliente> clienteAvaladorExistenteOptional = clienteService.buscarClientePorId(cliente.getClienteAvalador().getIdCli());
                if (clienteAvaladorExistenteOptional.isPresent()) {
                    Cliente clienteAvaladorExistente = clienteAvaladorExistenteOptional.get();
                    clienteExistente.setClienteAvalador(clienteAvaladorExistente);
                } else {
                    // El cliente avalador proporcionado no existe, devolver un error 404
                    return ResponseEntity.notFound().build();
                }
            } else {
                // No se proporcionó un cliente avalador en la solicitud, establecerlo como null
                clienteExistente.setClienteAvalador(null);
            }

            Cliente clienteGuardado = clienteService.guardarCliente(clienteExistente);
            return ResponseEntity.ok(clienteGuardado);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        Optional<Cliente> clienteExistente = clienteService.buscarClientePorId(id);
        if (clienteExistente.isPresent()) {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
