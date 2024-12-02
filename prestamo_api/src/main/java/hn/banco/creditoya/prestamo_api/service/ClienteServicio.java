package hn.banco.creditoya.prestamo_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hn.banco.creditoya.prestamo_api.dto.ClienteDTO;
import hn.banco.creditoya.prestamo_api.model.Cliente;
import hn.banco.creditoya.prestamo_api.repository.ClienteRepositorio;
import hn.banco.creditoya.prestamo_api.repository.DireccionRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import springfox.documentation.swagger2.mappers.ModelMapper;

@Service
@RequiredArgsConstructor
public class ClienteServicio {
    
        private final ClienteRepositorio clienteRepository;
    private final DireccionRepositorio direccionRepository;
    @SuppressWarnings("deprecation")
    private final ModelMapper modelMapper;

    @Transactional
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        // Validar límite de direcciones
        if (clienteDTO.getDirecciones().size() > 2) {
            throw new RuntimeException("Un cliente no puede tener más de dos direcciones");
        }

        // Mapear DTO a entidad
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

        // Establecer relación bidireccional
        cliente.getDirecciones().forEach(direccion -> direccion.setCliente(cliente));

        // Guardar cliente
        return modelMapper.map(clienteRepository.save(cliente), ClienteDTO.class);
    }

    public ClienteDTO obtenerClientePorDni(String dni) {
        Cliente cliente = clienteRepository.findByDni(dni)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAllWithDetails().stream()
            .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
            .collect(Collectors.toList());
    }

}
