USE policia;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE ocorrencia;
TRUNCATE TABLE testemunha;
TRUNCATE TABLE autuante;
TRUNCATE TABLE autuado;
TRUNCATE TABLE posto_trabalho;    
TRUNCATE TABLE tipo_ocorrencia;
TRUNCATE TABLE administrador;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO administrador
(id_administrador, nome_administrador, data_nascimento_administrador, sexo_administrador, bi_administrador, nip_administrador, email_administrador, telefone_administrador, palavra_passe_administrador)
VALUES
(1, 'João Manuel', '1985-04-12', 'Masculino', '000000001LA041', 100001, 'joao.manuel@example.com', '923000001', 'admin123'),
(2, 'Maria da Conceição', '1990-08-22', 'Feminino', '000000002LA042', 100002, 'maria.conceicao@example.com', '923000002', 'admin123'),
(3, 'Pedro António', '1982-11-05', 'Masculino', '000000003LA043', 100003, 'pedro.antonio@example.com', '923000003', 'admin123'),
(4, 'Ana Beatriz', '1994-02-18', 'Feminino', '000000004LA044', 100004, 'ana.beatriz@example.com', '923000004', 'admin123'),
(5, 'Carlos Mateus', '1988-06-30', 'Masculino', '000000005LA045', 100005, 'carlos.mateus@example.com', '923000005', 'admin123');

INSERT INTO tipo_ocorrencia
(id_tipo_ocorrencia, nome_tipo_ocorrencia)
VALUES
(1, 'Roubo'),
(2, 'Furto'),
(3, 'Agressão física'),
(4, 'Dano material'),
(5, 'Perturbação pública'); 

INSERT INTO posto_trabalho
(id_posto_trabalho, nome_posto_trabalho, numero_posto_trabalho, id_municipio)
VALUES
(1, 'Comando Municipal de Luanda', 101, 1),
(2, 'Comando Municipal do Lubango', 102, 2),
(3, 'Comando Municipal de Benguela', 103, 3),
(4, 'Comando Municipal do Huambo', 104, 4),
(5, 'Comando Municipal de Malanje', 105, 5);

INSERT INTO autuado
(id_autuado, nome_autuado, pai_autuado, mae_autuado, bi_autuado, residencia_autuado, data_nascimento_autuado, sexo_autuado, proximidade_autuado, estado_civil_autuado, data_emissao_bi_autuado, data_validade_bi_autuado, telefone_autuado, id_profissao, id_municipio)
VALUES
(1, 'Miguel Afonso', 'Afonso Miguel', 'Rosa Maria', '100000001LA041', 'Bairro Popular', '1995-03-10', 'Masculino', 'Mercado Municipal', 'Solteiro', '2018-05-20', '2028-05-20', '924000001', 1, 1),
(2, 'Teresa Domingos', 'Domingos Paulo', 'Isabel Teresa', '100000002LA042', 'Rua Principal', '1992-07-14', 'Feminino', 'Escola Central', 'Casado', '2017-09-12', '2027-09-12', '924000002', 2, 2),
(3, 'António Paulo', 'Paulo José', 'Marta Isabel', '100000003LA043', 'Bairro Novo', '1989-12-01', 'Masculino', 'Hospital Municipal', 'Solteiro', '2016-04-08', '2026-04-08', '924000003', 3, 3),
(4, 'Helena Mateus', 'Mateus André', 'Carla Helena', '100000004LA044', 'Zona Comercial', '1998-01-25', 'Feminino', 'Banco Local', 'Divorciado', '2019-06-18', '2029-06-18', '924000004', 4, 4),
(5, 'Rui Manuel', 'Manuel Vicente', 'Joana Rita', '100000005LA045', 'Bairro da Paz', '1991-10-09', 'Masculino', 'Terminal Rodoviário', 'Casado', '2015-11-30', '2025-11-30', '924000005', 5, 5);

