package hn.banco.creditoya.prestamo_api.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hn.banco.creditoya.prestamo_api.model.Prestamo;
import hn.banco.creditoya.prestamo_api.model.TablaAmortizacion;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long> {
   
    List<Prestamo> findByClientes_Dni(String dni);
    
    @Query("SELECT SUM(ta.saldo) FROM TablaAmortizacion ta WHERE ta.prestamo.idPrestamo = :idPrestamo AND ta.estado = 'P'")
    BigDecimal calcularSaldoPendiente(Long idPrestamo);
    
    @Query("SELECT ta FROM TablaAmortizacion ta " +
           "WHERE ta.prestamo.idPrestamo = :idPrestamo " +
           "AND ta.estado = 'P' " +
           "ORDER BY ta.numeroCuota ASC LIMIT 1")
    Optional<TablaAmortizacion> obtenerCuotaMasAntigua(Long idPrestamo);

}

