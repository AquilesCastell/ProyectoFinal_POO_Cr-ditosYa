package hn.banco.creditoya.prestamo_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.banco.creditoya.prestamo_api.model.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByDni(String dni);
}
