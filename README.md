# MELI- search App
Aplicacion navita Android para buscar productos de la API de Mercado Libre. Cuenta con tres pantallas, una de busqueda, otra de resultado y el detaller del producto seleccionado.

El repo cuenta con 3 ramas, main, mainCompose, mainXml. **mainXml** es el mismo proyecto, pero con el sistema tradicional de vistas y compose. **mainCompose** es el mismo proyecto pero solo con compose. **main** es una rama sincronizada con mainCompose.

## Tecnologías
- Kotlin
- Koin
- Corrutinas
- Flows
- Jetpack compose
- Xml
- Retrofit-Gson
- Epoxy
- Junit
- mockk
- turbine
## Arquitectura
Se usa la Arquitectura **MVVM** , se usa una pequena variante que prioriza la modularizacion por feature.

![image](https://user-images.githubusercontent.com/86072587/225817080-03a37836-7eaa-4e41-838d-613232c4b69b.png)


## Testing

La App cuenta con test unitarios de los repositorios, UseCases y demas clases. 
