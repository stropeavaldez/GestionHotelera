package com.gestionHotelera.gestion;

import com.gestionHotelera.infraestructura.Hotel;

public class Puntuacion {
	private int estrellas;
	private Reserva reserva;

	public Puntuacion(int estrellas, Reserva reserva) {
		this.estrellas = estrellas;
		this.reserva = reserva;
	}

	// Permite registrar el puntaje e impactarlo en el Hotel solo si la reserva finalizó
	public boolean registrarPuntaje(Hotel hotel) {
		if (this.reserva != null && this.reserva.getEstadoReserva() == EstadoReserva.FINALIZADA) {
			if (hotel != null) {
				hotel.agregarPuntuacion(this);
			}
			System.out.println("Puntaje de " + estrellas + " estrellas registrado con éxito para la reserva N° " + reserva.getNumReserva());
			return true;
		} else {
			System.out.println("Error: No se puede registrar la puntuación. La reserva N° " 
					+ (reserva != null ? reserva.getNumReserva() : "S/N") 
					+ " aún no está FINALIZADA.");
			return false;
		}
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}