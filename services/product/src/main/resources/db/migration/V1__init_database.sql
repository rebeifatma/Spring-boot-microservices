-- Création de la séquence pour Category
CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 1 START WITH 1;

-- Création de la séquence pour Product
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 1 START WITH 1;

-- Création de la table Category
CREATE TABLE IF NOT EXISTS category (
                                        id INTEGER DEFAULT nextval('category_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
    );

-- Création de la table Product
CREATE TABLE IF NOT EXISTS product (
                                       id INTEGER DEFAULT nextval('product_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price INTEGER NOT NULL,
    available_quantity DOUBLE PRECISION NOT NULL,
    category_id INTEGER,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
    );
