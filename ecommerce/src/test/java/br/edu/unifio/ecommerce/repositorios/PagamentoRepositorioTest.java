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
import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PagamentoRepositorioTest {

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    void deveBuscarPagamentoPorId() {

        Cliente cliente = new Cliente();

        cliente.setNome("Marcos");
        cliente.setEmail("marcos@email.com");
        cliente.setTelefone("43999992222");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 20, 10, 0));
        pedido.setStatus("ENVIADO");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(clienteSalvo);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("500.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 20, 10, 30));
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedidoSalvo);

        Pagamento pagamentoSalvo = pagamentoRepositorio.save(pagamento);

        Pagamento pagamentoEncontrado = pagamentoRepositorio
                .findById(pagamentoSalvo.getId())
                .orElse(null);

        assertNotNull(pagamentoEncontrado);

        assertEquals(
                new BigDecimal("500.00"),
                pagamentoEncontrado.getValor()
        );

        assertEquals(
                "APROVADO",
                pagamentoEncontrado.getStatus()
        );

        assertEquals(
                "PIX",
                pagamentoEncontrado.getTipo()
        );

        assertEquals(
                pedidoSalvo.getId(),
                pagamentoEncontrado.getPedido().getId()
        );
    }

    @Test
    void deveListarPagamentos() {

        int quantidadeAntes = pagamentoRepositorio.findAll().size();

        Cliente cliente = new Cliente();

        cliente.setNome("Juliana");
        cliente.setEmail("juliana@email.com");
        cliente.setTelefone("43988886666");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Pedido pedido1 = new Pedido();

        pedido1.setData(LocalDateTime.of(2026, 9, 21, 10, 0));
        pedido1.setStatus("PENDENTE");
        pedido1.setValorTotal(new BigDecimal("150.00"));
        pedido1.setCliente(clienteSalvo);

        Pedido pedido2 = new Pedido();

        pedido2.setData(LocalDateTime.of(2026, 9, 22, 11, 0));
        pedido2.setStatus("ENVIADO");
        pedido2.setValorTotal(new BigDecimal("250.00"));
        pedido2.setCliente(clienteSalvo);

        Pedido pedidoSalvo1 = pedidoRepositorio.save(pedido1);
        Pedido pedidoSalvo2 = pedidoRepositorio.save(pedido2);

        Pagamento pagamento1 = new Pagamento();

        pagamento1.setValor(new BigDecimal("150.00"));
        pagamento1.setData(LocalDateTime.of(2026, 9, 21, 10, 30));
        pagamento1.setStatus("PENDENTE");
        pagamento1.setTipo("CARTAO");
        pagamento1.setPedido(pedidoSalvo1);

        Pagamento pagamento2 = new Pagamento();

        pagamento2.setValor(new BigDecimal("250.00"));
        pagamento2.setData(LocalDateTime.of(2026, 9, 22, 11, 30));
        pagamento2.setStatus("APROVADO");
        pagamento2.setTipo("PIX");
        pagamento2.setPedido(pedidoSalvo2);

        pagamentoRepositorio.save(pagamento1);
        pagamentoRepositorio.save(pagamento2);

        var pagamentos = pagamentoRepositorio.findAll();

        assertNotNull(pagamentos);

        assertEquals(quantidadeAntes + 2, pagamentos.size());

        assertTrue(pagamentos.stream()
                .anyMatch(p -> p.getTipo().equals("CARTAO")));

        assertTrue(pagamentos.stream()
                .anyMatch(p -> p.getTipo().equals("PIX")));
    }
}