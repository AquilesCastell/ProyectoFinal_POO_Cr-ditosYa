package hn.banco.creditoya.prestamo_api.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearPrestamoRequest {
    private String dni;
    private BigDecimal monto;
    private Integer plazo;
    private String tipoPrestamo;
}
