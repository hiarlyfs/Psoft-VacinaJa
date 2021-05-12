INSERT INTO tipo_login (id, tipo_login) VALUES (1, 'ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO tipo_login (id, tipo_login) VALUES (2, 'CIDADAO') ON CONFLICT (id) DO NOTHING;
INSERT INTO tipo_login (id, tipo_login) VALUES (3, 'FUNCIONARIO') ON CONFLICT (id) DO NOTHING;
INSERT INTO status_cadastro (id, status) VALUES (1, 'PENDENTE') ON CONFLICT (id) DO NOTHING;
INSERT INTO status_cadastro (id, status) VALUES (2, 'CONFIRMADO') ON CONFLICT (id) DO NOTHING;
INSERT INTO login (id, login, password, tipo_login_id) VALUES (1, 'admin', 'admin', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO email_template (id, name, path) VALUES (1, 'default', 'templates/default_email.html') ON CONFLICT (id) DO NOTHING;
INSERT INTO email_template (id, name, path) VALUES (2, 'habilitado', 'templates/habilitado_email.html') ON CONFLICT (id) DO NOTHING;