INSERT INTO autuante
(id_autuante, nome_autuante, pai_autuante, mae_autuante, bi_autuante, residencia_autuante, data_nascimento_autuante, sexo_autuante, altura_autuante, data_emissao_bi_autuante, data_validade_bi_autuante, nip_autuante, telefone_autuante, id_patente, id_municipio, id_posto_trabalho)
VALUES
(1, 'Fernando Lopes', 'Lopes Manuel', 'Ana Lopes', '200000001LA041', 'Centralidade Norte', '1984-02-15', 'Masculino', 1.78, '2016-03-10', '2026-03-10', 200001, '925000001', 1, 1, 1),
(2, 'Carla Mendes', 'Mendes António', 'Teresa Mendes', '200000002LA042', 'Bairro Azul', '1987-05-21', 'Feminino', 1.65, '2017-07-19', '2027-07-19', 200002, '925000002', 2, 2, 2),
(3, 'Paulo Henrique', 'Henrique João', 'Maria Paula', '200000003LA043', 'Rua das Acácias', '1981-09-03', 'Masculino', 1.82, '2015-12-04', '2025-12-04', 200003, '925000003', 3, 3, 3),
(4, 'Sandra Costa', 'Costa Miguel', 'Lurdes Costa', '200000004LA044', 'Bairro Industrial', '1990-11-27', 'Feminino', 1.70, '2018-02-14', '2028-02-14', 200004, '925000004', 4, 4, 4),
(5, 'José Alberto', 'Alberto Pedro', 'Clara José', '200000005LA045', 'Zona Administrativa', '1986-06-06', 'Masculino', 1.75, '2019-08-22', '2029-08-22', 200005, '925000005', 5, 5, 5);

INSERT INTO testemunha
(id_testemunha, nome_testemunha, pai_testemunha, mae_testemunha, bi_testemunha, residencia_testemunha, data_nascimento_testemunha, sexo_testemunha, proximidade_testemunha, estado_civil_testemunha, data_emissao_bi_testemunha, data_validade_bi_testemunha, telefone_testemunha, id_municipio, id_profissao)
VALUES
(1, 'Daniel Correia', 'Correia Paulo', 'Luísa Correia', '300000001LA041', 'Bairro Popular', '1993-04-17', 'Masculino', 'Farmácia Central', 'Solteiro', '2017-01-15', '2027-01-15', '926000001', 1, 1),
(2, 'Beatriz Figueiredo', 'Figueiredo João', 'Amélia Figueiredo', '300000002LA042', 'Rua Principal', '1996-08-11', 'Feminino', 'Escola Central', 'Casado', '2018-09-20', '2028-09-20', '926000002', 2, 2),
(3, 'Luís Bernardo', 'Bernardo Mateus', 'Rita Bernardo', '300000003LA043', 'Bairro Novo', '1988-02-28', 'Masculino', 'Hospital Municipal', 'Solteiro', '2016-06-25', '2026-06-25', '926000003', 3, 3),
(4, 'Patrícia Gomes', 'Gomes André', 'Helena Gomes', '300000004LA044', 'Zona Comercial', '1991-12-05', 'Feminino', 'Banco Local', 'Divorciado', '2019-03-14', '2029-03-14', '926000004', 4, 4),
(5, 'Manuel Teixeira', 'Teixeira Carlos', 'Sofia Teixeira', '300000005LA045', 'Bairro da Paz', '1985-10-19', 'Masculino', 'Terminal Rodoviário', 'Casado', '2015-05-09', '2025-05-09', '926000005', 5, 5);

INSERT INTO ocorrencia
(id_ocorrencia, data_ocorrencia, hora_ocorrencia, descricao_ocorrencia, rua_ocorrencia, cidade_ocorrencia, bairro_ocorrencia, proximidade_ocorrencia, id_autuado, id_autuante, id_tipo_ocorrencia, id_testemunha, id_testemunha1, id_municipio)
VALUES
(1, '2024-01-12', '09:30', 'Furto de telemóvel em via pública', 'Rua Principal', 'Luanda', 'Bairro Popular', 'Mercado Municipal', 1, 1, 2, 1, 2, 1),
(2, '2024-02-18', '14:15', 'Discussão com agressão física leve', 'Rua das Acácias', 'Lubango', 'Bairro Azul', 'Escola Central', 2, 2, 3, 2, 3, 2),
(3, '2024-03-07', '20:45', 'Dano material em estabelecimento comercial', 'Avenida Central', 'Benguela', 'Bairro Novo', 'Hospital Municipal', 3, 3, 4, 3, 4, 3),
(4, '2024-04-22', '18:10', 'Perturbação pública durante evento local', 'Rua do Comércio', 'Huambo', 'Zona Comercial', 'Banco Local', 4, 4, 5, 4, 5, 4),
(5, '2024-05-05', '23:00', 'Roubo de carteira próximo ao terminal', 'Rua da Estação', 'Malanje', 'Bairro da Paz', 'Terminal Rodoviário', 5, 5, 1, 5, 1, 5);