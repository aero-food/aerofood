use aerofood_db;

insert into orders (delivery_time, gate, ordered_status, ordered_time, total, restaurant_id, user_id) VALUES ('12', 'A3', '1', '11', 12.00, 1, 1);

insert into order_details(description, dish_type, price, quantity, menu_item, orders) VALUES ('test', 'side', 2.00, 1, 1, 1);
insert into order_details(description, dish_type, price, quantity, menu_item, orders) VALUES ('test', 'side', 2.00, 1, 2, 1);