package hn.banco.creditoya.prestamo_api.model;

import java.time.LocalDate;
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
    private int id;

    @ManyToOne
    @JoinColumn(name = "idPrestamo", referencedColumnName = "idPrestamo")
    private Prestamo prestamo;

    private int numeroCuota;
    private double interes;
    private double capital;
    private double saldo;
    private char estado;
    private LocalDate fechaVencimiento;
}
