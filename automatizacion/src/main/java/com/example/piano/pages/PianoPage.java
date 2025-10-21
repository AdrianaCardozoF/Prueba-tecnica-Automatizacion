package com.example.piano.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.*;
import java.util.NoSuchElementException;

/**
 * Modelado de la pagina web.
 * Selecciona la tecla por texto de la nota (por ejemplo: "do", "re", "mi").
 * Soporta notas repetidas.
 */
public class PianoPage {

    private WebDriver driver;
    private String url = "https://www.musicca.com/es/piano";

    public PianoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get(url);
    }

    /**
     * Hace clic en una secuencia de notas y devuelve el resultado de cada nota tecleada.
     */
    public Map<String, Boolean> playSequence(List<String> notas) {
        Map<String, Boolean> resultado = new LinkedHashMap<>();
        Map<String, Integer> ocurrencias = new HashMap<>();

        for (String nota : notas) {
            String clave = nota.toLowerCase();
            int idx = ocurrencias.getOrDefault(clave, 0);
            boolean tocada = pressKey(nota, idx);
            resultado.put(nota, tocada);
            ocurrencias.put(clave, idx + 1);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return resultado;
    }

    /**
     * Repite una secuencia N veces.
     */
    public Map<String, Boolean> playSequenceRepeated(List<String> notas, int veces) {
        Map<String, Boolean> resultado = new LinkedHashMap<>();
        for (int i = 0; i < veces; i++) {
            System.out.println("Repetición #" + (i + 1));
            resultado.putAll(playSequence(notas));
        }
        return resultado;
    }

    /**
     * Busca la tecla por texto visible de la nota y hace clic.
     */
    private boolean pressKey(String nota, int ocurrencia) {
        try {
            String notaMinuscula = nota.toLowerCase();
            // Busca todas las teclas de la nota
            List<WebElement> teclas = driver.findElements(
                By.xpath("//ul[@class='piano--white-keys']//span[contains(@class,'white-key')]//span[@class='note' and text()='" + notaMinuscula + "']")
            );

        if (teclas.isEmpty()) {
            System.out.println("No se encontró la tecla para la nota: " + nota);
            return false;
        }

        // Si hay más de una tecla de la misma nota, elegimos según la ocurrencia en la secuencia
        int index = Math.min(ocurrencia, teclas.size() - 1);

        WebElement tecla = teclas.get(index).findElement(By.xpath("./ancestor::span[contains(@class,'white-key')]"));
        new Actions(driver).moveToElement(tecla).click().perform();

        System.out.println("🎵 Tocando nota: " + nota + " (ocurrencia " + index + ")");
        return true;

        } catch (NoSuchElementException e) {
            System.out.println("Error al tocar la nota: " + nota + " -> " + e.getMessage());
            return false;
        }
    }
}