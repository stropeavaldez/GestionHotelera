package com.gestionHotelera2.infraestructura;

import java.time.LocalDate;
import java.util.List;

import com.gestionHotelera2.gestion.EstadoReserva;
import com.gestionHotelera2.gestion.Puntuacion;
import com.gestionHotelera2.gestion.Reserva;
import com.gestionHotelera2.gestion.ReservaBuilder;
import com.gestionHotelera2.personas.Empleado;
import com.gestionHotelera2.personas.Huesped;

public class HotelFacade {

    private Hotel hotel;

    // El hotel se obtiene SIEMPRE con getInstancia (Singleton), nunca con new
    public HotelFacade(String nombre, String direccion, int cantEstrellas) {
        this.hotel = Hotel.getInstancia(nombre, direccion, cantEstrellas);
    }

    public Habitacion registrarHabitacion(int numero, TipoHabitacion tipo) {
        Habitacion habitacion = new Habitacion(numero, tipo);
        hotel.agregarHabitacion(habitacion);
        return habitacion;
    }

    public Reserva gestionarNuevaReserva(LocalDate fecha, Habitacion habitacion, Huesped huesped, Empleado empleado) {
        
    	validarHabitacionDelHotel(habitacion);
        
        Reserva reserva = new ReservaBuilder()      
                .setFecha(fecha)
                .setHabitacion(habitacion)
                .setHuesped(huesped)                
                .setEmpleado(empleado)
                .build();                           
        huesped.agregarReserva(reserva);            
        return reserva;
    }

    public void suscribirHuesped(Huesped huesped) {
        hotel.suscribir(huesped);
    }

    public void desuscribirHuesped(Huesped huesped) {
        hotel.desuscribir(huesped);
    }

    // Notifica a los suscriptores, excepto al huesped que deja la habitacion
    public void liberarHabitacion(Habitacion habitacion, Huesped huesped) {
        hotel.notificarHabitacionLiberada(habitacion, huesped);
    }

    // El precio vive en cada Habitacion: se actualizan todas las de ese tipo
    public void modificarPrecio(TipoHabitacion tipo, float precio) {
        for (Habitacion h : hotel.verHabitaciones()) {
            if (h.getTipoHabitacion() == tipo) {        
                h.setPrecio(precio);
            }
        }
    }

    public void cancelarReserva(Reserva reserva) {
        Huesped huesped = reserva.getHuesped();
        huesped.cancelarReserva(reserva);               
        liberarHabitacion(reserva.getHabitacion(), huesped);
    }

    public void modificarReserva(Reserva reserva, LocalDate fecha, Habitacion habitacion) {
        if (habitacion != null) {
            validarHabitacionDelHotel(habitacion);
        }
        reserva.getHuesped().modificarReserva(reserva, fecha, habitacion);
    }

    public void finalizarReserva(Reserva reserva, int valor) {
        if (reserva.getEstado() != EstadoReserva.CONFIRMADA) {
            throw new IllegalStateException("Solo se puede finalizar una reserva CONFIRMADA");
        }
        reserva.setEstado(EstadoReserva.FINALIZADA);
        Puntuacion puntuacion = new Puntuacion(reserva, valor);
        reserva.setPuntuacion(puntuacion);
        hotel.agregarPuntuacion(puntuacion);            
        liberarHabitacion(reserva.getHabitacion(), reserva.getHuesped());
    }

    public List<Habitacion> verHabitaciones() {
        return hotel.verHabitaciones();
    }

    public List<Reserva> verReservas(Huesped huesped) {
        return huesped.verReservas();
    }

    public Hotel getHotel() {
        return hotel;
    }

    private void validarHabitacionDelHotel(Habitacion habitacion) {
        if (!hotel.verHabitaciones().contains(habitacion)) {
            throw new IllegalArgumentException("La habitación no pertenece al hotel");
        }
    }
}
