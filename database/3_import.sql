INSERT INTO public.role (id, name) VALUES (default, 'admin');
INSERT INTO public.role (id, name) VALUES (default, 'customer');


INSERT INTO public.city (id, name) VALUES (default, 'Tartu');
INSERT INTO public.city (id, name) VALUES (default, 'Tallinn');
INSERT INTO public.city (id, name) VALUES (default, 'Pärnu');


INSERT INTO public.location (id, city_id, name, number_of_atms, status) VALUES (default, 2, 'Sikupilli Prisma', 5, 'A');
INSERT INTO public.location (id, city_id, name, number_of_atms, status) VALUES (default, 2, 'Tondi Selver', 3, 'A');
INSERT INTO public.location (id, city_id, name, number_of_atms, status) VALUES (default, 1, 'Sõbra Prisma', 2, 'A');
