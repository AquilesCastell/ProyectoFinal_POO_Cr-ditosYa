package hn.banco.creditoya.prestamo_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hn.banco.creditoya.prestamo_api.model.Direccion;

@Repository
public interface DireccionRepositorio extends JpaRepository<Direccion, Long> {
    List<Direccion> findByCliente_Dni(String dni);
    
    @Query("SELECT COUNT(d) FROM Direccion d WHERE d.cliente.dni = :dni")
    int countByClienteDni(String dni);
}
