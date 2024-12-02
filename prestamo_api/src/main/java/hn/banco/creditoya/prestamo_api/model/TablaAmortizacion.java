package hn.banco.creditoya.prestamo_api.model;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tabla_amortizacion")
public class TablaAmortizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Relación M:1 con Prestamos
    @ManyToOne
    @JoinColumn(name = "idPrestamo")
    private Prestamo prestamo;

    @Column(name = "numeroCuota")
    private Integer numeroCuota;

    @Column(name = "interes")
    private BigDecimal interes;

    @Column(name = "capital")
    private BigDecimal capital;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fechaVencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
}
