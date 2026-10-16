package com.gestionHotelera.gestion;

import java.time.LocalDate;

import com.gestionHotelera.infraestructura.Habitacion;
import com.gestionHotelera.personas.Empleado;
import com.gestionHotelera.personas.Huesped;

public class Reserva {
	
	private int numReserva;
	private LocalDate fecha;
	private Huesped huesped;
	private Habitacion habitacion;
	private Empleado empleadoRecepcion;
	private EstadoReserva estado;
	private Puntuacion puntuacion;
	
	public Reserva(int numReserva, LocalDate fecha, Huesped huesped, Habitacion habitacion, Empleado empleadoRecepcion) {
		this.numReserva = numReserva;
		this.fecha = fecha;
		this.huesped = huesped;
		this.habitacion = habitacion;
		this.empleadoRecepcion = empleadoRecepcion;
		this.estado = EstadoReserva.PENDIENTE;
		this.puntuacion = null;
		
		// Vinculación automática en la lista de reservas del huésped (1..*)
		if (huesped != null) {
			huesped.agregarReserva(this);
		}
	}

	public int getNumReserva() {
		return numReserva;
	}
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Huesped getHuesped() {
		return huesped;
	}
	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Empleado getEmpleadoRecepcion() {
		return empleadoRecepcion;
	}
	public void setEmpleadoRecepcion(Empleado empleadoRecepcion) {
		this.empleadoRecepcion = empleadoRecepcion;
	}

	public EstadoReserva getEstadoReserva() {
		return estado;
	}
	public void setEstadoReserva(EstadoReserva estado) {
		this.estado = estado;
	}

	public Puntuacion getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Puntuacion puntuacion) {
		this.puntuacion = puntuacion;
	}
}