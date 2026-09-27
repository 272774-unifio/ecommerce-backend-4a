package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.Categoria;

@SpringBootTest
public class CategoriaRepositorioTest {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    void deveBuscarCategoriaPorId() {

        Categoria categoria = new Categoria();

        categoria.setNome("Teste");
        categoria.setDescricao("Categoria para teste");

        Categoria categoriaSalva = categoriaRepositorio.save(categoria);

        Categoria categoriaEncontrada = categoriaRepositorio
                .findById(categoriaSalva.getId())
                .orElse(null);

        assertNotNull(categoriaEncontrada);
        assertEquals("Teste", categoriaEncontrada.getNome());
        assertEquals("Categoria para teste", categoriaEncontrada.getDescricao());
    }

    @Test
    void deveListarCategorias() {

        int quantidadeAntes = categoriaRepositorio.findAll().size();

        Categoria categoria1 = new Categoria();
        categoria1.setNome("Roupas");
        categoria1.setDescricao("Produtos de roupas");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Livros");
        categoria2.setDescricao("Livros e materiais de leitura");

        categoriaRepositorio.save(categoria1);
        categoriaRepositorio.save(categoria2);

        var categorias = categoriaRepositorio.findAll();

        assertNotNull(categorias);
        assertEquals(quantidadeAntes + 2, categorias.size());

        assertTrue(categorias.stream()
                .anyMatch(c -> c.getNome().equals("Roupas")));

        assertTrue(categorias.stream()
                .anyMatch(c -> c.getNome().equals("Livros")));
    }
}