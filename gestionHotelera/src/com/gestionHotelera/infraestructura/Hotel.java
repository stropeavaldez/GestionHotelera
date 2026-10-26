package com.gestionHotelera.infraestructura;

import java.util.ArrayList;
import java.util.List;

import com.gestionHotelera.gestion.Puntuacion;
import com.gestionHotelera.personas.Huesped; // Import necesario para los suscriptores

public class Hotel {
	private static Hotel instancia;

	private String nombre;
	private String direccion;
	private float cantEstrellas;
	private static int cantHotel = 0;
	
	private List<Puntuacion> listPuntuaciones;
	private List<Habitacion> listHabitaciones;
	
	// NUEVO: Lista para almacenar los suscriptores (Patrón Observer)
	private List<Huesped> suscriptores;

	private Hotel(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.cantEstrellas = 0;
		this.listPuntuaciones = new ArrayList<>();
		this.listHabitaciones = new ArrayList<>();
		this.suscriptores = new ArrayList<>(); // Inicializamos la lista de suscriptores
		
		cantHotel++;
	}

	public static Hotel getInstancia(String nombre, String direccion) {
		if (instancia == null) {
			instancia = new Hotel(nombre, direccion);
		}
		return instancia;
	}

	public static Hotel getInstancia() {
		if (instancia == null) {
			instancia = new Hotel("Hotel Central", "Av. Principal 100");
		}
		return instancia;
	}

	// =========================================================================
	// MÉTODOS DEL PATRÓN OBSERVER
	// =========================================================================
	
	public void suscribir(Huesped huesped) {
		if (huesped != null && !suscriptores.contains(huesped)) {
			suscriptores.add(huesped);
		}
	}

	public void desuscribir(Huesped huesped) {
		suscriptores.remove(huesped);
	}

	/**
	 * Notifica a los suscriptores que una habitación fue liberada.
	 * REQUISITO: Validar que el huésped que deja la habitación NO reciba la notificación.
	 */
	public void notificarHabitacionLiberada(Habitacion habitacion, Huesped huespedQueSale) {
		System.out.println("\n--- [NOTIFICACIÓN] Habitación liberada en " + nombre + " ---");
		for (Huesped suscriptor : suscriptores) {
			// Validación solicitada: si el suscriptor es distinto al huésped que sale, se le notifica
			if (!suscriptor.equals(huespedQueSale)) {
				System.out.println("-> Enviando aviso a " + suscriptor.getNombre() + " " + suscriptor.getApellido() + 
								   ": La habitación N° " + habitacion.getNumero() + 
								   " (" + habitacion.getTipo() + ") está disponible. Costo por noche: $" + habitacion.getPrecio());
			}
		}
		System.out.println("----------------------------------------------------------\n");
	}

	// --- RESTO DE TUS MÉTODOS EXISTENTES ---
	public static int getCantHotel() { return cantHotel; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getDireccion() { return direccion; }
	public void setDireccion(String direccion) { this.direccion = direccion; }
	public float getCantEstrellas() { return cantEstrellas; }
	public void setCantEstrellas(float cantEstrellas) { this.cantEstrellas = cantEstrellas; }
	public List<Puntuacion> getListPuntuaciones() { return listPuntuaciones; }
	public List<Habitacion> getListHabitaciones() { return listHabitaciones; }
	
	public float calcularPromedioEstrellas() {
		if (listPuntuaciones.isEmpty()) {
			this.cantEstrellas = 0.0f;
			return 0.0f;
		}
		int suma = 0;
		for (Puntuacion p : listPuntuaciones) {
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