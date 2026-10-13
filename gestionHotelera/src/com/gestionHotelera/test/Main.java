package com.gestionHotelera.test;

import java.time.LocalDate;

import com.gestionHotelera.gestion.*;
import com.gestionHotelera.infraestructura.*;
import com.gestionHotelera.personas.*;

public class Main {
	
	public static void main(String[] args) {
		
		// Verificación inicial de hoteles en la cadena
		System.out.println("=== CADENA DE HOTELES RIOS&TROPEA ===");
		System.out.println("Cantidad inicial de hoteles: " + Hotel.getCantHotel() + "\n");
		
		// Instanciación de hoteles
		Hotel h1 = new Hotel("Grand Hotel", "Av. Corrientes 1234");
		Hotel h2 = new Hotel("Hotel Playa", "Costanera 500");
		
		System.out.println("Cantidad total de hoteles tras la creación: " + Hotel.getCantHotel() + "\n");
		
		// Creación de Empleado y Huésped
		Empleado pr101 = new Empleado("Facundo", "Romano", "29290290", LocalDate.of(1985, 1, 11), TipoCargo.RECEPCION, 85000.0f);
		Huesped ph101 = new Huesped("Santiago", "Tropea", "39390801", LocalDate.of(1995, 11, 21), 1);
		
		int edadEmpleado = pr101.calcularEdad();
		int edadHuesped = ph101.calcularEdad();
		
		System.out.println("=== DATOS EMPLEADO ===");
		System.out.println("Nombre: " + pr101.getNombre() + " " + pr101.getApellido());
		System.out.println("Cargo: " + pr101.getCargo());
		System.out.println("Edad: " + edadEmpleado + " años\n");
		
		System.out.println("=== DATOS HUESPED ===");
		System.out.println("Nombre: " + ph101.getNombre() + " " + ph101.getApellido());
		System.out.println("Reserva: " + ph101.getNumReserva());
		System.out.println("Edad: " + edadHuesped + " años");
		
	}
	
}