# Proyecto de Automatización - Piano Virtual

Este proyecto automatiza la interacción con una aplicación web de piano virtual.
El propósito es simular la ejecución de secuencias de notas musicales mediante la automatización de pruebas con Selenium WebDriver y JUnit.

## Tecnologías utilizadas
- Java 17 o superior
- Selenium WebDriver
- JUnit 5
- Maven
- ExtentReports (para reportes de ejecución)
- Page Object Model (POM) como patrón de diseño

## Estructura del proyecto
 piano-automation
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┗ java
 ┃ ┃   ┣ DriverFactory.java
 ┃ ┃   ┣ PianoPage.java
 ┃ ┃   ┗ Note.java
 ┃ ┗ test
 ┃   ┗ java
 ┃     ┣ BaseTest.java
 ┃     ┗ PianoScenariosTest.java
 ┣ pom.xml
 ┣ README.md
 

## Descripción de las clases principales
DriverFactory.java
Se encarga de la configuración y gestión del ciclo de vida del WebDriver.

Inicializa el navegador (por defecto Chrome).
Aplica configuraciones básicas como maximizar la ventana y definir tiempos de espera.
Permite la reutilización del mismo driver durante la ejecución de los tests.

Note.java
Representa un objeto de tipo Nota musical, asociando su nombre (“do”, “re”, “mi”…) con su data-note del HTML.
Facilita la ejecución de secuencias de notas en orden, mejorando la legibilidad y mantenimiento del código.

PianoPage.java
Contiene los localizadores y métodos que permiten interactuar con el piano.
Utiliza el Page Object Model (POM), separando la lógica de interacción del código de prueba.

Funciones principales:
- Encontrar teclas por nombre o data-note.
- Reproducir una secuencia de notas.
- Validar la presencia de elementos en la interfaz.

BaseTest.java
Clase base para los tests.
Configura el entorno antes y después de cada prueba (@BeforeAll, @AfterAll).
Inicia el navegador y carga la URL base del piano.
Genera los reportes de ejecución con ExtentReports.

PianoScenariosTest.java
Contiene los escenarios de prueba definidos en JUnit.
Ejemplo de escenario: “Reproducir la secuencia DO, RE, MI, FA”.
Incluye validaciones y reporta los resultados de la ejecución en un archivo HTML dentro de reports/.

## Instalación y configuración
1️ Clonar el repositorio
git clone https://github.com/usuario/piano-automation.git
cd piano-automation

2️ Instalar dependencias
mvn clean install

3️ Configurar el navegador
Por defecto, el proyecto usa Google Chrome.
Asegúrate de tener instalado ChromeDriver compatible con tu versión de navegador.

## Ejecución de pruebas

Puedes ejecutar las pruebas de dos maneras:

Desde línea de comandos:
mvn test

Desde un IDE (IntelliJ, Eclipse, VSCode):

Ejecuta directamente la clase PianoScenariosTest.java como JUnit Test.

## Reporte de ejecución
Debes instalar Allure:
Ejecutar el comando Get-ExecutionPolicy en powershell, posteriormente ejecutar el comando Set-ExecutionPolicy RemoteSigned, instalar scoop con el comando  iwr -useb get.scoop.sh | iex  y finalmente ejecutar el comando scoop install allure

Después de ejecutar las pruebas, ejecutar el comando mvn -q allure:report y finalmente ejecutar el comando mvn allure:serve. Esto te abrira un navegador con el reporte de ejecución.

## Autor
