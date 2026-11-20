package com.gestionHotelera2.prueba;

import java.time.LocalDate;

import com.gestionHotelera2.gestion.Puntuacion;
import com.gestionHotelera2.gestion.Reserva;
import com.gestionHotelera2.infraestructura.Habitacion;
import com.gestionHotelera2.infraestructura.Hotel;
import com.gestionHotelera2.infraestructura.HotelFacade;
import com.gestionHotelera2.infraestructura.TipoHabitacion;
import com.gestionHotelera2.personas.Empleado;
import com.gestionHotelera2.personas.Huesped;
import com.gestionHotelera2.personas.TipoCargo;

public class PruebaGestionHotelera {

    public static void main(String[] args) {

        System.out.println("===================================================");
        System.out.println("A) ALTA DE HOTEL: 3 HABITACIONES, 6 HUESPEDES Y 2 EMPLEADOS");
        System.out.println("---------------------------------------------------");             
        System.out.println("PRE : hoteles en la cadena = " + Hotel.getCantHotel());

        HotelFacade fachada = new HotelFacade("Hotel Sol", "Av. del Mar 123", 3);
        Hotel hotel = fachada.getHotel();

        Habitacion h1 = fachada.registrarHabitacion(1, TipoHabitacion.SIMPLE);
        Habitacion h2 = fachada.registrarHabitacion(2, TipoHabitacion.DOBLE);
        Habitacion h3 = fachada.registrarHabitacion(3, TipoHabitacion.SUITE);

        Huesped[] huespedes = {
            new Huesped("Ana", "Gomez", "30111111", LocalDate.of(1990, 5, 20)),
            new Huesped("Luis", "Perez", "31222222", LocalDate.of(1985, 8, 14)),
            new Huesped("Marta", "Lopez", "32333333", LocalDate.of(1992, 1, 3)),
            new Huesped("Diego", "Torres", "33444444", LocalDate.of(1978, 11, 30)),
            new Huesped("Sofia", "Ruiz", "34555555", LocalDate.of(2000, 7, 7)),
            new Huesped("Pedro", "Sosa", "35666666", LocalDate.of(1969, 2, 25))
        };
        Empleado recepcionista = new Empleado("Laura", "Diaz", "27777777",
                LocalDate.of(1988, 3, 10), TipoCargo.RECEPCION, 900000);
        Empleado limpieza = new Empleado("Jose", "Mendez", "28888888",
                LocalDate.of(1983, 9, 18), TipoCargo.LIMPIEZA, 700000);

        System.out.println("POST: hotel = " + hotel.getNombre() + " (" + hotel.getDireccion() + ")"
                + " | estrellas = " + hotel.calcularPromedioPuntuaciones());
        System.out.println("      habitaciones registradas = " + hotel.verHabitaciones().size());
        for (Habitacion h : hotel.verHabitaciones()) {
            System.out.println("        - " + h);
        }
        System.out.println("      huespedes creados = " + huespedes.length);
        for (Huesped h : huespedes) {
            System.out.println("        - id " + h.getId() + ": " + h + " (" + h.calcularEdad() + " años)");
        }
        System.out.println("      empleados creados = 2");
        System.out.println("        - id " + recepcionista.getId() + ": " + recepcionista + " (" + recepcionista.getCargo() + ")");
        System.out.println("        - id " + limpieza.getId() + ": " + limpieza + " (" + limpieza.getCargo() + ")");

        System.out.println("===================================================");
        System.out.println("B) TODOS LOS HUESPEDES ESTAN SUSCRIPTOS AL HOTEL");
        System.out.println("---------------------------------------------------"); 
        System.out.println("PRE : suscriptores = " + hotel.verSuscriptores().size());
        for (Huesped h : huespedes) {
            fachada.suscribirHuesped(h);
        }
        fachada.suscribirHuesped(huespedes[0]);   
        boolean todosSuscriptos = true;
        for (Huesped h : huespedes) {
            boolean suscripto = hotel.verSuscriptores().contains(h);
            todosSuscriptos = todosSuscriptos && suscripto;
            System.out.println("      " + h + ": " + (suscripto ? "SUSCRIPTO" : "NO suscripto"));
        }
        System.out.println("POST: suscriptores = " + hotel.verSuscriptores().size());
        System.out.println("      ¿todos suscriptos? " + todosSuscriptos);

        System.out.println("===================================================");
        System.out.println("C) DOS RESERVAS");
        System.out.println("---------------------------------------------------"); 
        System.out.println("PRE : reservas de Ana = " + huespedes[0].verReservas().size()
                + " | reservas de Luis = " + huespedes[1].verReservas().size());
        Reserva r1 = fachada.gestionarNuevaReserva(LocalDate.of(2026, 12, 20), h1, huespedes[0], recepcionista);
        Reserva r2 = fachada.gestionarNuevaReserva(LocalDate.of(2026, 12, 22), h2, huespedes[1], recepcionista);
        Reserva r3 = fachada.gestionarNuevaReserva(LocalDate.of(2026, 12, 20), h1, huespedes[2], recepcionista);
        System.out.println("POST: " + r1);
        System.out.println("      " + r2);
        System.out.println("      " + r3);
        System.out.println("      reservas de Ana = " + huespedes[0].verReservas().size()
                + " | reservas de Luis = " + huespedes[1].verReservas().size() );

        System.out.println("===================================================");
        System.out.println("D) LOS HUESPEDES PUNTUAN AL HOTEL");
        System.out.println("---------------------------------------------------"); 
        System.out.println("PRE : estrellas del hotel = " + hotel.calcularPromedioPuntuaciones()
                + " | puntuaciones = " + hotel.verPuntuaciones().size());
        System.out.println("      " + r1.getEstado() + " / " + r2.getEstado());

        System.out.println("--- Finaliza la reserva #" + r1.getNumero() + " de " + r1.getHuesped() + ". NO debe recibir el aviso:");
        fachada.finalizarReserva(r1, 5);
        System.out.println("--- Finaliza la reserva #" + r2.getNumero() + " de " + r2.getHuesped() + ". NO debe recibir el aviso:");
        fachada.finalizarReserva(r2, 4);

        System.out.println("POST: " + r1.getEstado() + " / " + r2.getEstado());
        System.out.println("      puntuaciones en el hotel = " + hotel.verPuntuaciones().size());
        for (Puntuacion p : hotel.verPuntuaciones()) {
            System.out.println("        - Reserva #" + p.getReserva().getNumero() + " ("
                    + p.getReserva().getHuesped() + "): " + p.getValor() + " estrellas");
        }
        System.out.println("estrellas del hotel (promedio redondeado) = " + hotel.calcularPromedioPuntuaciones());

        System.out.println("===================================================");
        System.out.println("E) DESTRUCCION DEL HOTEL (AGREGACION Y COMPOSICION)");
        System.out.println("---------------------------------------------------"); 
        System.out.println("PRE : hoteles en la cadena = " + Hotel.getCantHotel());
        System.out.println("habitaciones = " + hotel.verHabitaciones().size()
                + " | suscriptores = " + hotel.verSuscriptores().size()
                + " | puntuaciones = " + hotel.verPuntuaciones().size());

        try {
            hotel.destruir();
            System.out.println("Hotel destruido. Hoteles en la cadena = " + Hotel.getCantHotel());
            hotel.verHabitaciones(); // Tiene que fallar: el hotel ya no existe
            System.out.println("ERROR: debería haber fallado");
        } catch (IllegalStateException e) {
            System.out.println("Error controlado (hotel): " + e.getMessage());
        }

        try {
            fachada.registrarHabitacion(4, TipoHabitacion.SIMPLE);
            System.out.println("ERROR: debería haber fallado");
        } catch (IllegalStateException e) {
            System.out.println("Error controlado (fachada): " + e.getMessage());
        }

        System.out.println("POST:");
        System.out.println("COMPOSICION -> el hotel ya no tiene habitaciones: no se puede acceder a ellas desde él.");
        System.out.println("COMPOSICION -> la reserva #" + r1.getNumero() + " todavía referencia a su habitación (asociación Reserva -> Habitacion): " + r1.getHabitacion());
        System.out.println("AGREGACION  -> los huéspedes siguen existiendo:");
        for (Huesped h : huespedes) {
            System.out.println("        - " + h + " (id " + h.getId() + ", " + h.calcularEdad()
                    + " años, reservas = " + h.verReservas().size() + ")");
        }
        System.out.println("AGREGACION  -> los empleados siguen existiendo: "
                + recepcionista + " y " + limpieza);
        System.out.println("AGREGACION  -> las puntuaciones siguen en sus reservas: reserva #"
                + r1.getNumero() + " = " + r1.getPuntuacion().getValor()
                + ", reserva #" + r2.getNumero() + " = " + r2.getPuntuacion().getValor());

        Hotel nuevo = Hotel.getInstancia("Hotel Nuevo", "Calle 1", 4);
        System.out.println("  SINGLETON   -> ya se puede crear otro hotel: " + nuevo.getNombre()
                + " | hoteles en la cadena = " + Hotel.getCantHotel()
                + " | ¿es el hotel destruido? " + (nuevo == hotel));
    }

}