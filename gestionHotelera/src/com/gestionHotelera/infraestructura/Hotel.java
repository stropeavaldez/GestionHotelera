package com.gestionHotelera.infraestructura;

import java.util.ArrayList;
import java.util.List;

import com.gestionHotelera.gestion.Puntuacion;

public class Hotel {
	private String nombre;
	private String direccion;
	private float cantEstrellas;
	private List<Puntuacion> listPuntuaciones;
	private List<Habitacion> listHabitaciones;
	
	public Hotel (String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.cantEstrellas = 0;
		this.listPuntuaciones = new ArrayList<>();
		this.listHabitaciones = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion (String direccion) {
		this.direccion = direccion;
	}
	
	public float getCantEstrellas() {
		return cantEstrellas;
	}
	public void setCantEstrellas (float cantEstrellas) {
		this.cantEstrellas = cantEstrellas;
	}
	
	public List<Puntuacion> getListPuntuaciones() {
		return listPuntuaciones;
	}
	
	public List<Habitacion> getListHabitaciones() {
		return listHabitaciones;
	}
	
	public float calcularPromedioEstrellas() {
		if (listPuntuaciones.isEmpty()) {
			this.cantEstrellas = 0.0f;
			return 0.0f;
		}
		
		int suma = 0;
		
		for (Puntuacion p: listPuntuaciones) {
			suma += p.getEstrellas();
		}
		
		this.cantEstrellas = (float) suma / listPuntuaciones.size();
		return this.cantEstrellas;
	}
	
	public void agregarPuntuacion(Puntuacion p) {
		if (p != null) {
			this.listPuntuaciones.add(p);
			calcularPromedioEstrellas();
		}
	}
	
	public void eliminarPuntuacion(Puntuacion p) {
		this.listPuntuaciones.remove(p);
		calcularPromedioEstrellas();
	}
	
	public void agregarHabitacion(Habitacion h) {
		if (h != null) {
			this.listHabitaciones.add(h);
		}
	}
	
	public void eliminarHabitacion(Habitacion h) {
		this.listHabitaciones.remove(h);
	}
}
