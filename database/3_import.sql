INSERT INTO public.role (id, name) VALUES (DEFAULT, 'admin');
INSERT INTO public.role (id, name) VALUES (DEFAULT, 'customer');

INSERT INTO public."user" (id, role_id, username, password, status) VALUES (DEFAULT, 1, 'admin', '123', 'A');
INSERT INTO public."user" (id, role_id, username, password, status) VALUES (DEFAULT, 2, 'rain', '123', 'A');
INSERT INTO public."user" (id, role_id, username, password, status) VALUES (DEFAULT, 2, 'mitteaktiivne', '123', 'D');

INSERT INTO public.city (id, name) VALUES (DEFAULT, 'Pärnu');
INSERT INTO public.city (id, name) VALUES (DEFAULT, 'Tallinn');
INSERT INTO public.city (id, name) VALUES (DEFAULT, 'Tartu');


INSERT INTO public.location (id, city_id, name, number_of_atms, image_id, status) VALUES (DEFAULT, 2, 'Sikupilli Supermarket', 5, null, 'A');
INSERT INTO public.location (id, city_id, name, number_of_atms, image_id, status) VALUES (DEFAULT, 3, 'Tondi Supermarket', 2, null, 'A');

INSERT INTO public.transaction_type (id, name) VALUES (DEFAULT, 'raha sisse');
INSERT INTO public.transaction_type (id, name) VALUES (DEFAULT, 'raha välja');
INSERT INTO public.transaction_type (id, name) VALUES (DEFAULT, 'maksed');