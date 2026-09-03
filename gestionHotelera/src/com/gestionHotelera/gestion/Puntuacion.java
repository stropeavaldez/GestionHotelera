package com.gestionHotelera.gestion;

public class Puntuacion {
	private int estrellas;
	private Reserva reserva;

	public Puntuacion (int estrellas, Reserva reserva) {
		this.estrellas = estrellas;
		this.reserva = reserva;
	}
	
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas (int estrellas) {
		this.estrellas = estrellas;
	}
	
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva (Reserva reserva) {
		this.reserva = reserva;
	}
	
	public void registrarPuntaje() {
		System.out.println ("Puntaje de " + estrellas + " estrellas registrado para la reserva N° " + reserva.getNumReserva());
	}
	
}
