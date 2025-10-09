package pa2al;

import java.util.Scanner;
/* Grupo 6:
 - Gustavo Adolfo Borjas Coronado
 - Jonathan Jesus Bellido Mendez
 - Cristian David Robles Tarazona
 - Jose David Urbano Meza
  El siguiente programa permite gestionar resultados de exámenes 
 médicos de pacientes usando arreglos, mostrando, buscando, modificando, 
 insertando, eliminando y generando reportes.
*/
public class PA2Al {

    //Creación e inicialización de arreglos (Cristian)
    static String[] pacientes = { "Ana", "Luis", "Marta", "Pedro", "Jose" };
    static String[] examenes = { "Glucosa", "Colesterol", "Presion", "Trigliceridos" };

  
    static int[][] resultados = {
            { 95, 180, 120, 140 }, // Ana
            { 110, 210, 135, 160 }, // Luis
            { 130, 250, 145, 190 }, // Marta
            { 100, 190, 125, 150 }, // Pedro
            { 90, 170, 118, 135 } // Jose
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        mostrarTabla();

        int opcion;
        do {
            // Menú principal 
            System.out.println("\n== Sistema Hospital Vida Salud ==");
            System.out.println("1. Buscar resultados por paciente");
            System.out.println("2. Buscar resultados por examen");
            System.out.println("3. Modificar resultado");
            System.out.println("4. Insertar nuevo paciente");
            System.out.println("5. Eliminar paciente");
            System.out.println("6. Reporte estadistico (max, min, promedio)");
            System.out.println("7. Deteccion de anomalias");
            System.out.println("8. Mostrar tabla completa");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            
            while (!sc.hasNextInt()) {
                System.out.print("Por favor ingrese un número de opción válido: ");
                sc.nextLine();
            }
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> busquedaPorPaciente(sc);  
                case 2 -> busquedaPorExamen(sc);     
                case 3 -> modificar(sc);           
                case 4 -> insertar(sc);               
                case 5 -> eliminar(sc);               
                case 6 -> reporteEstadistico();      
                case 7 -> deteccionAnomalias();       
                case 8 -> mostrarTabla();             
                case 9 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 9);

        sc.close();
    }

    // Mostrar tabla completa (Cristian)
    // Matriz con alineación por columnas
    static void mostrarTabla() {
        int wNombre = 12;   
        int wDato = 13;
        int gap = 2;

        System.out.printf("%-" + wNombre + "s", "Paciente");
        for (String e : examenes)
            System.out.printf("%" + (wDato + gap) + "s", e);
        System.out.println();
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < pacientes.length; i++) {
            System.out.printf("%-" + wNombre + "s", pacientes[i]);
            for (int j = 0; j < examenes.length; j++) {
                System.out.printf("%" + (wDato + gap) + "d", resultados[i][j]);
            }
            System.out.println();
        }
    }

    // Búsqueda por paciente (Jonathan)
    static void busquedaPorPaciente(Scanner sc) {
        System.out.print("Ingrese el nombre del paciente a buscar: ");
        String nombre = sc.nextLine().trim();
        boolean encontrado = false;

        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].equalsIgnoreCase(nombre)) {
                encontrado = true;
                System.out.println("\nPaciente: " + pacientes[i]);
                System.out.println("Resultados de sus exámenes:");
                for (int j = 0; j < examenes.length; j++) {
                    System.out.println(" - " + examenes[j] + " = " + resultados[i][j]);
                }
                break;
            }
        }
        if (!encontrado)
            System.out.println("Paciente no encontrado.");
    }

    // Búsqueda por examen (Jonathan)
        static void busquedaPorExamen(Scanner sc) {
        System.out.print("Ingrese el nombre del examen a buscar: ");
        String examen = sc.nextLine().trim();

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
            System.out.println(" - " + pacientes[i] + " = " + resultados[i][col]);
        }
    }

    // Modificar resultado (Jonathan)
       static void modificar(Scanner sc) {
        System.out.print("Ingrese el nombre del paciente a modificar: ");
        String paciente = sc.nextLine().trim();

        System.out.print("Ingrese el nombre del examen a modificar: ");
        String examen = sc.nextLine().trim();

        System.out.print("Ingrese el nuevo valor (entero): ");
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número entero válido: ");
            sc.nextLine();
        }
        int nuevoValor = sc.nextInt();
        sc.nextLine(); 

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

    // Insertar nuevo paciente (Gustavo)
       static void insertar(Scanner sc) {
        System.out.print("Ingrese el nombre del nuevo paciente: ");
        String nuevoPaciente = sc.nextLine().trim();

        int[] nuevosResultados = new int[examenes.length];
        for (int j = 0; j < examenes.length; j++) {
            System.out.print("Ingrese el resultado de " + examenes[j] + " (entero): ");
            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un número entero válido: ");
                sc.nextLine();
            }
            nuevosResultados[j] = sc.nextInt();
            sc.nextLine();
        }

      
        String[] nuevosPacientes = new String[pacientes.length + 1];
        for (int i = 0; i < pacientes.length; i++) nuevosPacientes[i] = pacientes[i];
        nuevosPacientes[pacientes.length] = nuevoPaciente;
        pacientes = nuevosPacientes;

        // ampliar matriz de resultados
        int[][] nuevosResultadosMatriz = new int[resultados.length + 1][examenes.length];
        for (int i = 0; i < resultados.length; i++)
            for (int j = 0; j < examenes.length; j++)
                nuevosResultadosMatriz[i][j] = resultados[i][j];
        for (int j = 0; j < examenes.length; j++)
            nuevosResultadosMatriz[resultados.length][j] = nuevosResultados[j];

        resultados = nuevosResultadosMatriz;

        System.out.println("Se insertó el paciente " + nuevoPaciente + " con sus resultados.");
        System.out.println("\nTabla actualizada:");
        mostrarTabla();
    }

    //Eliminar paciente (Gustavo)
      static void eliminar(Scanner sc) {
        System.out.print("Ingrese el nombre del paciente a eliminar: ");
        String pacienteEliminar = sc.nextLine().trim();

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
                nuevosPacientes[k++] = pacientes[i];
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

    // Reporte estadístico (David) 
        static void reporteEstadistico() {
        System.out.println();
        System.out.println("=== REPORTES POR CADA EXAMEN ===");
        System.out.println("Valor maximo, valor minimo y promedio");
        System.out.println("-------------------------------------");
        for (int i = 0; i < examenes.length; i++) { // recorrer examenes
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
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
            int promedio = total / resultados.length;
            System.out.println(examenes[i] + " -> Max: " + max + ", Min: " + min + ", Prom: " + promedio);
        }
    }

    //Detección de anomalías (David)
      static void deteccionAnomalias() {
        System.out.println();
        System.out.println("=== DETECCION DE ANOMALIAS ===");
        int[] umbrales = { 126, 240, 140, 200 }; // umbrales para cada examen
        String[] nombresAnomalias = { "Diabetes", "Alto", "Hipertensión grado 2", "Alto" }; // nombres de anomalías

        for (int i = 0; i < pacientes.length; i++) { // recorrer pacientes
            for (int j = 0; j < examenes.length; j++) { // recorrer examenes
                if (resultados[i][j] > umbrales[j]) { // valor sobrepasa umbral?
                    System.out.println("Anomalia Detectada: " + pacientes[i] + " - " + examenes[j] + " = "
                            + nombresAnomalias[j] + " (Valor: " + resultados[i][j] + ")");
                }
            }
        }
    }
}
