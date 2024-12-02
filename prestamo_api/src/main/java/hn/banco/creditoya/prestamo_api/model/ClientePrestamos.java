package hn.banco.creditoya.prestamo_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente_prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientePrestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación M:1 con Cliente
    @ManyToOne
    @JoinColumn(name = "dni")
    private Cliente cliente;

    // Relación M:1 con Prestamos
    @ManyToOne
    @JoinColumn(name = "idPrestamo")
    private Prestamo prestamo;
}
