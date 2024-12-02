package hn.banco.creditoya.prestamo_api.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.banco.creditoya.prestamo_api.dto.CrearPrestamoRequest;
import hn.banco.creditoya.prestamo_api.dto.PrestamoDTO;
import hn.banco.creditoya.prestamo_api.enumeration.TipoPrestamo;
import hn.banco.creditoya.prestamo_api.model.Cliente;
import hn.banco.creditoya.prestamo_api.model.Prestamo;
import hn.banco.creditoya.prestamo_api.model.TablaAmortizacion;
import hn.banco.creditoya.prestamo_api.repository.ClienteRepositorio;
import hn.banco.creditoya.prestamo_api.repository.PrestamoRepositorio;
import hn.banco.creditoya.prestamo_api.repository.TablaAmortizacionRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import springfox.documentation.swagger2.mappers.ModelMapper;

@Service
@RequiredArgsConstructor
public class PrestamoServicio {
    private final PrestamoRepositorio prestamosRepository;
    private final ClienteRepositorio clienteRepository;
    private final TablaAmortizacionRepositorio tablaAmortizacionRepository;
    private final ModelMapper modelMapper;

    @Value("${prestamo.tasa.vehicular}")
    private BigDecimal tasaVehicular;

    @Value("${prestamo.tasa.personal}")
    private BigDecimal tasaPersonal;

    @Value("${prestamo.tasa.hipotecario}")
    private BigDecimal tasaHipotecario;

    @Transactional
    public PrestamoDTO crearPrestamo(CrearPrestamoRequest request) {
        // Validar cliente
        Cliente cliente = clienteRepository.findByDni(request.getDni())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Calcular tasa de interés según el tipo de préstamo
        BigDecimal tasaInteres = obtenerTasaInteresPorTipo(TipoPrestamo.valueOf(request.getTipoPrestamo()));

        // Validar plazo mínimo
        if (request.getPlazo() < 1) {
            throw new IllegalArgumentException("El plazo mínimo debe ser de 1 año");
        }

        // Calcular cuota
        BigDecimal cuota = calcularCuota(
            request.getMonto(), 
            tasaInteres, 
            request.getPlazo()
        );

        // Validar nivel de endeudamiento
        validarNivelEndeudamiento(cliente, cuota);

        // Crear préstamo
        Prestamo prestamo = Prestamo.builder()
            .monto(request.getMonto())
            .plazo(request.getPlazo())
            .tasaInteres(tasaInteres)
            .cuota(cuota)
            .estado("A")
            .tipoPrestamo(TipoPrestamo.valueOf(request.getTipoPrestamo()))
            .build();

        // Asociar préstamo al cliente
        prestamo.setClientes(List.of(cliente));

        // Guardar préstamo
        prestamo = prestamosRepository.save(prestamo);

        // Generar tabla de amortización
        generarTablaAmortizacion(prestamo);

        return modelMapper.map(prestamo, PrestamoDTO.class);
    }

    private BigDecimal obtenerTasaInteresPorTipo(TipoPrestamo tipo) {
        return switch (tipo) {
            case V -> tasaVehicular;
            case P -> tasaPersonal;
            case H -> tasaHipotecario;
        };
    }

    private BigDecimal calcularCuota(BigDecimal monto, BigDecimal tasaInteres, Integer plazo) {
        BigDecimal tasaMensual = tasaInteres.divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_UP);
        int numeroPagos = plazo * 12;
        
        BigDecimal numerador = monto.multiply(tasaMensual)
            .multiply(BigDecimal.ONE.add(tasaMensual).pow(numeroPagos));
        
        BigDecimal denominador = BigDecimal.ONE.add(tasaMensual)
            .pow(numeroPagos).subtract(BigDecimal.ONE);
        
        return numerador.divide(denominador, 2, RoundingMode.HALF_UP);
    }

    private void validarNivelEndeudamiento(Cliente cliente, BigDecimal nuevaCuota) {
        // Calcular total de egresos actuales
        BigDecimal totalEgresos = cliente.getPrestamos().stream()
            .map(Prestamo::getCuota)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .add(nuevaCuota);

        // Validar que no supere el 40% del sueldo
        BigDecimal porcentajeEndeudamiento = totalEgresos.divide(cliente.getSueldo(), 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));

        if (porcentajeEndeudamiento.compareTo(BigDecimal.valueOf(40)) > 0) {
            throw new RuntimeException("El nivel de endeudamiento supera el 40% del sueldo");
        }
    }

    private void generarTablaAmortizacion(Prestamo prestamo) {
        BigDecimal saldoInicial = prestamo.getMonto();
        BigDecimal tasaMensual = prestamo.getTasaInteres().divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_UP);
        int numeroPagos = prestamo.getPlazo() * 12;

        List<TablaAmortizacion> tablaAmortizacion = new ArrayList<>();

        // Registro inicial
        TablaAmortizacion registroInicial = TablaAmortizacion.builder()
            .prestamo(prestamo)
            .numeroCuota(0)
            .interes(BigDecimal.ZERO)
            .capital(BigDecimal.ZERO)
            .saldo(saldoInicial)
            .estado("P")
            .build();
        tablaAmortizacion.add(registroInicial);

        // Generar cuotas
        for (int i = 1; i <= numeroPagos; i++) {
            BigDecimal interes = saldoInicial.multiply(tasaMensual).setScale(2, RoundingMode.HALF_UP);
            BigDecimal capital = prestamo.getCuota().subtract(interes).setScale(2, RoundingMode.HALF_UP);
            saldoInicial = saldoInicial.subtract(capital).setScale(2, RoundingMode.HALF_UP);

            TablaAmortizacion cuota = TablaAmortizacion.builder()
                .prestamo(prestamo)
                .numeroCuota(i)
                .interes(interes)
                .capital(capital)
                .saldo(saldoInicial)
                .estado("P")
                .build();

            tablaAmortizacion.add(cuota);
        }

        tablaAmortizacionRepository.saveAll(tablaAmortizacion);
    }
}
