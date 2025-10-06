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
    static String[] pacientes = {"Ana", "Luis", "Marta", "Pedro", "Jose"};
    static String[] examenes  = {"Glucosa", "Colesterol", "Presion", "Trigliceridos"};

    // matriz resultados[filaPaciente][colExamen]
    static int[][] resultados = {
            { 95, 180, 120, 140}, // Ana
            {110, 210, 135, 160}, // Luis
            {130, 250, 145, 190}, // Marta
            {100, 190, 125, 150}, // Pedro
            { 90, 170, 118, 135}  // Jose
    };

    public static void main(String[] args) {
        mostrarTabla();   // ejecución de mostrarTabla
    }

    // (2) Recorrido que muestra la matriz (Cristian Robles)
    static void mostrarTabla() {
    int wNombre = 12;   // ancho para "Paciente"
    int wDato   = 13;   // ancho base para cada examen
    int gap     = 2;    // espacio extra entre columnas

    // Encabezados
    System.out.printf("%-" + wNombre + "s", "Paciente");
    for (String e : examenes) System.out.printf("%" + (wDato + gap) + "s", e);
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

}

