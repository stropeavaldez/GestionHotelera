package com.gestionHotelera.personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gestionHotelera.gestion.Reserva;

public class Huesped extends Persona {
	// Reemplazamos "int numReserva" por una lista de objetos Reserva
	private List<Reserva> listReservas;

	public Huesped(String nombre, String apellido, String dni, LocalDate fechaNac) {
		super(nombre, apellido, dni, fechaNac);
		this.listReservas = new ArrayList<>();
	}

	// Permite al huésped sumar múltiples reservas a su historial (1..*)
	public void agregarReserva(Reserva reserva) {
		if (reserva != null) {
			this.listReservas.add(reserva);
		}
	}

	public List<Reserva> getListReservas() {
		return listReservas;
	}
}