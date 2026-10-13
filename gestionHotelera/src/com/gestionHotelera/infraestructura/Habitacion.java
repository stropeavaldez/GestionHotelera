package com.gestionHotelera.infraestructura;

public class Habitacion {
	private int numero;
	private TipoHabitacion tipo;
	private float precio;

	public Habitacion(int numero, TipoHabitacion tipo) {
		this.numero = numero;
		this.tipo = tipo;
		// Asigna el precio según el tipo de habitación elegido.
		// Si el tipo no es nulo, toma el precio de TipoHabitacion.
		// Si el tipo es nulo, asigna 0.0f.
		this.precio = (tipo != null) ? tipo.getPrecio() : 0.0f;
	}

	public Habitacion(int numero, TipoHabitacion tipo, float precio) {
		this.numero = numero;
		this.tipo = tipo;
		this.precio = precio;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoHabitacion getTipo() {
		return tipo;
	}
	public void setTipo(TipoHabitacion tipo) {
		this.tipo = tipo;
	}

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
}