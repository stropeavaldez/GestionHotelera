package com.gestionHotelera.infraestructura;

public class Habitacion {
	private int numero;
	private TipoHabitacion tipo;

	public Habitacion (int numero, TipoHabitacion tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero (int numero) {
		this.numero = numero;
	}

	public TipoHabitacion getTipo() {
		return tipo;
	}
	public void setTipo (TipoHabitacion tipo) {
		this.tipo = tipo;
	}
}
