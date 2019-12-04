DROP TABLE IF EXISTS restaurant;

CREATE TABLE IF NOT EXISTS restaurant (

);

DESCRIBE restaurant;

insert into restaurant (name, thumbnail, picture_url, gate, airport, phone_number) values ('Auntie Annes Pretzels', 'https://cdn.auntieannes.com/-/media/auntie-annes/newsroom/aa_vert_rgb_aprilfools_smaller.png?v=1&d=20181114T183105Z', 'https://scontent-dfw5-1.xx.fbcdn.net/v/t1.0-9/74271020_10157588664372410_2290340857334726656_o.jpg?_nc_cat=109&_nc_oc=AQkigBDmVFEszrfS4dXFi0DLO512qrprflAAeza1-naZ1VCNo6zQRmLfpn4YcSoDAkg&_nc_ht=scontent-dfw5-1.xx&oh=070da33cee5d1a28c4e3c9ab895996f1&oe=5E82C549', 'A9', 'SAT', 2102653254);

insert into restaurant (name, thumbnail, picture_url, gate, airport, phone_number) values ('Blimpie Subs', 'https://www.blimpie.com/assets/images/logo.png', 'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fchristianclippers.com%2Fwp-content%2Fuploads%2F2012%2F03%2Fblimpie.jpg&f=1&nofb=1', 'A15', 'SAT', 2108221833);

insert into restaurant (name, thumbnail, picture_url, gate, airport, phone_number) values ('Famous Famiglia Pizzeria', 'Hassett', 'lhassett2@bbb.org', 'A9', 'SAT', 2109308844);



insert into cuisine (description) values ('Italian');

insert into cuisine (description) values ('Subs');

insert into cuisine (description) values ('Pretzels');


insert into menu_category (description) values ('Appetizer');
insert into menu_category (description) values ('Entree');
insert into menu_category (description) values ('Side');
insert into menu_category (description) values ('Dessert');
insert into menu_category (description) values ('Combos');


insert into menu_item (title, description, price, restaurant_id, menu_category_id) values ('Original Pretzel & Soda Combo', 'Every single pretzel is an original, and freshly baked to raise the standard of snacking. It’s the pretzel that started it all — made from five simple, fresh ingredients. Pick a pretzel up — it’s sure to return the favor!', '5.79', 1, 5);

insert into menu_item (title, description, price, restaurant_id, menu_category_id) values ('Combo2', 'Every single pretzel is an original, and freshly baked to raise the standard of snacking. It’s the pretzel that started it all — made from five simple, fresh ingredients. Pick a pretzel up — it’s sure to return the favor!', '5.79', 1, 5);


insert into menu_item (title, description, price, restaurant_id, menu_category_id) values ('BLIMPIE BEST', 'SLOW-CURED HAM, SALAMI, CAPICOLA, PROSCIUTTINI, PROVOLONE MADE THE BLIMPIE® WAY WITH TOMATOES, LETTUCE, ONION, VINEGAR, OIL AND OREGANO', '5.29', 2, 2);

insert into menu_item (title, description, price, restaurant_id, menu_category_id) values ('BLIMPIE BESTer', 'SLOW-CURED HAM, SALAMI, CAPICOLA, PROSCIUTTINI, PROVOLONE MADE THE BLIMPIE® WAY WITH TOMATOES, LETTUCE, ONION, VINEGAR, OIL AND OREGANO', '5.29', 2, 2);


insert into menu_item (title, description, price, restaurant_id, menu_category_id) values ('Sausage and Cheese Stromboli', 'A Stromboli with sausage and cheese', '4.95', 3, 2);

insert into menu_item (title, description, price, restaurant_id, menu_category_id) values ('Da Gabbagool', 'A Stromboli with sausage and cheese', '4.95', 3, 2);