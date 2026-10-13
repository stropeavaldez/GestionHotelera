package com.gestionHotelera.personas;

import java.time.LocalDate;

public class Huesped extends Persona {
	private int numReserva;

	public Huesped(String nombre, String apellido, String dni, LocalDate fechaNac, int numReserva) {
		super(nombre, apellido, dni, fechaNac);
		this.numReserva = numReserva;
	}

	public int getNumReserva() {
		return numReserva;
	}
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}
}