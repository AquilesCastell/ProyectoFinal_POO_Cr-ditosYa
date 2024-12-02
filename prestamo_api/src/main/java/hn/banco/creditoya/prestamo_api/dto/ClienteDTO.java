package hn.banco.creditoya.prestamo_api.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private BigDecimal sueldo;
    private List<DireccionDTO> direcciones;
    private List<PrestamoDTO> prestamos;
}
