/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pa2al;



public class PA2Al {

   
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
                System.out.println("Resultados de sus exámenes:");
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
        System.out.println("Relación de pacientes y sus resultados:");
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
        System.out.println("Se modificó el valor de " + examen + " de " + paciente + " a " + nuevoValor);

        System.out.println("\nTabla actualizada:");
        mostrarTabla();
    }
}