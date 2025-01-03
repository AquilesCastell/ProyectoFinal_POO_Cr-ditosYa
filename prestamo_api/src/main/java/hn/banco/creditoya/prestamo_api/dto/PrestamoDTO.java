package hn.banco.creditoya.prestamo_api.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoDTO {
    private Long idPrestamo;
    private BigDecimal monto;
    private Integer plazo;
    private BigDecimal tasaInteres;
    private BigDecimal cuota;
    private String estado;
    private String tipoPrestamo;
    private List<TablaAmortizacionDTO> tablaAmortizacion;
}