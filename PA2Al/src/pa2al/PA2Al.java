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

    // Creación e inicialización de arreglos de pacientes y exámenes (Cristian)
    static String[] pacientes = { "Ana", "Luis", "Marta", "Pedro", "Jose" }; // lista de pacientes
    static String[] examenes = { "Glucosa", "Colesterol", "Presion", "Trigliceridos" }; // lista de exámenes

    // Matriz de resultados [paciente][examen]
    static int[][] resultados = {
            { 95, 180, 120, 140 }, // Resultados de Ana
            { 110, 210, 135, 160 }, // Resultados de Luis
            { 130, 250, 145, 190 }, // Resultados de Marta
            { 100, 190, 125, 150 }, // Resultados de Pedro
            { 90, 170, 118, 135 } // Resultados de Jose
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner para leer entrada del usuario

        mostrarTabla(); // Mostrar tabla completa al inicio

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

            while (!sc.hasNextInt()) { // Validar que la entrada sea un número
                System.out.print("Por favor ingrese un número de opción válido: ");
                sc.nextLine();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> busquedaPorPaciente(sc);  // Buscar por paciente
                case 2 -> busquedaPorExamen(sc);    // Buscar por examen
                case 3 -> modificar(sc);             // Modificar resultado
                case 4 -> insertar(sc);              // Insertar paciente
                case 5 -> eliminar(sc);              // Eliminar paciente
                case 6 -> reporteEstadistico();      // Mostrar max, min y promedio
                case 7 -> deteccionAnomalias();      // Detectar valores anormales
                case 8 -> mostrarTabla();            // Mostrar tabla completa
                case 9 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 9); // Repetir hasta que el usuario elija salir

        sc.close(); // Cerrar Scanner
    }

    // Mostrar tabla completa (Cristian)
    static void mostrarTabla() {
        int wNombre = 12;   // ancho columna paciente
        int wDato = 13;     // ancho columna de resultados
        int gap = 2;        // espacio extra entre columnas

        System.out.printf("%-" + wNombre + "s", "Paciente"); // encabezado de pacientes
        for (String e : examenes)
            System.out.printf("%" + (wDato + gap) + "s", e); // encabezados de exámenes
        System.out.println();
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < pacientes.length; i++) {
            System.out.printf("%-" + wNombre + "s", pacientes[i]); // nombre del paciente
            for (int j = 0; j < examenes.length; j++) {
                System.out.printf("%" + (wDato + gap) + "d", resultados[i][j]); // mostrar resultados
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
            if (pacientes[i].equalsIgnoreCase(nombre)) { // comparar ignorando mayúsculas
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
            System.out.println("Paciente no encontrado."); // mensaje si no existe
    }

    // Búsqueda por examen (Jonathan)
    static void busquedaPorExamen(Scanner sc) {
        System.out.print("Ingrese el nombre del examen a buscar: ");
        String examen = sc.nextLine().trim();

        int col = -1;
        for (int j = 0; j < examenes.length; j++) {
            if (examenes[j].equalsIgnoreCase(examen)) {
                col = j; // guardar índice de columna
                break;
            }
        }

        if (col == -1) { // examen no encontrado
            System.out.println("Examen no encontrado.");
            return;
        }

        System.out.println("\nExamen: " + examenes[col]);
        System.out.println("Relación de pacientes y sus resultados:");
        for (int i = 0; i < pacientes.length; i++) {
            System.out.println(" - " + pacientes[i] + " = " + resultados[i][col]); // mostrar resultados
        }
    }

    // Modificar resultado (Jonathan)
    static void modificar(Scanner sc) {
        System.out.print("Ingrese el nombre del paciente a modificar: ");
        String paciente = sc.nextLine().trim();

        System.out.print("Ingrese el nombre del examen a modificar: ");
        String examen = sc.nextLine().trim();

        System.out.print("Ingrese el nuevo valor (entero): ");
        while (!sc.hasNextInt()) { // validar entero
            System.out.print("Ingrese un número entero válido: ");
            sc.nextLine();
        }
        int nuevoValor = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        int fila = -1, col = -1;
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].equalsIgnoreCase(paciente)) {
                fila = i; // fila del paciente
                break;
            }
        }
        for (int j = 0; j < examenes.length; j++) {
            if (examenes[j].equalsIgnoreCase(examen)) {
                col = j; // columna del examen
                break;
            }
        }

        if (fila == -1 || col == -1) { // validar existencia
            System.out.println("Paciente o examen no encontrado.");
            return;
        }

        resultados[fila][col] = nuevoValor; // actualizar valor
        System.out.println("Se modificó el valor de " + examen + " de " + paciente + " a " + nuevoValor);
        System.out.println("\nTabla actualizada:");
        mostrarTabla(); // mostrar tabla actualizada
    }

    // Insertar nuevo paciente (Gustavo)
    static void insertar(Scanner sc) {
        System.out.print("Ingrese el nombre del nuevo paciente: ");
        String nuevoPaciente = sc.nextLine().trim();

        int[] nuevosResultados = new int[examenes.length]; // crear array para resultados nuevos
        for (int j = 0; j < examenes.length; j++) {
            System.out.print("Ingrese el resultado de " + examenes[j] + " (entero): ");
            while (!sc.hasNextInt()) { // validar entero
                System.out.print("Ingrese un número entero válido: ");
                sc.nextLine();
            }
            nuevosResultados[j] = sc.nextInt(); 
            sc.nextLine();
        }

        // Ampliar arreglo de pacientes
        String[] nuevosPacientes = new String[pacientes.length + 1];
        for (int i = 0; i < pacientes.length; i++) nuevosPacientes[i] = pacientes[i];
        nuevosPacientes[pacientes.length] = nuevoPaciente;
        pacientes = nuevosPacientes;

        // Ampliar matriz de resultados
        int[][] nuevosResultadosMatriz = new int[resultados.length + 1][examenes.length];
        for (int i = 0; i < resultados.length; i++)
            for (int j = 0; j < examenes.length; j++)
                nuevosResultadosMatriz[i][j] = resultados[i][j]; // copiar resultados anteriores
        for (int j = 0; j < examenes.length; j++)
            nuevosResultadosMatriz[resultados.length][j] = nuevosResultados[j]; // agregar nuevos resultados

        resultados = nuevosResultadosMatriz;

        System.out.println("Se insertó el paciente " + nuevoPaciente + " con sus resultados.");
        System.out.println("\nTabla actualizada:");
        mostrarTabla();
    }

    // Eliminar paciente (Gustavo)
    static void eliminar(Scanner sc) {
        System.out.print("Ingrese el nombre del paciente a eliminar: ");
        String pacienteEliminar = sc.nextLine().trim();

        int indice = -1;
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].equalsIgnoreCase(pacienteEliminar)) {
                indice = i; // índice del paciente a eliminar
                break;
            }
        }
        if (indice == -1) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        // Crear nuevo arreglo de pacientes sin el eliminado
        String[] nuevosPacientes = new String[pacientes.length - 1];
        for (int i = 0, k = 0; i < pacientes.length; i++) {
            if (i != indice) {
                nuevosPacientes[k++] = pacientes[i];
            }
        }

        // Crear nueva matriz de resultados sin el paciente eliminado
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
        for (int i = 0; i < examenes.length; i++) { // recorrer cada examen
            int max = Integer.MIN_VALUE; // valor máximo inicial
            int min = Integer.MAX_VALUE; // valor mínimo inicial
            int total = 0; // acumulador para promedio
            for (int j = 0; j < resultados.length; j++) { // recorrer pacientes
                if (resultados[j][i] > max) max = resultados[j][i]; // actualizar máximo
                if (resultados[j][i] < min) min = resultados[j][i]; // actualizar mínimo
                total += resultados[j][i]; // acumular para promedio
            }
            int promedio = total / resultados.length; // calcular promedio
            System.out.println(examenes[i] + " -> Max: " + max + ", Min: " + min + ", Prom: " + promedio);
        }
    }

    // Detección de anomalías (David)
    static void deteccionAnomalias() {
        System.out.println();
        System.out.println("=== DETECCION DE ANOMALIAS ===");
        int[] umbrales = { 126, 240, 140, 200 }; // umbrales para cada examen
        String[] nombresAnomalias = { "Diabetes", "Alto", "Hipertensión grado 2", "Alto" }; // nombres de anomalías

        for (int i = 0; i < pacientes.length; i++) { // recorrer pacientes
            for (int j = 0; j < examenes.length; j++) { // recorrer examenes
                if (resultados[i][j] > umbrales[j]) { // si sobrepasa umbral
                    System.out.println("Anomalia Detectada: " + pacientes[i] + " - " + examenes[j] + " = "
                            + nombresAnomalias[j] + " (Valor: " + resultados[i][j] + ")");
                }
            }
        }
    }
}
