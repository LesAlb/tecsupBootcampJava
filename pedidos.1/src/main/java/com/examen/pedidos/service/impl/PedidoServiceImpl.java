package com.examen.pedidos.service.impl;

import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.entity.DetallePedido;
import com.examen.pedidos.entity.Pedido;
import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.exception.PedidoNotFoundException;
import com.examen.pedidos.exception.StockInsuficienteException;
import com.examen.pedidos.repository.ClienteRepository;
import com.examen.pedidos.repository.PedidoRepository;
import com.examen.pedidos.repository.ProductoRepository;
import com.examen.pedidos.service.PedidoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ClienteRepository clienteRepository,
                             ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        // Validar cliente
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDate.now());
        pedido.setEstado("CREADO");

        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0;

        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (detalle.getCantidad() <= 0) {
                throw new RuntimeException("Cantidad inválida");
            }

            if (producto.getStock() < detalle.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Calcular subtotal
            double subtotal = detalle.getCantidad() * producto.getPrecio();

            // Construir detalle
            DetallePedido nuevoDetalle = DetallePedido.builder()
                    .productoId(producto.getId())
                    .nombreProducto(producto.getNombre())
                    .cantidad(detalle.getCantidad())
                    .precioUnitario(producto.getPrecio())
                    .subtotal(subtotal)
                    .pedido(pedido)
                    .build();

            detalles.add(nuevoDetalle);
            total += subtotal;

            // Descontar stock
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido no encontrado con id: " + id));
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> listarPedidosPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return cliente.getPedidos();
    }
}
