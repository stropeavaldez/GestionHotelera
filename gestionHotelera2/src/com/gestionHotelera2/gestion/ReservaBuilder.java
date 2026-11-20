package com.gestionHotelera2.gestion;

import java.time.LocalDate;

import com.gestionHotelera2.infraestructura.Habitacion;
import com.gestionHotelera2.personas.Empleado;
import com.gestionHotelera2.personas.Huesped;
import com.gestionHotelera2.personas.TipoCargo;

public class ReservaBuilder {

    private LocalDate fecha;
    private Habitacion habitacion;
    private Huesped huesped;
    private Empleado empleado;
    
    LocalDate getFecha() {
    	return fecha; 
    	}
    
    public ReservaBuilder setFecha(LocalDate fecha) { 
    	this.fecha = fecha; 
    	return this; 
    	}
    
    Habitacion getHabitacion() { 
    	return habitacion; 
    	}
    
    public ReservaBuilder setHabitacion(Habitacion habitacion) { 
    	this.habitacion = habitacion; 
    	return this; 
    	}
    
    Huesped getHuesped() {
    	return huesped; 
    	}
    
    public ReservaBuilder setHuesped(Huesped huesped) { 
    	this.huesped = huesped; 
    	return this; 
    	}
    
    Empleado getEmpleado() { 
    	return empleado; 
    	}
    
    public ReservaBuilder setEmpleado(Empleado empleado) { 
    	this.empleado = empleado; 
    	return this; 
    	}

    // Valida que la reserva este completa ANTES de crearla: nunca existe una reserva a medias
    public Reserva build() {
        if (fecha == null || habitacion == null || huesped == null || empleado == null) {
            throw new IllegalStateException("Faltan datos para crear la reserva");
        }
        if (empleado.getCargo() != TipoCargo.RECEPCION) {
            throw new IllegalStateException("La reserva debe ser gestionada por un empleado de RECEPCION");
        }
        return new Reserva(this);
    }

}
