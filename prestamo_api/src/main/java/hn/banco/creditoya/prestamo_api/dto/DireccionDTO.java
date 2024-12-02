package hn.banco.creditoya.prestamo_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionDTO {
    private Long idDireccion;
    private String pais;
    private String departamento;
    private String ciudad;
    private String colonia;
    private String referencia;
}