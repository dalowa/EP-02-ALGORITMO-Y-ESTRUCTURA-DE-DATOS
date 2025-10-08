/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.pa2al; //editas con el nombre de tu proyecto XD jaj salu2

public class Pa2al {

   
    static String[] pacientes = {"Ana", "Luis", "Marta", "Pedro", "Jose"};
    static String[] examenes  = {"Glucosa", "Colesterol", "Presion", "Trigliceridos"};

  
    static int[][] resultados = {
            { 95, 180, 120, 140},
            {110, 210, 135, 160}, 
            {130, 250, 145, 190},
            {100, 190, 125, 150}, 
            { 90, 170, 118, 135} 
    };

    public static void main(String[] args) {
       
        mostrarTabla();   

       
        String nombreBuscado = "Ana";
        busquedaPorPaciente(nombreBuscado);

        
        String examenBuscado = "Glucosa";
        busquedaPorExamen(examenBuscado);

       
        String pacienteModificar = "Marta";
        String examenModificar = "Glucosa";
        int nuevoValor = 115;
        modificar(pacienteModificar, examenModificar, nuevoValor);

        
        String nuevoPaciente = "Carla";
        int[] nuevosResultados = {105, 200, 128, 155};
        insertar(nuevoPaciente, nuevosResultados);


        String pacienteEliminar = "Pedro";
        eliminar(pacienteEliminar);
    }

  
    static void mostrarTabla() {
        int wNombre = 12;   
        int wDato   = 13;   
        int gap     = 2;    

        
        System.out.printf("%-" + wNombre + "s", "Paciente");
        for (String e : examenes) System.out.printf("%" + (wDato + gap) + "s", e);
        System.out.println();

       
        for (int i = 0; i < pacientes.length; i++) {
            System.out.printf("%-" + wNombre + "s", pacientes[i]);
            for (int j = 0; j < examenes.length; j++) {
                System.out.printf("%" + (wDato + gap) + "d", resultados[i][j]);
            }
            System.out.println();
        }
    }

    
    static void busquedaPorPaciente(String nombre) {
        System.out.println("\nBUSQUEDA POR PACIENTE");
        boolean encontrado = false;

        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].equalsIgnoreCase(nombre)) {
                encontrado = true;
                System.out.println("\nPaciente: " + pacientes[i]);
                System.out.println("Resultados de sus examenes:");
                for (int j = 0; j < examenes.length; j++) {
                    System.out.println(examenes[j] + " = " + resultados[i][j]);
                }
                break;
            }
        }
        if (!encontrado)
            System.out.println("Paciente no encontrado.");
    }

   
    static void busquedaPorExamen(String examen) {
        System.out.println("\nBUSQUEDA POR EXAMEN");
        int col = -1;

        for (int j = 0; j < examenes.length; j++) {
            if (examenes[j].equalsIgnoreCase(examen)) {
                col = j;
                break;
            }
        }

        if (col == -1) {
            System.out.println("Examen no encontrado.");
            return;
        }

        System.out.println("\nExamen: " + examenes[col]);
        System.out.println("Relacion de pacientes y sus resultados:");
        for (int i = 0; i < pacientes.length; i++) {
            System.out.println(pacientes[i] + " = " + resultados[i][col]);
        }
    }

   
    static void modificar(String paciente, String examen, int nuevoValor) {
        System.out.println("\nMODIFICAR RESULTADO");
        int fila = -1, col = -1;

        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].equalsIgnoreCase(paciente)) {
                fila = i;
                break;
            }
        }

        for (int j = 0; j < examenes.length; j++) {
            if (examenes[j].equalsIgnoreCase(examen)) {
                col = j;
                break;
            }
        }

        if (fila == -1 || col == -1) {
            System.out.println("Paciente o examen no encontrado.");
            return;
        }

        resultados[fila][col] = nuevoValor;
        System.out.println("Se modificaron los valores de " + examen + " de " + paciente + " a " + nuevoValor);

        System.out.println("\nTabla actualizada:");
        mostrarTabla();
    }


    static void insertar(String nuevoPaciente, int[] nuevosResultados) {
        System.out.println("\nINSERTAR NUEVO PACIENTE");

        String[] nuevosPacientes = new String[pacientes.length + 1];
        for (int i = 0; i < pacientes.length; i++) {
            nuevosPacientes[i] = pacientes[i];
        }
        nuevosPacientes[pacientes.length] = nuevoPaciente;


        int[][] nuevosResultadosMatriz = new int[resultados.length + 1][examenes.length];
        for (int i = 0; i < resultados.length; i++) {
            for (int j = 0; j < examenes.length; j++) {
                nuevosResultadosMatriz[i][j] = resultados[i][j];
            }
        }
        for (int j = 0; j < examenes.length; j++) {
            nuevosResultadosMatriz[resultados.length][j] = nuevosResultados[j];
        }

        pacientes = nuevosPacientes;
        resultados = nuevosResultadosMatriz;

        System.out.println("Se inserto el paciente " + nuevoPaciente + " con sus resultados.");
        System.out.println("\nTabla actualizada:");
        mostrarTabla();
    }

 
    static void eliminar(String pacienteEliminar) {
        System.out.println("\nELIMINAR PACIENTE");
        int indice = -1;

       
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].equalsIgnoreCase(pacienteEliminar)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        
        String[] nuevosPacientes = new String[pacientes.length - 1];
        for (int i = 0, k = 0; i < pacientes.length; i++) {
            if (i != indice) {
                nuevosPacientes[k] = pacientes[i];
                k++;
            }
        }

        
        int[][] nuevosResultados = new int[resultados.length - 1][examenes.length];
        for (int i = 0, k = 0; i < resultados.length; i++) {
            if (i != indice) {
                for (int j = 0; j < examenes.length; j++) {
                    nuevosResultados[k][j] = resultados[i][j];
                }
                k++;
            }
        }

        
        pacientes = nuevosPacientes;
        resultados = nuevosResultados;

        System.out.println("El paciente " + pacienteEliminar + " ha sido eliminado del sistema.");
        System.out.println("\nTabla actualizada:");
        mostrarTabla();
    }
}
