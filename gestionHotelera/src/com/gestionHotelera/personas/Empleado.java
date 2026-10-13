package com.gestionHotelera.personas;

import java.time.LocalDate;

public class Empleado extends Persona {
	private TipoCargo cargo;
	private float sueldo;

	public Empleado(String nombre, String apellido, String dni, LocalDate fechaNac, TipoCargo cargo, float sueldo) {
		super(nombre, apellido, dni, fechaNac);
		this.cargo = cargo;
		this.sueldo = sueldo;
	}

	public TipoCargo getCargo() {
		return cargo;
	}
	public void setCargo(TipoCargo cargo) {
		this.cargo = cargo;
	}

	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
}