INSERT INTO "PUBLIC"."PELICULA_CATALOGO" VALUES

(1, 'espanol', 'muy buena', 'poster', 'fecha', 'titulo', '3/5'),

(2, 'espanol', 'muy buena', 'poster', 'fecha', 'titulo', '3/5'),

(3, 'espanol3', 'muy buena 3', 'poster3', 'fecha3', 'titulo3', '3/5 3'),

(4, 'espanol4', 'muy buena 4', 'poster4', 'fecha4', 'titulo4', '3/5 4');

INSERT INTO "PUBLIC"."USUARIO" VALUES

('rodomail', 0, 'rodo', 'pass', 'ADMIN'),

('rodomail2', 0, 'rodo2', 'pass2', 'USER');

INSERT INTO "PUBLIC"."PELICULA_ALQUILADA" VALUES

(1, 2, 'rodomail2');