package com.gestionHotelera2.gestion;

import java.time.LocalDate;

import com.gestionHotelera2.infraestructura.Habitacion;
import com.gestionHotelera2.personas.Empleado;
import com.gestionHotelera2.personas.Huesped;

public class Reserva {

    private static int contador = 0;         

    private int numero;
    private LocalDate fecha;
    private Habitacion habitacion;
    private Huesped huesped;
    private Empleado empleado;               
    private EstadoReserva estado;
    private Puntuacion puntuacion;           

    Reserva(ReservaBuilder builder) {
        this.numero = ++contador;
        this.fecha = builder.getFecha();
        this.habitacion = builder.getHabitacion();
        this.huesped = builder.getHuesped();
        this.empleado = builder.getEmpleado();
        this.estado = EstadoReserva.CONFIRMADA;   
    }

    public int getNumero() { 
    	return numero; 
    	}

    public LocalDate getFecha() { 
    	return fecha; 
    	}
    
    public void setFecha(LocalDate fecha) { 
    	this.fecha = fecha; 
    	}

    public Habitacion getHabitacion() { 
    	return habitacion; 
    	}
    
    public void setHabitacion(Habitacion habitacion) { 
    	this.habitacion = habitacion; 
    	}

    public Huesped getHuesped() { 
    	return huesped; 
    	}
    
    public void setHuesped(Huesped huesped) { 
    	this.huesped = huesped; 
    	}

    public Empleado getEmpleado() { 
    	return empleado; 
    	}
    
    public void setEmpleado(Empleado empleado) { 
    	this.empleado = empleado; 
    	}

    public EstadoReserva getEstado() { 
    	return estado; 
    	}
    
    public void setEstado(EstadoReserva estado) { 
    	this.estado = estado; 
    	}

    public Puntuacion getPuntuacion() { 
    	return puntuacion; 
    	}
    
    public void setPuntuacion(Puntuacion puntuacion) {
        if (estado != EstadoReserva.FINALIZADA) {
            throw new IllegalStateException("Solo se puede puntuar una reserva FINALIZADA");
        }
        if (this.puntuacion != null) {
            throw new IllegalStateException("La reserva ya fue puntuada");
        }
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Reserva #" + numero + " [" + huesped + " | " + habitacion + " | " + fecha + " | " + estado + "]";
    }
}