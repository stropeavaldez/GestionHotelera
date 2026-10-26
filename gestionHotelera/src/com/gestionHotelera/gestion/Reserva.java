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
	
	// Constructor PRIVADO: Solo puede ser invocado desde el Builder
	private Reserva(Builder builder) {
		this.numReserva = builder.numReserva;
		this.fecha = builder.fecha;
		this.huesped = builder.huesped;
		this.habitacion = builder.habitacion;
		this.empleadoRecepcion = builder.empleadoRecepcion;
		this.estado = builder.estado;
		this.puntuacion = builder.puntuacion;
		
		// Vinculación automática en la lista de reservas del huésped (1..*)
		if (this.huesped != null) {
			this.huesped.agregarReserva(this);
		}
	}

	// --- GETTERS Y SETTERS ---
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

	// =========================================================================
	// CLASE BUILDER (Patrón Builder)
	// =========================================================================
	public static class Builder {
		private int numReserva;
		private LocalDate fecha;
		private Huesped huesped;
		private Habitacion habitacion;
		private Empleado empleadoRecepcion;
		private EstadoReserva estado = EstadoReserva.PENDIENTE; // Valor por defecto
		private Puntuacion puntuacion = null;                   // Valor por defecto

		public Builder setNumReserva(int numReserva) {
			this.numReserva = numReserva;
			return this;
		}

		public Builder setFecha(LocalDate fecha) {
			this.fecha = fecha;
			return this;
		}

		public Builder setHuesped(Huesped huesped) {
			this.huesped = huesped;
			return this;
		}

		public Builder setHabitacion(Habitacion habitacion) {
			this.habitacion = habitacion;
			return this;
		}

		public Builder setEmpleadoRecepcion(Empleado empleadoRecepcion) {
			this.empleadoRecepcion = empleadoRecepcion;
			return this;
		}

		public Builder setEstadoReserva(EstadoReserva estado) {
			this.estado = estado;
			return this;
		}

		public Builder setPuntuacion(Puntuacion puntuacion) {
			this.puntuacion = puntuacion;
			return this;
		}

		// Método final que construye y retorna la Reserva aplicando el constructor privado
		public Reserva build() {
			if (fecha == null) {
				throw new IllegalStateException("La fecha de la reserva es obligatoria.");
			}
			return new Reserva(this);
		}
	}
}