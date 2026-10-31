-- ForoConnect - Datos de prueba para Sumativa 1
-- Ejecutar DESPUES de foroconnect_estructura_limpia.sql en un esquema VACIO.
-- NO ejecutar sobre el Oracle actual: IDs y correos ya existen.
-- Datos recreados de las pruebas realizadas; se omite la publicacion temporal ID 24.
-- IMPORTANTE: las contrasenas originales NO se exportaron.
-- Estas son cuentas DEMO: las tres tienen clave ForoDemo2026! (hash BCrypt).
-- Cambiar las claves si el entorno deja de ser local/de evaluacion.

INSERT INTO USUARIOS (ID, NOMBRE, CORREO, ROL, CONTRASENA) VALUES (1, 'Kari Actualizada', 'kari@foroconnect.cl', 'USUARIO', '$2y$10$DVemKjFwhyzLysqsj5cEL.phjjG8OQ8gbHjsKPz8FvFtIRDDGNWDS');
INSERT INTO USUARIOS (ID, NOMBRE, CORREO, ROL, CONTRASENA) VALUES (23, 'Administrador Foro', 'admin@foroconnect.cl', 'ADMIN', '$2y$10$446QueeDxGNy0.mJfCHklu89RoRN7dZIkv4mZ6/tYAWeEN.UYWii.');
INSERT INTO USUARIOS (ID, NOMBRE, CORREO, ROL, CONTRASENA) VALUES (41, 'Usuario Invitado', 'invitado@foroconnect.cl', 'USUARIO', '$2y$10$OV1I9S9Zr5uP4va/sPPtge6KIjav5Gnh6jNYEouPAmTjjCLaWKUkK');

INSERT INTO CATEGORIAS (ID, NOMBRE, DESCRIPCION) VALUES (1, 'Tecnología', 'Espacio para conversar sobre programación, computación e innovación.');
INSERT INTO CATEGORIAS (ID, NOMBRE, DESCRIPCION) VALUES (2, 'Videojuegos', 'Espacio para conversar sobre videojuegos, consolas y experiencias gaming.');
INSERT INTO CATEGORIAS (ID, NOMBRE, DESCRIPCION) VALUES (3, 'Música', 'Espacio para compartir artistas, canciones y conciertos.');
INSERT INTO CATEGORIAS (ID, NOMBRE, DESCRIPCION) VALUES (4, 'Deportes', 'Espacio para conversar sobre deportes, entrenamiento y actividad física.');
INSERT INTO CATEGORIAS (ID, NOMBRE, DESCRIPCION) VALUES (5, 'Viajes', 'Espacio para compartir destinos, experiencias y consejos de viaje.');

INSERT INTO PUBLICACIONES (ID, TITULO, CONTENIDO, USUARIO_ID, CATEGORIA_ID, FECHA_CREACION) VALUES (1, '¿Qué esperan del próximo GTA?', 'Hablemos sobre el próximo GTA. ¿Qué novedades, misiones y actividades les gustaría encontrar?', 1, 2, TO_TIMESTAMP('2026-10-31 14:46:00.1090513', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO PUBLICACIONES (ID, TITULO, CONTENIDO, USUARIO_ID, CATEGORIA_ID, FECHA_CREACION) VALUES (22, '¿Qué lenguaje de programación recomiendan aprender?', 'Hola a todos. Estoy aprendiendo desarrollo de software y me gustaría saber qué lenguajes recomiendan para comenzar a trabajar como desarrollador.', 1, 1, TO_TIMESTAMP('2026-10-31 15:05:48.9537194', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO PUBLICACIONES (ID, TITULO, CONTENIDO, USUARIO_ID, CATEGORIA_ID, FECHA_CREACION) VALUES (23, '¿Qué lugares recomiendan visitar en la Patagonia?', 'Estoy organizando un viaje por el sur de Chile y me gustaría conocer sus recomendaciones de lugares, parques nacionales y actividades al aire libre.', 23, 5, TO_TIMESTAMP('2026-10-31 15:06:16.2611644', 'YYYY-MM-DD HH24:MI:SS.FF'));

INSERT INTO COMENTARIOS (ID, CONTENIDO, USUARIO_ID, PUBLICACION_ID, FECHA_CREACION) VALUES (1, '¡Estoy muy emocionada por el nuevo GTA! Espero que tenga un mapa gigante, muchas misiones y nuevas actividades.', 23, 1, TO_TIMESTAMP('2026-10-31 14:56:48', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO COMENTARIOS (ID, CONTENIDO, USUARIO_ID, PUBLICACION_ID, FECHA_CREACION) VALUES (3, 'Yo recomiendo comenzar con Java o Python. Ambos son excelentes para aprender programación y desarrollar aplicaciones.', 23, 22, TO_TIMESTAMP('2026-10-31 15:06:46.1674826', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO COMENTARIOS (ID, CONTENIDO, USUARIO_ID, PUBLICACION_ID, FECHA_CREACION) VALUES (4, '¡Te recomiendo visitar Torres del Paine, Puerto Natales y el Lago Grey! Los paisajes son increíbles y hay muchas rutas para recorrer.', 1, 23, TO_TIMESTAMP('2026-10-31 15:07:10.1759631', 'YYYY-MM-DD HH24:MI:SS.FF'));

COMMIT;

-- Comprobacion:
SELECT COUNT(*) AS TOTAL_USUARIOS FROM USUARIOS;
SELECT COUNT(*) AS TOTAL_CATEGORIAS FROM CATEGORIAS;
SELECT COUNT(*) AS TOTAL_PUBLICACIONES FROM PUBLICACIONES;
SELECT COUNT(*) AS TOTAL_COMENTARIOS FROM COMENTARIOS;
