package edu.unc.pereyramonzon.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unc.pereyramonzon.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
