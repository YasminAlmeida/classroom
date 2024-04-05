package br.com.vainaweb.classroom.repository;

import br.com.vainaweb.classroom.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
}
