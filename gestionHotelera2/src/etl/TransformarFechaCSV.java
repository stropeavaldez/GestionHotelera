package etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TransformarFechaCSV {

    public static void main(String[] args) {
        String rutaEntrada = "reservas.csv";
        String rutaSalida = "reservas_transformado.csv";
        DateTimeFormatter formatoOrigen = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoDestino = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int transformadas = 0;
        int descartadas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaEntrada));
             FileWriter salida = new FileWriter(rutaSalida)) {

            String encabezado = br.readLine();          // la primera linea son los nombres de columna
            salida.write(encabezado + "\n");

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",", -1);   // -1 conserva las columnas vacias
                try {
                    LocalDate fecha = LocalDate.parse(campos[0].trim(), formatoOrigen);
                    campos[0] = fecha.format(formatoDestino);        // Transform: solo cambia la columna 0
                    salida.write(String.join(",", campos) + "\n");   // Load
                    transformadas++;
                } catch (DateTimeParseException e) {
                    descartadas++;                                   // fecha mal formada: no se carga
                }
            }

        } catch (IOException e) {
            System.out.println("Error en el proceso ETL: " + e.getMessage());
            return;
        }

        System.out.println("ETL finalizado: " + transformadas + " filas transformadas, "
                + descartadas + " descartadas.");
        System.out.println("Archivo generado: " + rutaSalida);

        mostrarMuestra(rutaEntrada, "Antes  (" + rutaEntrada + ")");
        mostrarMuestra(rutaSalida, "Después (" + rutaSalida + ")");
    }

    // Imprime el encabezado y las 2 primeras filas, para comparar el antes y el despues
    private static void mostrarMuestra(String ruta, String titulo) {
        System.out.println("\n" + titulo);
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            for (int i = 0; i < 3; i++) {
                String linea = br.readLine();
                if (linea != null) {
                    System.out.println("  " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer " + ruta);
        }
    }
}
