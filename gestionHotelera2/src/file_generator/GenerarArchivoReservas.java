package file_generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GenerarArchivoReservas {

    public static void main(String[] args) {
        String rutaTxt = "reservas.txt";
        String rutaCsv = "reservas.csv";   
        String[] tiposHabitacion = {"simple", "doble", "suite"};
        String[] nombres = {"Juan", "Ana", "Carlos", "Lucia", "Pedro", "Sofia", "Diego", "Laura", "Marcos", "Valentina"};
        String[] apellidos = {"Perez", "Gomez", "Lopez", "Diaz", "Fernandez", "Torres", "Ruiz", "Sosa", "Mendez", "Herrera"};
        Random random = new Random();

        Map<Integer, Integer> nochesPorReserva = new HashMap<>();   // numero de reserva -> noches
        Map<Integer, Integer> totalPorReserva = new HashMap<>();    // numero de reserva -> total a pagar

        try (FileWriter txt = new FileWriter(rutaTxt);
             FileWriter csv = new FileWriter(rutaCsv)) {

            csv.write("fecha,nro_reserva,huesped,dni,tipo_habitacion,empleado,noches,total_a_pagar\n");

            for (int i = 1; i <= 5000; i++) {
   
                int mes = 1 + random.nextInt(12);
                int dia = 1 + random.nextInt(28);
                String fecha = String.format("2026-%02d-%02d", mes, dia);

                String nombreHuesped = nombres[random.nextInt(nombres.length)];
                String apellidoHuesped = apellidos[random.nextInt(apellidos.length)];
                int dni = 10000000 + random.nextInt(40000000);

                String tipoHabitacion = tiposHabitacion[random.nextInt(tiposHabitacion.length)];
                int numeroReserva = 1000 + i;

                // Cantidad de noches (1 a 7) y total a pagar
                int noches = 1 + random.nextInt(7);
                int total = noches * precioPorNoche(tipoHabitacion);

                nochesPorReserva.put(numeroReserva, noches);
                totalPorReserva.put(numeroReserva, total);

                // TXT
                String lineaTxt = fecha + ", Nro. reserva: " + numeroReserva + ", Huésped: "
                        + nombreHuesped + " " + apellidoHuesped + " DNI " + dni
                        + ", Tipo de habitación: " + tipoHabitacion
                        + ", Empleado asignado: Sr. Empleado"
                        + ", Noches: " + noches + ", Total a pagar: $" + total + "\n";
                txt.write(lineaTxt);

                // CSV
                String lineaCsv = fecha + "," + numeroReserva + "," + nombreHuesped + " " + apellidoHuesped
                        + "," + dni + "," + tipoHabitacion + ",Sr. Empleado," + noches + "," + total + "\n";
                csv.write(lineaCsv);
            }

            System.out.println("Archivos generados correctamente: " + rutaTxt + " y " + rutaCsv);

        } catch (IOException e) {
            System.out.println("Error al generar los archivos: " + e.getMessage());
            return;
        }

        System.out.println("Registros en los diccionarios: " + nochesPorReserva.size());
        for (int numero = 1001; numero <= 1003; numero++) {
            System.out.println("Reserva " + numero + " -> noches: " + nochesPorReserva.get(numero)
                    + " | total a pagar: $" + totalPorReserva.get(numero));
        }
    }

    private static int precioPorNoche(String tipo) {
        if (tipo.equals("simple")) {
            return 50000;
        } else if (tipo.equals("doble")) {
            return 80000;
        }
        return 150000; 
    }
}
