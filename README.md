# TaskSphere - Proyecto Integrador (SC-405)

TaskSphere es un proyecto escolar de escritorio en Java Swing usado como base del **Proyecto Integrador de Calidad del Software**.
Este repositorio esta enfocado en la fase de pruebas (diseno, ejecucion y evidencia de casos de prueba).

## Enfoque de calidad

- Curso: **SC-405 Calidad del Software**.
- Modalidad: trabajo grupal por entregables (anteproyecto, planificacion, ejecucion, cierre/defensa).
- Cobertura esperada: **minimo 10 casos de prueba** sobre requerimientos funcionales.
- Las pruebas combinan validacion funcional, UI y verificaciones sobre base de datos.

## Requisitos para ejecutar pruebas

- Java (compilacion manual con `javac`).
- MySQL local con esquema `tasksphere` cargado desde `tasksphere.sql`.
- Conexion actual hardcodeada en `src/Modelo/Conexion.java`:
  - URL: `jdbc:mysql://localhost:3306/tasksphere`
  - Usuario: `root`
  - Password: vacio


## Notas importantes de pruebas

- Frameworks: **JUnit 4** + **AssertJ Swing**.
- Varias pruebas en `test/Vista` son UI-driven y requieren entorno con escritorio (no headless).
- Algunas pruebas alteran datos (por ejemplo tareas/usuarios) y luego restauran en `tearDown`/`finally`; usar una BD local aislada.

## Estructura relevante

- `src/Vista`: formularios Swing
- `src/Controlador`: flujo de eventos
- `src/Modelo`: acceso a datos y consultas SQL
- `test/Vista` y `test/Modelo`: casos de prueba
