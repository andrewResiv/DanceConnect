-- Удаляем старое поле role, если оно существовало
ALTER TABLE users DROP COLUMN IF EXISTS role;

-- Создаем таблицу roles
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

-- Создаем промежуточную таблицу user_roles
CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);
-- Вставляем базовые роли пользователей
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN') ON CONFLICT (name) DO NOTHING; -- Чтобы не дублировалось при повторном запуске
