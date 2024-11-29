package hn.banco.creditoya.prestamo_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.banco.creditoya.prestamo_api.model.Direccion;

public interface DireccionRepositorio extends JpaRepository<Direccion, Integer> {
    List<Direccion> findByClienteDni(String dni);
}
