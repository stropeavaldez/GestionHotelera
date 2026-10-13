package com.gestionHotelera.personas;

import java.time.LocalDate;
import java.time.Period;

public abstract class Persona implements SerVivo {
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNac;
	
	public Persona(String nombre, String apellido, String dni, LocalDate fechaNac) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNac = fechaNac;
	}
	
	// Solución al Duplicated Code: implementación centralizada
	@Override
	public int calcularEdad() {
		if (getFechaNac() == null) {
			return 0;
		}
		return Period.between(getFechaNac(), LocalDate.now()).getYears();
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
}