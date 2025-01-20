-- V2__add_partner_id_to_dance_partner_requests.sql

-- Добавляем колонку partner_id
ALTER TABLE dance_partner_requests
    ADD COLUMN partner_id INT;

-- Устанавливаем внешнее ключевое ограничение
ALTER TABLE dance_partner_requests
    ADD CONSTRAINT fk_partner
        FOREIGN KEY (partner_id)
            REFERENCES users (id)
            ON DELETE SET NULL;