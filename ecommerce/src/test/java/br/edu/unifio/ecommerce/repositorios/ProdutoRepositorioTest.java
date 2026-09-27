package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.Categoria;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ProdutoRepositorioTest {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    void deveBuscarProdutoPorId() {

        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Produtos eletrônicos");

        Categoria categoriaSalva = categoriaRepositorio.save(categoria);

        Produto produto = new Produto();

        produto.setNome("Notebook");
        produto.setDescricao("Notebook para estudos");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("3500.00"));
        produto.setCategoria(categoriaSalva);

        Produto produtoSalvo = produtoRepositorio.save(produto);

        Produto produtoEncontrado = produtoRepositorio
                .findById(produtoSalvo.getId())
                .orElse(null);

        assertNotNull(produtoEncontrado);

        assertEquals("Notebook", produtoEncontrado.getNome());
        assertEquals("Notebook para estudos", produtoEncontrado.getDescricao());

        assertEquals(
                categoriaSalva.getId(),
                produtoEncontrado.getCategoria().getId()
        );
    }

    @Test
    void deveListarProdutos() {

        int quantidadeAntes = produtoRepositorio.findAll().size();

        Categoria categoria = new Categoria();
        categoria.setNome("Livros");
        categoria.setDescricao("Livros para estudo");

        Categoria categoriaSalva = categoriaRepositorio.save(categoria);

        Produto produto1 = new Produto();

        produto1.setNome("Livro de Java");
        produto1.setDescricao("Livro sobre programação Java");
        produto1.setEstoque((short) 5);
        produto1.setPreco(new BigDecimal("100.00"));
        produto1.setCategoria(categoriaSalva);

        Produto produto2 = new Produto();

        produto2.setNome("Livro de Banco de Dados");
        produto2.setDescricao("Livro sobre banco de dados");
        produto2.setEstoque((short) 8);
        produto2.setPreco(new BigDecimal("120.00"));
        produto2.setCategoria(categoriaSalva);

        produtoRepositorio.save(produto1);
        produtoRepositorio.save(produto2);

        var produtos = produtoRepositorio.findAll();

        assertNotNull(produtos);

        assertEquals(quantidadeAntes + 2, produtos.size());

        assertTrue(produtos.stream()
                .anyMatch(p -> p.getNome().equals("Livro de Java")));

        assertTrue(produtos.stream()
                .anyMatch(p -> p.getNome().equals("Livro de Banco de Dados")));
    }
}