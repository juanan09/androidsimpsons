# Android Simpsons App 🍩

Este es un proyecto de Android moderno desarrollado con **Jetpack Compose** para explorar y buscar personajes de la serie "Los Simpsons" consumiendo la API de [thesimpsonsapi.com](https://thesimpsonsapi.com/).

## 🚀 Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal.
- **Jetpack Compose**: Framework moderno para la creación de interfaces de usuario nativas.
- **Material 3**: Última evolución de Material Design para componentes UI.
- **State Hoisting**: Patrón de diseño para el manejo eficiente del estado en Compose.
- **[Stitch](https://stitch.withgoogle.com/)**: Herramienta utilizada para definir y trasladar la capa visual del diseño al código de forma eficiente.
- **Retrofit / Coil** (Próximamente): Para el consumo de la API REST y carga de imágenes.

## 🎨 Diseño y Capa Visual (Stitch)

Para asegurar la fidelidad visual y una integración fluida entre el diseño y el código,  utilizando **Stitch by Google**. 

**¿Qué es Stitch?**
Es una plataforma que facilita la transición del diseño a la implementación técnica. Permite:
- Mantener una consistencia visual rigurosa (colores, tipografías, espaciados).
- Generar tokens y referencias visuales que se traducen directamente en componentes de Jetpack Compose.
- Agilizar el flujo de trabajo entre la concepción visual y el desarrollo nativo.

## 🛠️ Componentes Destacados

### Searcher Composable
Un buscador personalizado con los colores temáticos de la serie, basado en las guías visuales obtenidas de Stitch, que permite filtrar contenido en tiempo real mediante elevación de estado.

## 📌 Próximos Pasos

1. Configuración de **Retrofit** para conectar con `thesimpsonsapi.com`.
2. Implementación de una lista (LazyColumn) para mostrar los personajes.
3. Detalle de personaje al hacer clic.
4. Manejo de imágenes con **Coil**.

---
Desarrollado como práctica de integración de APIs y UI moderna en Android.
