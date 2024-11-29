package hn.banco.creditoya.prestamo_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.banco.creditoya.prestamo_api.model.TablaAmortizacion;

public interface TablaAmortizacionRepositorio extends JpaRepository<TablaAmortizacion, Integer> {
    List<TablaAmortizacion> findByPrestamoIdPrestamo(int prestamoId);
}
