package hn.banco.creditoya.prestamo_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hn.banco.creditoya.prestamo_api.model.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByDni(String dni);

    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.direcciones LEFT JOIN FETCH c.prestamos")
    List<Cliente> findAllWithDetails();
}
