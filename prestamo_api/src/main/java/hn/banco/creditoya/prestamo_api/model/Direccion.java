package hn.banco.creditoya.prestamo_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDireccion")
    private Long idDireccion;
    
    private String pais;
    private String departamento;
    private String ciudad;
    private String colonia;
    private String referencia;
    
    // Relación M:1 con Cliente
    @ManyToOne
    @JoinColumn(name = "dni")
    private Cliente cliente;

}