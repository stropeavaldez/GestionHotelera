package com.gestionHotelera.personas;

import java.time.LocalDate;

public abstract class Persona {
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNac;
	
	public Persona (String nombre, String apellido, String dni, LocalDate fechaNac) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNac = fechaNac;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido (String apellido) {
		this.apellido = apellido;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni (String dni) {
		this.dni = dni;
	}
	
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac (LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public abstract int calcularEdad();
	
}
