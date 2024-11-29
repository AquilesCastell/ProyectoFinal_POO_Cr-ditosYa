package hn.banco.creditoya.prestamo_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @ManyToMany
    @JoinTable(
        name = "cliente_prestamos",
        joinColumns = @JoinColumn(name = "dni"),
        inverseJoinColumns = @JoinColumn(name = "idPrestamo")
    )
    private List<Prestamo> prestamos;
}