package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest
public class ClienteRepositorioTest {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    void deveBuscarClientePorId() {

        Cliente cliente = new Cliente();

        cliente.setNome("Maria");
        cliente.setEmail("maria@email.com");
        cliente.setTelefone("43999999999");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Cliente clienteEncontrado = clienteRepositorio
                .findById(clienteSalvo.getId())
                .orElse(null);

        assertNotNull(clienteEncontrado);
        assertEquals("Maria", clienteEncontrado.getNome());
        assertEquals("maria@email.com", clienteEncontrado.getEmail());
    }

    @Test
    void deveListarClientes() {

        int quantidadeAntes = clienteRepositorio.findAll().size();

        Cliente cliente1 = new Cliente();
        cliente1.setNome("Carlos");
        cliente1.setEmail("carlos@email.com");
        cliente1.setTelefone("43988880001");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Fernanda");
        cliente2.setEmail("fernanda@email.com");
        cliente2.setTelefone("43988880002");

        clienteRepositorio.save(cliente1);
        clienteRepositorio.save(cliente2);

        var clientes = clienteRepositorio.findAll();

        assertNotNull(clientes);
        assertEquals(quantidadeAntes + 2, clientes.size());

        assertTrue(clientes.stream()
                .anyMatch(c -> c.getEmail().equals("carlos@email.com")));

        assertTrue(clientes.stream()
                .anyMatch(c -> c.getEmail().equals("fernanda@email.com")));
    }
}