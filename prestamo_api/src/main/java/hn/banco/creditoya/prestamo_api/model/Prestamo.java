package hn.banco.creditoya.prestamo_api.model;

import java.math.BigDecimal;

import hn.banco.creditoya.prestamo_api.enumeration.TipoPrestamo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestamo")
    private Long idPrestamo;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "plazo")
    private Integer plazo;

    @Column(name = "tasa_interes")
    private BigDecimal tasaInteres;
    
     @Column(name = "cuota")
    private BigDecimal cuota;
    private char estado;

    @Enumerated(EnumType.STRING)
    private TipoPrestamo tipoPrestamo;

    @ManyToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Cliente cliente;
}