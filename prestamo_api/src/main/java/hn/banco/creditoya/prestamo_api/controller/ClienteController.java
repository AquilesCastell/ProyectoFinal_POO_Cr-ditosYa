package hn.banco.creditoya.prestamo_api.controller;

import hn.banco.creditoya.prestamo_api.dto.ClienteDTO;
import hn.banco.creditoya.prestamo_api.dto.CrearPrestamoRequest;
import hn.banco.creditoya.prestamo_api.dto.PrestamoDTO;
import hn.banco.creditoya.prestamo_api.service.ClienteServicio;
import hn.banco.creditoya.prestamo_api.service.PrestamoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
@Tag(name = "Controlador de Clientes", description = "Operaciones relacionadas con clientes")
public class ClienteController {
    private final ClienteServicio clienteService;
    private final PrestamoServicio prestamoService;

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Permite crear un cliente con sus direcciones")
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.crearCliente(clienteDTO));
    }

    @GetMapping("/{dni}")
    @Operation(summary = "Obtener cliente por DNI", description = "Busca un cliente usando su número de identificación")
    public ResponseEntity<ClienteDTO> obtenerClientePorDni(@PathVariable String dni) {
        return ResponseEntity.ok(clienteService.obtenerClientePorDni(dni));
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes con sus detalles")
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodosLosClientes());
    }

    @PostMapping("/{dni}/prestamo")
    @Operation(summary = "Crear préstamo para un cliente", description = "Permite crear un préstamo asociado a un cliente")
    public ResponseEntity<PrestamoDTO> crearPrestamo(
        @PathVariable String dni, 
        @Valid @RequestBody CrearPrestamoRequest request
    ) {
        request.setDni(dni);
        return ResponseEntity.ok(prestamoService.crearPrestamo(request));
    }
}