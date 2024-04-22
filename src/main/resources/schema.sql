CREATE TABLE IF NOT EXISTS member (
    id                BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login_id          VARCHAR(20)     NOT NULL,
    password          VARCHAR(100)    NOT NULL,
    nickname          VARCHAR(20)     NOT NULL,
    name              VARCHAR(20)     NOT NULL,
    phone_number      VARCHAR(20)     NOT NULL,
    email             VARCHAR(320)    NOT NULL,
    created_time      TIMESTAMP       NOT NULL
);
