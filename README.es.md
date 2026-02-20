# Android Simpsons App 🍩

[English Version](README.md)

Este es un proyecto de Android moderno desarrollado con **Jetpack Compose** para explorar y buscar personajes de la serie "Los Simpsons" consumiendo la API de [thesimpsonsapi.com](https://thesimpsonsapi.com/).

## 🚀 Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal.
- **Jetpack Compose**: Framework moderno para la creación de interfaces de usuario nativas.
- **Material 3**: Última evolución de Material Design para componentes UI.
- **MVVM + Clean Architecture**: Arquitectura robusta y escalable dividida en capas (Presentation, Domain, Data, Core).
- **Manual Dependency Injection**: Implementación de inyección de dependencias sin frameworks externos (Hilt/Koin) para mantener el proyecto ligero.
- **Retrofit**: Consumo de la API REST oficial de Los Simpsons.
- **Coil**: Carga eficiente de imágenes desde URLs remotas.
- **Navigation Compose**: Gestión de rutas y navegación entre pantallas.
- **[Stitch](https://stitch.withgoogle.com/)**: Herramienta utilizada para definir y trasladar la capa visual del diseño al código.

## 📱 Pantallas y Funcionalidades

### 1. Pantalla Principal (Listado Paginado)
- Muestra una lista de personajes cargados dinámicamente desde la API.
- Incluye un sistema de paginación real para navegar entre los cientos de personajes disponibles.
- **SimpsonHeader**: Un encabezado personalizado con el estilo visual icónico de la serie.

### 2. Buscador Híbrido Inteligente
- **Búsqueda por ID**: Si introduces un número, la app consulta directamente la API para traer ese personaje específico.
- **Búsqueda por Nombre**: Si introduces texto, la app filtra en tiempo real sobre la lista cargada localmente.
- Estilo visual de "píldora" redondeada integrada en el encabezado.

### 3. Pantalla de Detalle
- Al pulsar sobre cualquier personaje, se navega a una vista detallada.
- Muestra imagen en alta resolución, ocupación, edad y género.
- **Listado de Frases**: Incluye una sección con todas las frases célebres del personaje en tarjetas individuales.

## 🛠️ Arquitectura del Proyecto

El proyecto sigue estrictamente el archivo `ARCHITECTURE.md`:
- **Data Layer**: Repositorios y fuentes de datos (Retrofit). Mapeo de DTOs a modelos de dominio.
- **Domain Layer**: Modelos de negocio puros y Casos de Uso (UseCases) con responsabilidad única.
- **Presentation Layer**: ViewModels que gestionan el estado de la UI de forma inmutable mediante `StateFlow` y pantallas en Compose.
- **Core Layer**: Utilidades de red, manejo de errores controlado (`AppError`) y validación de conectividad.

---
Desarrollado como una aplicación MVP de alta fidelidad visual y técnica.
