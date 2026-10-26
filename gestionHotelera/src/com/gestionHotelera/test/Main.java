package com.gestionHotelera.test;

import java.time.LocalDate;

import com.gestionHotelera.gestion.*;
import com.gestionHotelera.infraestructura.*;
import com.gestionHotelera.personas.*;
import com.gestionHotelera.fachada.HotelFacade;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("=== CADENA DE HOTELES RIOS&TROPEA ===");
		System.out.println("Cantidad inicial de hoteles: " + Hotel.getCantHotel() + "\n");
		
		// 1. Instanciación del Hotel mediante Singleton
		Hotel h1 = Hotel.getInstancia("Grand Hotel", "Av. Corrientes 1234");
		
		// 2. Instanciación de la Fachada (con 'new')
		HotelFacade fachada = new HotelFacade();
		
		// Creación de Empleado y Huéspedes
		Empleado pr101 = new Empleado("Facundo", "Romano", "29290290", LocalDate.of(1985, 1, 11), TipoCargo.RECEPCION, 85000.0f);
		Huesped ph101 = new Huesped("Santiago", "Tropea", "39390801", LocalDate.of(1995, 11, 21));
		Huesped ph102 = new Huesped("Mariana", "Gomez", "40111222", LocalDate.of(1998, 5, 12));
		
		// 3. Uso de la Fachada para registrar la habitación
		Habitacion hab101 = fachada.registrarHabitacionEnHotel(101, TipoHabitacion.DOBLE, 25000.0f);

		// 4. Uso de la Fachada para gestionar la reserva (ocultando el Builder al Main)
		Reserva reserva1 = fachada.gestionarNuevaReserva(
				501, 
				LocalDate.now(), 
				ph101, 
				hab101, 
				pr101, 
				EstadoReserva.CONFIRMADA
		);
		
		System.out.println("\n=== PRUEBA PATRÓN OBSERVER (Notificaciones) ===");
		// Suscribimos a ambos huéspedes a las notificaciones del hotel mediante la fachada
		fachada.suscribirHuesped(ph101);
		fachada.suscribirHuesped(ph102);
		
		// Simulamos que la habitación 101 se libera y el huésped que se retira es ph101 (Santiago Tropea).
		// Validación esperada: Mariana Gomez recibirá la notificación, pero Santiago Tropea NO.
		fachada.liberarHabitacion(hab101, ph101);
		
		System.out.println("=== DATOS FINALES DESDE EL CLIENTE ===");
		System.out.println("Huésped: " + ph101.getNombre() + " " + ph101.getApellido());
		System.out.println("Total de reservas asociadas al huésped: " + ph101.getListReservas().size());
		System.out.println("Estado de la reserva centralizada: " + reserva1.getEstadoReserva());
	}
}