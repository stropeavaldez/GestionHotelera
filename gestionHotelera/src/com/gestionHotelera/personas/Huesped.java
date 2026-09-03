package com.gestionHotelera.personas;

import java.time.LocalDate;
import java.time.Period;

public class Huesped extends Persona {

	private int numReserva;
	
	public Huesped(String nombre, String apellido, String dni, LocalDate fechaNac, int numReserva) {
		super(nombre, apellido, dni, fechaNac);
		this.numReserva = numReserva;
	}
	
	public int getNumReserva() {
		return numReserva;
	}
	public void setNumReserva (int numReserva) {
		this.numReserva = numReserva;
	}
	
	public void hacerReserva() {
		System.out.println ("El huésped" + getNombre() + " " + getApellido() + " ha realizado una reserva.");
	}

	@Override
	public int calcularEdad() {
		if (getFechaNac() == null) {
			return 0;
		}
		return Period.between(getFechaNac(), LocalDate.now()).getYears();
	}
}
