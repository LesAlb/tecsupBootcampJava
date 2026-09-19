package com.examen.pedidos.Service.impl;

import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.entity.DetallePedido;
import com.examen.pedidos.entity.Pedido;
import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.exception.PedidoNotFoundException;
import com.examen.pedidos.exception.StockInsuficienteException;
import com.examen.pedidos.repository.ClienteRepository;
import com.examen.pedidos.repository.PedidoRepository;
import com.examen.pedidos.repository.ProductoRepository;
import com.examen.pedidos.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    //happy path
    @Test
    void crearPedido_Valido_DeberiaCrearPedido() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Laptop");
        producto.setPrecio(1000.0);
        producto.setStock(10);

        DetallePedido detalle = DetallePedido.builder()
                .productoId(1L)
                .cantidad(2)
                .build();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDetalles(Collections.singletonList(detalle));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(inv -> inv.getArgument(0));

        Pedido resultado = pedidoService.crearPedido(pedido);

        assertNotNull(resultado);
        assertEquals("CREADO", resultado.getEstado());
        assertEquals(2000.0, resultado.getTotal());
        assertEquals(8, producto.getStock());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    // stock insuficiente
    @Test
    void crearPedido_StockInsuficiente_DeberiaLanzarExcepcion() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Laptop");
        producto.setPrecio(1000.0);
        producto.setStock(1);

        DetallePedido detalle = DetallePedido.builder()
                .productoId(1L)
                .cantidad(5)
                .build();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDetalles(Collections.singletonList(detalle));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        assertThrows(StockInsuficienteException.class, () -> pedidoService.crearPedido(pedido));
    }

    // pedido no encontrado
    @Test
    void buscarPedido_IdInexistente_DeberiaLanzarExcepcion() {
        Long pedidoId = 99L;
        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () -> pedidoService.buscarPorId(pedidoId));
    }
}
