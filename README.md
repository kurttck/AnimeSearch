<div align="center">

# AnimeSearch

</div>

<p align="center">
   <img src="http://img.shields.io/static/v1?label=STATUS&message=Por%20Mejorar&color=LIGHTBLUE&style=for-the-badge" />
   <img src="https://img.shields.io/badge/language-Java-007396?style=for-the-badge"/>
</p>

¡Bienvenido/a a AnimeSearch! Este es un proyecto en Java desarrollado con Spring que te permite explorar y buscar información sobre animes mediante la API de [Jikan](https://jikan.moe/), una API no oficial de MyAnimeList.

## Funcionalidades

AnimeSearch ofrece varias funcionalidades para ayudarte a descubrir y conocer más sobre tus animes favoritos:

1. **Top 10 Animes**: Muestra el top 10 de animes en MyAnimeList.
2. **Animes de la Temporada Actual**: Presenta los animes de la temporada en curso.
3. **Búsqueda de Anime**: Permite buscar un anime específico y obtener información detallada, incluyendo el episodio mejor calificado.

## 🔧 Tecnologías Utilizadas

- **Lenguaje de programación**: Java ☕
- **Framework**: Spring Boot 🌱
- **Administración de dependencias**: Maven 🛠️
- **Jackson Databind**: Librería para procesar JSON y convertirlo en objetos Java.
- **Funciones Lambda y Streams**: Se implementan en el proyecto para procesar y filtrar datos de forma eficiente.

## Requisitos

- **Java 8 o superior**: AnimeSearch está desarrollado en Java, por lo que necesitas tener el JDK instalado.
- **Spring Boot**: Framework utilizado para la estructura del proyecto y manejo de dependencias.
- **Conexión a internet**: La aplicación usa la API de Jikan para obtener datos de MyAnimeList en tiempo real.

## Instalación y Ejecución

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/animesearch.git
2. **Navegar al directorio del proyecto:**
    ```bash
    cd animesearch
3. **Compilar y ejecutar:**
    ```bash
    mvn spring-boot:run

## 🏠 Estructura del Proyecto

AnimeSearch está organizado en tres paquetes principales y una clase raíz:

1. **Raíz**:
   - **AnimeSearchApplication**: Clase principal que llama al menú de opciones y administra el flujo de la aplicación.

2. **main**: Contiene la clase principal `Main`, que ejecuta el flujo de la aplicación.

3. **service**: 
   - **ApiConsume**: Clase encargada de realizar las solicitudes HTTP a la API de Jikan para obtener datos de animes.
   - **DataConvert**: Clase responsable de convertir los datos JSON obtenidos en objetos de Java mediante Jackson Databind.
   - **IDataConvert**: Interfaz que define los métodos de conversión para ser implementados en `DataConvert`.

4. **model**: Contiene las clases modelo en forma de *records* para estructurar los datos obtenidos de la API.
   - **Anime**: Representa los detalles básicos de un anime, como el título, el puntaje y el año.
   - **DataAnime**: Record que encapsula una lista de objetos `Anime` para el manejo de datos obtenidos.
   - **Episode**: Representa un episodio individual de un anime, incluyendo número, título y puntaje.
   - **DataEpisode**: Contiene una lista de `Episode` para el manejo de episodios de un anime específico.

## ✒️ Autor

[<img src="https://avatars.githubusercontent.com/u/82422415?v=4" width=115><br><sub>Kurt Angeles</sub>](https://github.com/kurttck)

## 😊 Contacto

* [LinkedIn](https://www.linkedin.com/in/kurt-angeles-segura/).
* [GitHub](https://github.com/kurttck).
