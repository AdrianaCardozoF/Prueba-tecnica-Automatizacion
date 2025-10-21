package com.example.piano.tests;

import com.example.piano.pages.PianoPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import io.qameta.allure.*;


/**
Ejecuta los escenarios de prueba 1,2 y 3
Aplicando patron de diseño POM
*/

public class PianoScenariosTest extends BaseTest {

private PianoPage piano;

@BeforeEach
public void setUp() {
    initDriver();
    piano = new PianoPage(driver);
    piano.goTo();
}

private List notas(String... arr) {
    return Arrays.stream(arr)
            .map(String::toLowerCase)
            .collect(Collectors.toList());
}

@Test
public void escenario1_himnoDeLaAlegria() {
    System.out.println("Escenario 1: Himno de la Alegría");
    List secuencia = notas(
            "si", "si", "do", "re", "re", "do", "si", "la",
            "sol", "sol", "la", "si", "si", "la", "la"
    );

    Map<String, Boolean> resultado = piano.playSequence(secuencia);
    long tocadas = resultado.values().stream().filter(r -> r).count();
    double porcentaje = (double) tocadas / resultado.size();

    System.out.println("Notas tocadas correctamente: " + tocadas + "/" + resultado.size());
    Assertions.assertTrue(porcentaje >= 0.8,
         "Se detectaron menos del 80% de notas tocadas correctamente.");

    Allure.step("Verificar porcentaje de notas tocadas correctamente", () -> {
        Allure.attachment("Resultado", "Notas tocadas correctamente: " + tocadas + "/" + resultado.size());
        Allure.attachment("Porcentaje", "Porcentaje de notas tocadas correctamente: " + porcentaje);
    });
}

@Test
public void escenario2_repetirDosVeces() {
    System.out.println("Escenario 2: Repetir la secuencia dos veces");
    List secuencia = notas(
            "si", "si", "do", "re", "re", "do", "si", "la",
            "sol", "sol", "la", "si", "si", "la", "la"
    );

    Map<String, Boolean> resultado = piano.playSequenceRepeated(secuencia, 2);
    long tocadas = resultado.values().stream().filter(r -> r).count();
    double porcentaje = (double) tocadas / resultado.size();

    System.out.println("Notas tocadas correctamente: " + tocadas + "/" + resultado.size());
    Assertions.assertTrue(porcentaje >= 0.8,
         "Se detectaron menos del 80% de notas tocadas correctamente tras repetir 2 veces.");

    Allure.step("Verificar porcentaje de notas tocadas correctamente", () -> {
        Allure.attachment("Resultado", "Notas tocadas correctamente: " + tocadas + "/" + resultado.size());
        Allure.attachment("Porcentaje", "Porcentaje de notas tocadas correctamente: " + porcentaje);
    });
}

@Test
public void escenario3_secuenciaExtendida() {
    System.out.println("Escenario 3: Secuencia extendida");
    List secuencia = notas(
            "si", "si", "do", "re", "re", "do", "si", "la",
            "sol", "sol", "la", "si", "la", "sol", "sol", "la",
            "si", "sol", "la", "si", "do", "si", "sol", "la",
            "si", "do", "si", "sol", "sol", "fa", "re"
);

 // Al final se repite el escenario 1
    secuencia.addAll(notas(
         "si", "si", "do", "re", "re", "do", "si", "la",
         "sol", "sol", "la", "si", "si", "la", "la"
    ));

    Map<String, Boolean> resultado = piano.playSequence(secuencia);
    long tocadas = resultado.values().stream().filter(r -> r).count();
    double porcentaje = (double) tocadas / resultado.size();

    System.out.println("Notas tocadas correctamente: " + tocadas + "/" + resultado.size());
    Assertions.assertTrue(porcentaje >= 0.75,
         "Se detectaron menos del 75% de notas correctamente en la secuencia extendida.");

       Allure.step("Verificar porcentaje de notas tocadas correctamente", () -> {
           Allure.attachment("Resultado", "Notas tocadas correctamente: " + tocadas + "/" + resultado.size());
           Allure.attachment("Porcentaje", "Porcentaje de notas tocadas correctamente: " + porcentaje);
       });
    }
}