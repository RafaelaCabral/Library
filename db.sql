CREATE TABLE client (
    client_id CHAR(26) PRIMARY KEY,
    client_name VARCHAR(100) NOT NULL,
    client_email VARCHAR(100) NOT NULL UNIQUE,
    client_phone VARCHAR(14),
    client_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE publisher (
    publisher_id SERIAL PRIMARY KEY,
    publisher_name VARCHAR(255) NOT NULL,
    publisher_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    publisher_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE language (
    language_id SERIAL PRIMARY KEY,
    language_name VARCHAR(50) NOT NULL UNIQUE,
    language_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    language_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE catalog (
    catalog_id CHAR(13) PRIMARY KEY,
    catalog_title VARCHAR(255) NOT NULL,
    catalog_author VARCHAR(100) NOT NULL,
    catalog_year INT NOT NULL,
    catalog_version INT NOT NULL,
    publisher_id INT NOT NULL,
    catalog_pages INT NOT NULL,
    catalog_synopsis VARCHAR(600),
    catalog_is_foreign BOOLEAN NOT NULL DEFAULT false,
    language_id INT NOT NULL,
    catalog_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    catalog_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (publisher_id) REFERENCES publisher(publisher_id),
    FOREIGN KEY (language_id) REFERENCES language(language_id)
);

CREATE TABLE genrer (
    genrer_id SERIAL PRIMARY KEY,
    gendrer_value VARCHAR(50) NOT NULL UNIQUE,
    genrer_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    genrer_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE catalogGenrer (
    catalog_id CHAR(13) NOT NULL,
    genrer_id INT NOT NULL,
    catalog_genrer_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    catalog_genrer_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (catalog_id, genrer_id),
    FOREIGN KEY (catalog_id) REFERENCES catalog(catalog_id),
    FOREIGN KEY (genrer_id) REFERENCES genrer(genrer_id)
);

CREATE TABLE inventory (
    inventory_id CHAR(26) PRIMARY KEY,
    inventory_condition INT NOT NULL DEFAULT 100,
    inventory_is_available BOOLEAN NOT NULL DEFAULT true,
    inventory_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    inventory_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    catalog_id CHAR(13) NOT NULL,
    FOREIGN KEY (catalog_id) REFERENCES catalog(catalog_id)
);

CREATE TABLE loan (
    loan_id CHAR(26) PRIMARY KEY,
    loan_days_to_expire INT NOT NULL DEFAULT 30,
    loan_returned_at TIMESTAMP DEFAULT NULL,
    loan_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    loan_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_id CHAR(26) NOT NULL,
    inventory_id CHAR(26) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(client_id),
    FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id)
);
