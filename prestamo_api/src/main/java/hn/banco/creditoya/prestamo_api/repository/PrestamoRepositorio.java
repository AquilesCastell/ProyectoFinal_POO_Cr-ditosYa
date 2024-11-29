package hn.banco.creditoya.prestamo_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.banco.creditoya.prestamo_api.model.Prestamo;

public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByClienteDni(String dni);
}

