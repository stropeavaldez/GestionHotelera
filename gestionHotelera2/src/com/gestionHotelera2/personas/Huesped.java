package com.gestionHotelera2.personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gestionHotelera2.gestion.EstadoReserva;
import com.gestionHotelera2.gestion.Reserva;
import com.gestionHotelera2.infraestructura.Habitacion;

public class Huesped extends Persona {

    private List<Reserva> listReservas = new ArrayList<>();

    public Huesped(String nombre, String apellido, String dni, LocalDate fechaNac) {
        super(nombre, apellido, dni, fechaNac);
    }

    public void agregarReserva(Reserva reserva) {
        if (reserva != null && !listReservas.contains(reserva)) {
            listReservas.add(reserva);
        }
    }

    public void modificarReserva(Reserva reserva, LocalDate fecha, Habitacion habitacion) {
        validarReservaModificable(reserva);
        if (fecha != null) {
            reserva.setFecha(fecha);
        }
        if (habitacion != null) {
            reserva.setHabitacion(habitacion);
        }
    }

    public void cancelarReserva(Reserva reserva) {
        validarReservaModificable(reserva);
        reserva.setEstado(EstadoReserva.CANCELADA);
    }

    private void validarReservaModificable(Reserva reserva) {
        if (reserva == null || !listReservas.contains(reserva)) {
            throw new IllegalArgumentException("La reserva no pertenece a este huesped");
        }
        if (reserva.getEstado() != EstadoReserva.CONFIRMADA) {
            throw new IllegalStateException("Solo se puede operar sobre una reserva CONFIRMADA (estado actual: "
                    + reserva.getEstado() + ")");
        }
    }

    public List<Reserva> verReservas() {
        return new ArrayList<>(listReservas);
    }

    public void recibirNotificacion(Habitacion habitacion) {
        System.out.println("   [Notificacion para " + this + "] Habitación liberada -> tipo: "
                + habitacion.getTipoHabitacion() + " | costo por noche: $" + habitacion.getPrecio());
    }
}
