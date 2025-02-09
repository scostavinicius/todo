-- Inserir dados na tabela 'tb_user'
INSERT INTO tb_user (name, email) VALUES ('João Silva', 'joao@exemplo.com');
INSERT INTO tb_user (name, email) VALUES ('Maria Oliveira', 'maria@exemplo.com');
INSERT INTO tb_user (name, email) VALUES ('Carlos Souza', 'carlos@exemplo.com');

-- Inserir dados na tabela 'tb_task' (relacionando com usuários)
-- Tarefas do João Silva (user_id = 1)
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Tarefa 1 do João', 'Descrição da tarefa 1 do João', false, 1);
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Tarefa 2 do João', 'Descrição da tarefa 2 do João', true, 1);

-- Tarefas da Maria Oliveira (user_id = 2)
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Tarefa 1 da Maria', 'Descrição da tarefa 1 da Maria', true, 2);
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Tarefa 2 da Maria', 'Descrição da tarefa 2 da Maria', false, 2);

-- Tarefas do Carlos Souza (user_id = 3)
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Tarefa 1 do Carlos', 'Descrição da tarefa 1 do Carlos', false, 3);
INSERT INTO tb_task (title, description, completed, user_id) VALUES ('Tarefa 2 do Carlos', 'Descrição da tarefa 2 do Carlos', true, 3);
