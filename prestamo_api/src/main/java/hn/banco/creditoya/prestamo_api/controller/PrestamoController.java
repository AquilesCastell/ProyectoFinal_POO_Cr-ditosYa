package hn.banco.creditoya.prestamo_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.banco.creditoya.prestamo_api.dto.PrestamoDTO;
import hn.banco.creditoya.prestamo_api.service.PrestamoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/prestamo")
@RequiredArgsConstructor
@Tag(name = "Controlador de Préstamos", description = "Operaciones relacionadas con préstamos")
public class PrestamoController {
    private final PrestamoServicio prestamoService;

    @GetMapping("/cliente/{dni}")
    @Operation(summary = "Buscar préstamos por DNI", description = "Devuelve los préstamos asociados a un cliente")
    public ResponseEntity<List<PrestamoDTO>> buscarPrestamoPorDni(@PathVariable String dni) {
        // Implementar la lógica de búsqueda de préstamos por DNI
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{idPrestamo}")
    @Operation(summary = "Buscar préstamo por ID", description = "Devuelve la información de un préstamo específico")
    public ResponseEntity<PrestamoDTO> buscarPrestamoPorId(@PathVariable Long idPrestamo) {
        // Implementar la lógica de búsqueda de préstamo por ID
        return ResponseEntity.ok(null);
    }
}