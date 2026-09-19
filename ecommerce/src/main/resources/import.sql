INSERT INTO categoria (nome, descricao) VALUES ('Eletrônicos', 'Produtos eletrônicos');
INSERT INTO categoria (nome, descricao) VALUES ('Informática', 'Produtos de informática');
INSERT INTO categoria (nome, descricao) VALUES ('Celulares', 'Celulares e acessórios');
INSERT INTO categoria (nome, descricao) VALUES ('Casa', 'Produtos para casa');
INSERT INTO categoria (nome, descricao) VALUES ('Esportes', 'Produtos esportivos');

INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) VALUES ('Notebook', 'Notebook para uso pessoal', 10, 3500.00, 2);
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) VALUES ('Mouse', 'Mouse sem fio', 25, 80.00, 2);
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) VALUES ('Celular', 'Smartphone', 15, 1800.00, 3);
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) VALUES ('Cadeira', 'Cadeira para escritório', 8, 450.00, 4);
INSERT INTO produto (nome, descricao, estoque, preco, categoria_id) VALUES ('Bola', 'Bola esportiva', 20, 100.00, 5);

INSERT INTO cliente (nome, email, telefone) VALUES ('João Silva', 'joao@email.com', '43999990001');
INSERT INTO cliente (nome, email, telefone) VALUES ('Maria Santos', 'maria@email.com', '43999990002');
INSERT INTO cliente (nome, email, telefone) VALUES ('Pedro Oliveira', 'pedro@email.com', '43999990003');
INSERT INTO cliente (nome, email, telefone) VALUES ('Ana Souza', 'ana@email.com', '43999990004');
INSERT INTO cliente (nome, email, telefone) VALUES ('Lucas Costa', 'lucas@email.com', '43999990005');

INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES ('2026-09-01 10:00:00', 'PAGO', 3500.00, 1);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES ('2026-09-02 14:30:00', 'PENDENTE', 1800.00, 2);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES ('2026-09-03 09:15:00', 'PAGO', 450.00, 3);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES ('2026-09-04 16:00:00', 'ENVIADO', 100.00, 4);
INSERT INTO pedido (data, status, valor_total, cliente_id) VALUES ('2026-09-05 11:45:00', 'PENDENTE', 80.00, 5);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 3500.00, 1, 1);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 1800.00, 2, 3);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 450.00, 3, 4);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 100.00, 4, 5);
INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 80.00, 5, 2);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (3500.00, '2026-09-01 10:30:00', 'APROVADO', 'PIX', 1);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (1800.00, '2026-09-02 15:00:00', 'PENDENTE', 'CARTAO', 2);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (450.00, '2026-09-03 09:30:00', 'APROVADO', 'PIX', 3);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (100.00, '2026-09-04 16:30:00', 'APROVADO', 'DINHEIRO', 4);
INSERT INTO pagamento (valor, data, status, tipo, pedido_id) VALUES (80.00, '2026-09-05 12:00:00', 'PENDENTE', 'CARTAO', 5);