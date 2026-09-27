package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.Categoria;
import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.ItemPedido;
import br.edu.unifio.ecommerce.entidades.Pedido;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ItemPedidoRepositorioTest {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    void deveBuscarItemPedidoPorId() {

        Categoria categoria = new Categoria();
        categoria.setNome("Informática");
        categoria.setDescricao("Produtos de informática");

        Categoria categoriaSalva = categoriaRepositorio.save(categoria);

        Produto produto = new Produto();
        produto.setNome("Mouse");
        produto.setDescricao("Mouse sem fio");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("80.00"));
        produto.setCategoria(categoriaSalva);

        Produto produtoSalvo = produtoRepositorio.save(produto);

        Cliente cliente = new Cliente();
        cliente.setNome("Carlos");
        cliente.setEmail("carlos@email.com");
        cliente.setTelefone("43999991111");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 15, 10, 0));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("160.00"));
        pedido.setCliente(clienteSalvo);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        ItemPedido item = new ItemPedido();

        item.setQuantidade(2);
        item.setValorUnitario(new BigDecimal("80.00"));
        item.setPedido(pedidoSalvo);
        item.setProduto(produtoSalvo);

        ItemPedido itemSalvo = itemPedidoRepositorio.save(item);

        ItemPedido itemEncontrado = itemPedidoRepositorio
                .findById(itemSalvo.getId())
                .orElse(null);

        assertNotNull(itemEncontrado);

        assertEquals(2, itemEncontrado.getQuantidade());

        assertEquals(
                new BigDecimal("80.00"),
                itemEncontrado.getValorUnitario()
        );

        assertEquals(
                pedidoSalvo.getId(),
                itemEncontrado.getPedido().getId()
        );

        assertEquals(
                produtoSalvo.getId(),
                itemEncontrado.getProduto().getId()
        );
    }

    @Test
    void deveListarItensPedido() {

        int quantidadeAntes = itemPedidoRepositorio.findAll().size();

        Categoria categoria = new Categoria();
        categoria.setNome("Livros");
        categoria.setDescricao("Livros para estudo");

        Categoria categoriaSalva = categoriaRepositorio.save(categoria);

        Produto produto1 = new Produto();
        produto1.setNome("Livro Java");
        produto1.setDescricao("Livro de Java");
        produto1.setEstoque((short) 10);
        produto1.setPreco(new BigDecimal("100.00"));
        produto1.setCategoria(categoriaSalva);

        Produto produto2 = new Produto();
        produto2.setNome("Livro SQL");
        produto2.setDescricao("Livro de SQL");
        produto2.setEstoque((short) 8);
        produto2.setPreco(new BigDecimal("120.00"));
        produto2.setCategoria(categoriaSalva);

        Produto produtoSalvo1 = produtoRepositorio.save(produto1);
        Produto produtoSalvo2 = produtoRepositorio.save(produto2);

        Cliente cliente = new Cliente();
        cliente.setNome("Fernanda");
        cliente.setEmail("fernanda@email.com");
        cliente.setTelefone("43988887777");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 16, 14, 0));
        pedido.setStatus("ENVIADO");
        pedido.setValorTotal(new BigDecimal("220.00"));
        pedido.setCliente(clienteSalvo);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        ItemPedido item1 = new ItemPedido();
        item1.setQuantidade(1);
        item1.setValorUnitario(new BigDecimal("100.00"));
        item1.setPedido(pedidoSalvo);
        item1.setProduto(produtoSalvo1);

        ItemPedido item2 = new ItemPedido();
        item2.setQuantidade(1);
        item2.setValorUnitario(new BigDecimal("120.00"));
        item2.setPedido(pedidoSalvo);
        item2.setProduto(produtoSalvo2);

        itemPedidoRepositorio.save(item1);
        itemPedidoRepositorio.save(item2);

        var itens = itemPedidoRepositorio.findAll();

        assertNotNull(itens);

        assertEquals(quantidadeAntes + 2, itens.size());

        assertTrue(itens.stream()
                .anyMatch(i -> i.getValorUnitario()
                        .equals(new BigDecimal("100.00"))));

        assertTrue(itens.stream()
                .anyMatch(i -> i.getValorUnitario()
                        .equals(new BigDecimal("120.00"))));
    }
}