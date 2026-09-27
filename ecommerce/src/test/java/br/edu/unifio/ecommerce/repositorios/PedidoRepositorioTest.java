package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PedidoRepositorioTest {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    void deveBuscarPedidoPorId() {

        Cliente cliente = new Cliente();

        cliente.setNome("João");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("43999990000");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 10, 14, 30));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("250.00"));
        pedido.setCliente(clienteSalvo);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        Pedido pedidoEncontrado = pedidoRepositorio
                .findById(pedidoSalvo.getId())
                .orElse(null);

        assertNotNull(pedidoEncontrado);

        assertEquals("PENDENTE", pedidoEncontrado.getStatus());
        assertEquals(
                new BigDecimal("250.00"),
                pedidoEncontrado.getValorTotal()
        );

        assertEquals(
                clienteSalvo.getId(),
                pedidoEncontrado.getCliente().getId()
        );
    }

    @Test
    void deveListarPedidos() {

        int quantidadeAntes = pedidoRepositorio.findAll().size();

        Cliente cliente = new Cliente();

        cliente.setNome("Ana");
        cliente.setEmail("ana@email.com");
        cliente.setTelefone("43988880000");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Pedido pedido1 = new Pedido();

        pedido1.setData(LocalDateTime.of(2026, 9, 11, 10, 0));
        pedido1.setStatus("PENDENTE");
        pedido1.setValorTotal(new BigDecimal("100.00"));
        pedido1.setCliente(clienteSalvo);

        Pedido pedido2 = new Pedido();

        pedido2.setData(LocalDateTime.of(2026, 9, 12, 15, 0));
        pedido2.setStatus("ENVIADO");
        pedido2.setValorTotal(new BigDecimal("200.00"));
        pedido2.setCliente(clienteSalvo);

        pedidoRepositorio.save(pedido1);
        pedidoRepositorio.save(pedido2);

        var pedidos = pedidoRepositorio.findAll();

        assertNotNull(pedidos);

        assertEquals(quantidadeAntes + 2, pedidos.size());

        assertTrue(pedidos.stream()
                .anyMatch(p -> p.getStatus().equals("PENDENTE")));

        assertTrue(pedidos.stream()
                .anyMatch(p -> p.getStatus().equals("ENVIADO")));
    }
}