package com.gestionHotelera.infraestructura;

public enum TipoHabitacion {
	SIMPLE(50000.0f),
	DOBLE(75000.0f),
	SUITE(120000.0f);
	
	private float precio;
	
	TipoHabitacion(float precio) {
		this.precio = precio;
	}
	
	public float getPrecio() {
		return precio;
	}
}
