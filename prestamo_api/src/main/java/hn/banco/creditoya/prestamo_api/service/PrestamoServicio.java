package hn.banco.creditoya.prestamo_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.banco.creditoya.prestamo_api.model.Cliente;
import hn.banco.creditoya.prestamo_api.model.Prestamo;
import hn.banco.creditoya.prestamo_api.repository.ClienteRepositorio;
import hn.banco.creditoya.prestamo_api.repository.PrestamoRepositorio;
import hn.banco.creditoya.prestamo_api.repository.TablaAmortizacionRepositorio;
import lombok.Value;

@Service
public class PrestamoServicio {
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private TablaAmortizacionRepositorio tablaAmortizacionRepositorio;
    @Value("${tasa.interes.V}")
    private double tasaVehicular;
    @Value("${tasa.interes.P}")
    private double tasaPersonal;
    @Value("${tasa.interes.H}")
    private double tasaHipotecario;

    public Prestamo crearPrestamo(Prestamo prestamo) {
        // Validar plazo mínimo
        if (prestamo.getPlazo() < 1) {
            throw new IllegalArgumentException("El plazo mínimo debe ser 1 año");
        }

        // Establecer tasa de interés según el tipo de préstamo
        switch (prestamo.getTipoPrestamo()) {
            case V: prestamo.setTasa_interes(tasaVehicular); break;
            case P: prestamo.setTasa_interes(tasaPersonal); break;
            case H: prestamo.setTasa_interes(tasaHipotecario); break;
        }

        // Calcular cuota usando la fórmula proporcionada
        double r = prestamo.getTasa_interes() / 12;
        int n = prestamo.getPlazo() * 12;
        double cuota = (prestamo.getMonto() * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        prestamo.setCuota(cuota);

        // Validar nivel de endeudamiento
        Cliente cliente = prestamo.getCliente();
        double totalEgresos = cliente.getPrestamos().stream()
            .mapToDouble(Prestamo::getCuota)
            .sum() + cuota;
        
        if ((totalEgresos / cliente.getSueldo()) > 0.4) {
            throw new IllegalArgumentException("El nivel de endeudamiento supera el 40% del sueldo");
        }

        Prestamo nuevoPrestamo = prestamoRepositorio.save(prestamo);
        generarTablaAmortizacion(nuevoPrestamo);
        return nuevoPrestamo;
    }

    private void generarTablaAmortizacion(Prestamo prestamo) {
        // Implementar lógica de generación de tabla de amortización
        // Similar a la descripción detallada en los requisitos
    }
}
