-- Inserir dados na tabela 'tb_user'
INSERT INTO tb_user (name, email) VALUES ('João Silva', 'joao@exemplo.com');
INSERT INTO tb_user (name, email) VALUES ('Maria Oliveira', 'maria@exemplo.com');
INSERT INTO tb_user (name, email) VALUES ('Carlos Souza', 'carlos@exemplo.com');

-- Inserir dados na tabela 'tb_task' (relacionando com usuários)
-- Tarefas do João Silva (user_id = 1)
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Pagar contas', 'Pagar a conta de luz e internet até sexta-feira', false, 1);
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Revisar relatório', 'Finalizar e revisar o relatório mensal da empresa', true, 1);

-- Tarefas da Maria Oliveira (user_id = 2)
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Lista de compras', 'Comprar leite, ovos, pão e café para a semana', true, 2);
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Marcar consulta médica', 'Agendar um check-up com o clínico geral', false, 2);

-- Tarefas do Carlos Souza (user_id = 3)
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Trocar óleo do carro', 'Levar o carro para a troca de óleo e revisão', false, 3);
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Estudar para a prova', 'Revisar os capítulos 3 a 5 do livro de matemática', true, 3);
