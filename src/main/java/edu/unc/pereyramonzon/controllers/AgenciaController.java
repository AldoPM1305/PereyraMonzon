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

import edu.unc.pereyramonzon.domain.Agencia;
import edu.unc.pereyramonzon.services.AgenciaService;

@RestController
@RequestMapping("api/v1/agencias")
public class AgenciaController {
    
    @Autowired
    private AgenciaService agenciaService;
    
    @GetMapping
    public List<Agencia> listarAgencias() {
        return agenciaService.listarAgencias();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAgenciaPorId(@PathVariable Long id) {
        Optional<Agencia> agenciaOptional = agenciaService.buscarAgenciaPorId(id);
        if (agenciaOptional.isPresent()) {
            return ResponseEntity.ok(agenciaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<?> crearAgencia(@RequestBody Agencia agencia) {
        Agencia nuevaAgencia = agenciaService.guardarAgencia(agencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAgencia);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editarAgencia(@PathVariable Long id, @RequestBody Agencia agencia) {
        Optional<Agencia> agenciaExistente = agenciaService.buscarAgenciaPorId(id);
        if (agenciaExistente.isPresent()) {
            Agencia agenciaActualizada = agenciaExistente.get();
            // Actualizar los campos de la agencia
            agenciaActualizada.setNombre(agencia.getNombre());
            agenciaActualizada.setUbicacion(agencia.getUbicacion());
            agenciaActualizada.setTelefono(agencia.getTelefono());
            
            Agencia agenciaGuardada = agenciaService.guardarAgencia(agenciaActualizada);
            return ResponseEntity.ok(agenciaGuardada);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAgencia(@PathVariable Long id) {
        Optional<Agencia> agenciaExistente = agenciaService.buscarAgenciaPorId(id);
        if (agenciaExistente.isPresent()) {
            agenciaService.eliminarAgencia(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
