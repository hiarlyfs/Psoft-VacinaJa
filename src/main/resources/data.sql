INSERT INTO tipo_login (id, tipo_login) VALUES (1, 'ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO tipo_login (id, tipo_login) VALUES (2, 'CIDADAO') ON CONFLICT (id) DO NOTHING;
INSERT INTO tipo_login (id, tipo_login) VALUES (3, 'FUNCIONARIO') ON CONFLICT (id) DO NOTHING;