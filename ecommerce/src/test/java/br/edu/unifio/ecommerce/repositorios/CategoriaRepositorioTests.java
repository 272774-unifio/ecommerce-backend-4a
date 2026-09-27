package br.edu.unifio.ecommerce.repositorios;

import br.edu.unifio.ecommerce.Categoria;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveBuscarUmaCategoriaPorId() {

        Categoria categoria = categoriaRepositorio
                .findById(Short.parseShort("1"))
                .orElseThrow();

        assertNotNull(categoria);
        assertEquals("Eletrônicos", categoria.getNome());
    }

    @Test
    public void deveListarTodasAsCategorias() {

        List<Categoria> categorias = categoriaRepositorio.findAll();

        assertNotNull(categorias);
       assertTrue(categorias.size() >= 5);
    }
}