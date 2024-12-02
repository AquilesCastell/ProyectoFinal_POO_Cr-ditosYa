package hn.banco.creditoya.prestamo_api.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TablaAmortizacionDTO {
    private Long id;
    private Integer numeroCuota;
    private BigDecimal interes;
    private BigDecimal capital;
    private BigDecimal saldo;
    private String estado;
    private Date fechaVencimiento;
}