#  Mobile Automation Final Practice

Automatización de 4 escenarios de navegación sobre la **WDIO Native Demo App**, usando **Appium + Java + TestNG**, siguiendo el patrón Page Object.

---

## La analogía: un robot repartidor en una tienda

Para entender la arquitectura, pensemos en un **robot repartidor** al que soltamos dentro de una tienda (la app) con una lista de misiones que cumplir.

- **`BaseTest` es la central de despacho.** Antes de cada misión, la central enciende un robot nuevo (`driver`) y le da una libreta en blanco para anotar cualquier problema que encuentre en el camino (`SoftAssert`). Al terminar la misión, pase lo que pase, la central revisa la libreta completa (`assertAll()`) y apaga al robot (`driver.quit()`). **Ningún robot se enciende solo** — siempre es la central quien lo hace, nunca la misión en sí.

- **`BaseScreen` son las manos y los ojos genéricos del robot.** El robot no tiene "memoria fotográfica" de la tienda — no guarda de antemano dónde está cada botón. En cambio, cada vez que necesita tocar algo, primero *mira* dónde está en ese instante (`getWebElement`), y recién ahí actúa: tocar (`click`), escribir (`sendKeys`), leer una etiqueta (`getText`), o esperar pacientemente a que algo aparezca sin quedarse pegado para siempre (`isElementDisplayed`, con espera explícita y sin `Thread.sleep`).

- **Cada `Screen` es el mapa de una sala de la tienda.** `HomeScreen` es el mapa del pasillo central con los carteles hacia cada sección. `LoginScreen` es el mapa del mostrador de registro. `SwipeScreen` es el mapa de la vitrina giratoria de tarjetas. Cada mapa conoce las coordenadas de **su propia sala** (los localizadores), pero usa las manos genéricas heredadas de `BaseScreen` para interactuar — nunca inventa su propia forma de tocar o esperar.

- **Cada `Test` es una misión con un supervisor.** El supervisor (la clase de test) es el único que tiene autoridad para decir "esto está bien" o "esto está mal" (los asserts) — el robot nunca opina por su cuenta, solo reporta lo que ve. Y cada misión es **independiente**: a cada robot se le da una misión completa desde cero, sin asumir que el robot anterior dejó algo preparado.

---

##  Estructura del proyecto

```
src/test/java/com/dmolano/mobile/
├── screens/
│   ├── BaseScreen.java      → manos y ojos genéricos del robot
│   ├── HomeScreen.java      → mapa de la bottom bar
│   ├── LoginScreen.java     → mapa de Login / Sign Up
│   ├── WebScreen.java       → mapa de la sección Web (WebView)
│   ├── FormsScreen.java     → mapa de la sección Forms
│   ├── SwipeScreen.java     → mapa de la vitrina de swipe
│   ├── DragScreen.java      → mapa del puzzle de Drag
│   └── MenuScreen.java      → mapa del menú lateral
├── tests/
│   ├── BaseTest.java                  → central de despacho
│   ├── BottomBarNavigationTest.java   → Escenario 1
│   ├── SignUpTest.java                → Escenario 2
│   ├── LoginTest.java                 → Escenario 3
│   └── SwipeTest.java                 → Escenario 4
└── utils/
    └── RandomDataGenerator.java       → genera credenciales únicas por corrida
```

---

## Escenarios automatizados

| # | Escenario | Qué verifica |
|---|-----------|---------------|
| 1 | **Navegación bottom bar** | Desde Home, navega las 7 secciones y verifica visibilidad + una propiedad (input habilitado en Forms) |
| 2 | **Sign Up exitoso** | Registra un usuario con email único (plus-addressing) y verifica el mensaje de éxito |
| 3 | **Login exitoso** | Crea su propio usuario (reusando el flujo de Sign Up) y verifica el login, sin depender de que `SignUpTest` se haya ejecutado antes |
| 4 | **Swipe de tarjetas** | Recorre el carrusel horizontal hasta la última tarjeta y hace swipe vertical hasta encontrar el mensaje oculto "You found me!!!" |

---

## ️ Stack

- **Appium Java Client** 10.1.0
- **Selenium** 4.49.0
- **TestNG** 7.10.2
- **Java** 17
- **Logback** (logging — nada de `System.out.println`)
- Emulador: Pixel 7 Pro, Android 14 (API 34)

---

##  Problemas encontrados durante el desarrollo

### El bug de la ventana invisible del emulador (Windows)
Al arrancar el emulador, la ventana simplemente no aparecía en pantalla, aunque el proceso corría normalmente (confirmado con `adb devices`). La causa: un problema de escalado de PPP (DPI) de Windows posicionaba la ventana fuera del área visible. Se resolvió invalidando el comportamiento de escalado de alto DPI en las propiedades de `qemu-system-x86_64.exe` (Compatibilidad → "Invalidar el comportamiento de ajuste con valores altos de PPP" → Sistema mejorado).

### Incompatibilidad de dependencias (Selenium / Appium Java Client)
La versión inicial de Appium Java Client (9.3.0) referenciaba clases de Selenium (`ContextAware`, `LocationContext`) que fueron eliminadas en versiones más recientes de Selenium, que Maven resolvía automáticamente. Se resolvió subiendo a Appium Java Client 10.1.0, que ya no depende de esas clases obsoletas.

### El Swipe: el reto más grande del proyecto
Esta fue, por lejos, la parte más compleja de automatizar:

1. **Los gestos W3C Actions "a mano" no funcionaban de forma confiable** sobre el carrusel (basado en componentes de React Native). El gesto se registraba, pero el componente no siempre reconocía el cruce del umbral necesario para cambiar de tarjeta.
2. **Efecto "rubber-banding":** en varios intentos, el carrusel arrastraba la tarjeta pero, al soltar el toque, la tarjeta rebotaba elásticamente de vuelta a su posición original en lugar de avanzar — un comportamiento inconsistente de la propia app, no del código de automatización.
3. **Virtualización de la lista:** el carrusel recicla las vistas internamente (los índices `__CAROUSEL_ITEM_N__` no corresponden de forma estable a una tarjeta física), lo que hacía frágil cualquier estrategia basada en rastrear una tarjeta por índice o por coordenadas exactas.

**Solución final:** se migró de W3C Actions a comandos nativos de Appium (`mobile: swipeGesture`, más confiables sobre gestos táctiles de Android), y se rediseñó la validación para dejar de perseguir índices o coordenadas exactas de tarjetas individuales. En cambio, el test repite el swipe en un bucle con espera explícita (sin `Thread.sleep`), verificando en cada vuelta si el contenido distintivo de la última tarjeta ("COMPATIBLE") ya es completamente visible en pantalla — un criterio simple, determinístico y resistente al comportamiento inconsistente propio de la app.

---

## Cómo correr los tests

1. Emulador Android corriendo (`emulator -avd Pixel_7_Pro`)
2. Appium Server corriendo en paralelo (`appium`)
3. Correr cualquier clase de test desde IntelliJ (botón ▶) o con Maven: `mvn test`