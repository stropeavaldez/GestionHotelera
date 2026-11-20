package file_reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Reporte {

    public static void main(String[] args) {
        String rutaArchivo = "reservas.txt";

        Map<Integer, Integer> reservasPorMes = new HashMap<>();
        Map<String, Integer> reservasPorTipo = new HashMap<>();
        Map<DayOfWeek, Integer> reservasPorDia = new HashMap<>();
        
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Campos: [0] fecha, [1] nro. reserva, [2] huesped, [3] tipo de habitacion, ...
                String[] campos = linea.split(",");
                if (campos.length < 4) {
                    continue;                         // linea malformada: se ignora
                }
                try {
                    LocalDate fecha = LocalDate.parse(campos[0].trim(), formatoFecha);
                    String tipo = campos[3].split(":")[1].trim();   // "Tipo de habitación: doble" -> "doble"

                    int mes = fecha.getMonthValue();
                    DayOfWeek dia = fecha.getDayOfWeek();

                    reservasPorMes.put(mes, reservasPorMes.getOrDefault(mes, 0) + 1);
                    reservasPorTipo.put(tipo, reservasPorTipo.getOrDefault(tipo, 0) + 1);
                    reservasPorDia.put(dia, reservasPorDia.getOrDefault(dia, 0) + 1);

                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    // fecha o tipo mal formado: se ignora la linea
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        mostrarMesConMasReservas(reservasPorMes);
        mostrarTiposMasContratados(reservasPorTipo);
        mostrarDiaConMasReservas(reservasPorDia);
    }

    // ---------------- Reporte 1: mes con mas reservas ----------------
    private static void mostrarMesConMasReservas(Map<Integer, Integer> reservasPorMes) {
        int mesMasReservas = -1;
        int maxReservas = 0;

        for (Map.Entry<Integer, Integer> entry : reservasPorMes.entrySet()) {
            if (entry.getValue() > maxReservas) {
                maxReservas = entry.getValue();
                mesMasReservas = entry.getKey();
            }
        }

        System.out.println("=== Cantidad de reservas por mes ===");
        for (int mes = 1; mes <= 12; mes++) {
            System.out.println("Mes " + mes + ": " + reservasPorMes.getOrDefault(mes, 0) + " reservas");
        }
        System.out.println("\nEl mes con más reservas es: " + mesMasReservas
                + " con " + maxReservas + " reservas.");
    }

    // ---------------- Reporte 2: tipos de habitacion mas contratados ----------------
    private static void mostrarTiposMasContratados(Map<String, Integer> reservasPorTipo) {
        List<Map.Entry<String, Integer>> ranking = new ArrayList<>(reservasPorTipo.entrySet());
        ranking.sort((a, b) -> b.getValue() - a.getValue()); 

        System.out.println("\n=== Tipos de habitación más contratados (de mayor a menor) ===");
        int puesto = 1;
        for (Map.Entry<String, Integer> entry : ranking) {
            System.out.println(puesto + ". " + entry.getKey() + ": " + entry.getValue() + " reservas");
            puesto++;
        }
    }

    // ---------------- Reporte 3: dia de la semana con mas reservas ----------------
    private static void mostrarDiaConMasReservas(Map<DayOfWeek, Integer> reservasPorDia) {
        DayOfWeek diaMaximo = null;
        int maxReservas = 0;

        for (Map.Entry<DayOfWeek, Integer> entry : reservasPorDia.entrySet()) {
            if (diaMaximo == null || entry.getValue() > maxReservas) {
                diaMaximo = entry.getKey();
                maxReservas = entry.getValue();
            }
        }

        Locale espanol = Locale.forLanguageTag("es-AR");
        System.out.println("\n=== Reservas por día de la semana ===");
        for (DayOfWeek dia : DayOfWeek.values()) {  
            System.out.println(dia.getDisplayName(TextStyle.FULL, espanol) + ": "
                    + reservasPorDia.getOrDefault(dia, 0));
        }

        if (diaMaximo != null) {
            System.out.println("\nDía con más reservas: " + diaMaximo.getDisplayName(TextStyle.FULL, espanol)
                    + " (" + maxReservas + " reservas).");
        }
    }
}
