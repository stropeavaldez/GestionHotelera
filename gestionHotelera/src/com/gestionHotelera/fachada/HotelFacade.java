package com.gestionHotelera.fachada;

import java.time.LocalDate;
import com.gestionHotelera.gestion.*;
import com.gestionHotelera.infraestructura.*;
import com.gestionHotelera.personas.*;

public class HotelFacade {
    
    private Hotel hotel;

    // Constructor público 
    public HotelFacade() {
        // Obtiene la única instancia del Hotel 
        this.hotel = Hotel.getInstancia();
    }

    // Facilita la creación y registro de una habitación en el hotel.
    public Habitacion registrarHabitacionEnHotel(int numero, TipoHabitacion tipo, float precio) {
        Habitacion habitacion = new Habitacion(numero, tipo, precio);
        hotel.agregarHabitacion(habitacion);
        System.out.println("[FACADE] Habitación N° " + numero + " (" + tipo + ") agregada exitosamente al hotel.");
        return habitacion;
    }

    // Centraliza y simplifica la creación de una Reserva utilizando internamente el patrón Builder.
    public Reserva gestionarNuevaReserva(int numReserva, LocalDate fecha, Huesped huesped, Habitacion habitacion, Empleado empleado, EstadoReserva estado) {
        Reserva reserva = new Reserva.Builder()
                .setNumReserva(numReserva)
                .setFecha(fecha)
                .setHuesped(huesped)
                .setHabitacion(habitacion)
                .setEmpleadoRecepcion(empleado)
                .setEstadoReserva(estado)
                .build();
        
        System.out.println("[FACADE] Reserva N° " + numReserva + " procesada y vinculada al huésped con éxito.");
        return reserva;
    }

    // =========================================================================
    // MÉTODOS DE GESTIÓN OBSERVER DESDE LA FACHADA
    // =========================================================================

    // Suscribe un huésped a las notificaciones del hotel
    public void suscribirHuesped(Huesped huesped) {
        hotel.suscribir(huesped);
        System.out.println("[FACADE] Huésped " + huesped.getNombre() + " " + huesped.getApellido() + " suscripto a las notificaciones del hotel.");
    }

    // Desuscribe un huésped de las notificaciones del hotel
    public void desuscribirHuesped(Huesped huesped) {
        hotel.desuscribir(huesped);
        System.out.println("[FACADE] Huésped " + huesped.getNombre() + " " + huesped.getApellido() + " desuscripto de las notificaciones.");
    }

    // Libera una habitación y notifica a los suscriptores (excluyendo al huésped que se retira)
    public void liberarHabitacion(Habitacion habitacion, Huesped huespedQueSale) {
        hotel.notificarHabitacionLiberada(habitacion, huespedQueSale);
    }
}