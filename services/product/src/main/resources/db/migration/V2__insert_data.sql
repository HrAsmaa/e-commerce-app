-- Inserting a new category into the category table
INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Electronics and Gadgets', 'Electronics');

INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Furniture and Home Décor', 'Furniture');

INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Books and Stationery', 'Books');


-- Inserting products into the product table with category_id from category name
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES (
    nextval('product_seq'),
    'Latest model of smartphone with advanced features',
    'Smartphone',
    100,
    599.99,
    (SELECT id FROM category WHERE name = 'Electronics')
);

INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES (
    nextval('product_seq'),
    'Wooden 5-piece dining set',
    'Dining Set',
    50,
    299.99,
    (SELECT id FROM category WHERE name = 'Furniture')
);

INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES (
    nextval('product_seq'),
    'Collection of mystery novels by famous authors',
    'Mystery Book Set',
    200,
    29.99,
    (SELECT id FROM category WHERE name = 'Books')
);
