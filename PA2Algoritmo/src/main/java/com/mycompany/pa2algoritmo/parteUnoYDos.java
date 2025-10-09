/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pa2algoritmo;

/**
 *
 * @author Asus
 */
public class parteUnoYDos {

    // (1) Creación e inicialización de arreglos (Cristian Robles)
    static String[] pacientes = { "Ana", "Luis", "Marta", "Pedro", "Jose" };
    static String[] examenes = { "Glucosa", "Colesterol", "Presion", "Trigliceridos" };

    // matriz resultados[filaPaciente][colExamen]
    static int[][] resultados = {
            { 95, 180, 120, 140 }, // Ana
            { 110, 210, 135, 160 }, // Luis
            { 130, 250, 145, 190 }, // Marta
            { 100, 190, 125, 150 }, // Pedro
            { 90, 170, 118, 135 } // Jose
    };

    public static void main(String[] args) {
        mostrarTabla(); // ejecución de mostrarTabla
        mostrarReporteExamenes();
        mostrarDeteccionAnomalias();
    }

    // (2) Recorrido que muestra la matriz (Cristian Robles)
    static void mostrarTabla() {
        int wNombre = 12; // ancho para "Paciente"
        int wDato = 13; // ancho base para cada examen
        int gap = 2; // espacio extra entre columnas

        // Encabezados
        System.out.printf("%-" + wNombre + "s", "Paciente");
        for (String e : examenes)
            System.out.printf("%" + (wDato + gap) + "s", e);
        System.out.println();

        // Filas
        for (int i = 0; i < pacientes.length; i++) {
            System.out.printf("%-" + wNombre + "s", pacientes[i]);
            for (int j = 0; j < examenes.length; j++) {
                System.out.printf("%" + (wDato + gap) + "d", resultados[i][j]);
            }
            System.out.println();
        }
    }

    // (7) Reportes por cada examen (David Urbano)

    static void mostrarReporteExamenes() {
        System.out.println();
        System.out.println("=== REPORTES POR CADA EXAMEN ===");
        System.out.println("valor maximo, valor minimo y promedio");
        System.out.println("-------------------------------------");
        for (int i = 0; i < examenes.length; i++) { // recorrer examenes
            int max = 0; // valor inicial muy bajo
            int min = 999; // valor inicial muy alto
            int total = 0; // acumulador para promedio
            for (int j = 0; j < resultados.length; j++) { // recorrer pacientes
                if (resultados[j][i] > max) { // nuevo max?
                    max = resultados[j][i]; // actualizar max
                }
                if (resultados[j][i] < min) { // nuevo min?
                    min = resultados[j][i]; // actualizar min
                }
                total += resultados[j][i]; // acumular para promedio
            }
            System.out.println(examenes[i] + " -> Max: " + max + ", Min: " + min + ", Prom: "
                    + total / resultados.length);
        }
    }

    // (8) Detección de anomalías (David Urbano)
    static void mostrarDeteccionAnomalias() {
        System.out.println();
        System.out.println("=== DETECCION DE ANOMALIAS ===");
        int[] umbrales = { 126, 240, 140, 200 }; // umbrales para cada examen
        String[] nombresAnomalias = { "Diabetes", "Alto", "Hipertensión grado 2", "Alto" }; // nombres de anomalías

        for (int i = 0; i < pacientes.length; i++) { // recorrer pacientes
            for (int j = 0; j < examenes.length; j++) { // recorrer examenes
                if (resultados[i][j] > umbrales[j]) { // valor sobrepasa umbral?
                    System.out.println(
                            "Anomalia Detectada: " + pacientes[i] + " - " + examenes[j] + " = " + nombresAnomalias[j]
                                    + " (Valor: " + resultados[i][j] + ")");
                }
            }
        }
    }

}
