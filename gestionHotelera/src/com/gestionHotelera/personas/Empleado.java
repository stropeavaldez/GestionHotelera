package com.gestionHotelera.personas;

import java.time.LocalDate;
import java.time.Period;

public class Empleado extends Persona {

	public TipoCargo cargo;
	private float salario;
	
	public Empleado(String nombre, String apellido, String dni, LocalDate fechaNac, TipoCargo cargo, float salario) {
		super(nombre, apellido, dni, fechaNac);
		this.cargo = cargo;
		this.salario = salario;
	}
	
	public TipoCargo getCargo() {
		return cargo;
	}
	public void setCargo (TipoCargo cargo) {
		this.cargo = cargo;
	}
	
	public float getSalario() {
		return salario;
	}
	public void setSalario (float salario) {
		this.salario = salario;
	}

	@Override
	public int calcularEdad() {
		if (getFechaNac() == null) {
			return 0;
		}
		return Period.between(getFechaNac(), LocalDate.now()).getYears();
	}
	
}
