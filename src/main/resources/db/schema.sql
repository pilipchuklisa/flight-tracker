CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    verification_code VARCHAR(255),
    verification_expiration TIMESTAMP,
    role VARCHAR(50) NOT NULL,
    enable BOOLEAN NOT NULL
);

CREATE TABLE airport_time_zones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    iata_code VARCHAR(10) UNIQUE NOT NULL,
    timezone VARCHAR(255) NOT NULL
);
