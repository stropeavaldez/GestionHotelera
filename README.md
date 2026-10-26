**CHECKLIST DE LOS EJERCICIOS**

*** 1. EJERCITACIÓN INTEGRADORA - DIAGRAMA DE CLASES ***
Diseñar el diagrama de clases con atributos, métodos relevantes y multiplicidad en las relaciones, e identificar herencia, agregación y/o composición en el siguiente sistema. 
Una cadena de hoteles desea desarrollar un sistema para administrar sus reservas y la gestión de las habitaciones. 
El sistema debe contemplar lo siguiente:

** PERSONAS **
Todas las personas tienen:
- NOMBRE
- APELLIDO
- DNI
- FECHA DE NACIMIENTO

	public class Persona {
		private String nombre;
		private String apellido;
		private String dni;
		private LocalDate fechaNac;
	}

Hay dos tipos de personas que interactúan con el sistema: 

** HUÉSPED **
Un huésped puede tener varias reservas.

	public class Huesped extends Persona {
		private List<Reserva> listReservas;
	}

** EMPLEADO **
- CARGO (Recepción o Limpieza)
- SALARIO

	public class Empleado extends Persona {
		private TipoCargo cargo;
		private float sueldo;
  	}
	
	public enum TipoCargo {
		RECEPCION,
		LIMPIEZA
	}

Un empleado de recepción gestiona cada reserva.

	public class Reserva {
		private Empleado empleadoRecepcion;
	}

** HOTEL **
Cada hotel tiene:
- NOMBRE
- DIRECCIÓN
- CANTIDAD DE ESTRELLAS

	public class Hotel {
		private String nombre;
		private String direccion;
		private float cantEstrellas;
  	}

Un hotel puede tener varias habitaciones (al menos una).

	private List<Habitacion> listHabitaciones;

** HABITACIÓN **
Las habitaciones tienen:
- NÚMERO
- TIPO (simple, doble, suite).
Cada tipo está vinculado a un precio por noche.

	public class Habitacion {
		private int numero;
		private TipoHabitacion tipo;
		private float precio;
	}

	public enum TipoHabitacion {
		SIMPLE(50000.0f),
		DOBLE(75000.0f),
		SUITE(120000.0f);
	}

** RESERVAS **
Una reserva está asociada a un huésped, a una habitación y a una fecha.

	public class Reserva {
		private int numReserva;
		private LocalDate fecha;
		private Huesped huesped;
		private Habitacion habitacion;
		private Empleado empleadoRecepcion;
		private EstadoReserva estado;
		private Puntuacion puntuacion;
	}

** PUNTUACIONES **
Dado que cada hotel tiene una cantidad de estrellas obtenidas, considerar que cada puntaje es aplicado una vez que finaliza la reserva.

// Permite registrar el puntaje e impactarlo en el Hotel solo si la reserva finalizó
	
	public boolean registrarPuntaje(Hotel hotel) {
		if (this.reserva != null && this.reserva.getEstadoReserva() == EstadoReserva.FINALIZADA) {
			if (hotel != null) {
				hotel.agregarPuntuacion(this);
			}
			System.out.println("Puntaje de " + estrellas + " estrellas registrado con éxito para la reserva N° " + reserva.getNumReserva());
			return true;
		} else {
			System.out.println("Error: No se puede registrar la puntuación. La reserva N° "
					+ (reserva != null ? reserva.getNumReserva() : "S/N")
					+ " aún no está FINALIZADA.");
			return false;
		}
	}

*** 2. ENUMS Y PAQUETES ***
En base al ejercicio integrador de la clase pasada (UML), crear un enum que sea aplicable a alguna de las clases. 
Luego actualizar el diagrama trabajado.
Por otra parte generar los paquetes correspondientes al diagrama de gestión hotelera.

** ENUMS **

	public enum TipoCargo {
		RECEPCION,
		LIMPIEZA
	}

	public enum TipoHabitacion {
		SIMPLE(50000.0f),
		DOBLE(75000.0f),
		SUITE(120000.0f);
	}

	public enum EstadoReserva {
		PENDIENTE,
		CONFIRMADA,
		FINALIZADA,
		CANCELADA;
	}

** PAQUETES **

	package com.gestionHotelera.gestion;
	package com.gestionHotelera.infraestructura;
	package com.gestionHotelera.personas;
	package com.gestionHotelera.test;

*** 3. ROLES ***
Especificar todas las relaciones de roles entre las distintas clases partícipes.
Luego, identificar las responsabilidades que no son necesarias dado que no aportan contexto. 

*** 4. NAVEGABILIDAD ***
Modificar tipo “Collection<ObjectType>" en el diagrama (si aplica) por "List" o "Set".
Indicar la navegabilidad en las clases partícipes del diagrama.

*** 5. GENERALIZACIÓN Y CLASES ABSTRACTAS ***
Generar el método abstracto “calcularEdad()”.
El resultado del método mencionado debe ser el atributo derivado “edad”.
Actualizar el diagrama de clases implementando el atributo derivado “edad” y el método “calcularEdad()”.

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
		
		public int calcularEdad() {
			if (getFechaNac() == null) {
				return 0;
			}
			return Period.between(getFechaNac(), LocalDate.now()).getYears();
		}
	}

Probar la implementación desde un main.

	int edadEmpleado = pr101.calcularEdad();
	int edadHuesped = ph101.calcularEdad();

*** 6. INTERFACES Y POLIMORFISMO ***
Agregar en el TP de gestión hotelera una interfaz utilizada por la clase “Persona”, en donde permita redefinir el cálculo de la edad a partir de la fecha de nacimiento (actividad mencionada en otra clase).

	public interface SerVivo {
		int calcularEdad();
	}
	
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
		
		@Override
		public int calcularEdad() {
			if (getFechaNac() == null) {
				return 0;
			}
			return Period.between(getFechaNac(), LocalDate.now()).getYears();
		}
	}

*** 7. AGREGACIÓN Y COMPOSICIÓN ***
Desarrollar en Java, retomando el diagrama de clases que se viene trabajando en anteriores clases (TP), las relaciones de agregación y composición existentes. 
Además, revisar si alguna de las relaciones especificadas en el diagrama corresponde a una asociación y no a un todo-parte.

** COMPOSICIÓN **

	// Hotel - Habitaciones
	private List<Habitacion> listHabitaciones;

** AGREGACIÓN **

	// Hotel - Puntuaciones
	private List<Puntuacion> listPuntuaciones;

** ASOCIACIÓN **

	public class Reserva {
		private Huesped huesped;
		private Habitacion habitacion;
		private Empleado empleadoRecepcion;
	}

*** 8. MODIFICADOR STATIC ***
En base al diagrama y código del ejercicio de gestión hotelera que se viene trabajando clase a clase, crear un atributo estático para representar la cantidad de hoteles de la cadena. 
Además, crear el método pertinente para conocer la cantidad de hoteles existentes en la cadena.

** ATRIBUTO ESTÁTICO **

	private static int cantHotel = 0;
	// Incremento automático del contador global
			cantHotel++;

** MÉTODO ESTÁTICO **

	public static int getCantHotel() {
		return cantHotel;
	}

*** 9. REUTILIZACIÓN, REFACTORIZACIÓN Y CODE SMELLS ***
Revisar en el código ya generado del TP de gestión hotelera si presenta algún Code Smell. 
En caso de existir, documentarlo y generar una solución de código.

** DUPLICATED CODE **
Método calcularEdad(). Se movió la implementación completa del método a la clase Persona.
	
	@Override
	public int calcularEdad() {
		if (getFechaNac() == null) {
			return 0;
		}
		return Period.between(getFechaNac(), LocalDate.now()).getYears();
	}
	
** PRIMITIVE OBSESSION **
Las fechas se manejaban con String. Ahora pasaron a LocalDate. 

** LAZY CLASS / DATA CLASS **
La clase Puntuación tenía únicamente getters y setters para la cantidad de estrellas, sin lógica. Se agregó el método “registrarPuntaje” con validación del estado de la reserva. 
	
	public boolean registrarPuntaje(Hotel hotel) {
		if (this.reserva != null && this.reserva.getEstadoReserva() == EstadoReserva.FINALIZADA) {
			if (hotel != null) {
				hotel.agregarPuntuacion(this);
			}
			System.out.println("Puntaje de " + estrellas + " estrellas registrado con éxito para la reserva N° " + reserva.getNumReserva());
			return true;
		} else {
			System.out.println("Error: No se puede registrar la puntuación. La reserva N° "
					+ (reserva != null ? reserva.getNumReserva() : "S/N")
					+ " aún no está FINALIZADA.");
			return false;
		}
	}

*** 10. SINGLETON ***
Continuando con el trabajo de la gestión hotelera, se pide: 
Supongamos que la cadena de hoteles solo desea que exista un único hotel. 
Entonces, aplicar el patrón Singleton al código de Java que han venido trabajando para que la cadena no permita más de un hotel.

	public class Hotel {
	
		// Atributo estático para la única instancia
		private static Hotel instancia;
		
		// Constructor PRIVADO para evitar la creación externa con 'new'
		private Hotel(String nombre, String direccion) {
			this.nombre = nombre;
			this.direccion = direccion;
			this.cantEstrellas = 0;
			this.listPuntuaciones = new ArrayList<>();
			this.listHabitaciones = new ArrayList<>();
		}
	
		// Método ESTÁTICO PÚBLICO para acceder a la instancia única (Singleton)
		public static Hotel getInstancia(String nombre, String direccion) {
			if (instancia == null) {
				instancia = new Hotel(nombre, direccion);
			}
			return instancia;
		}
		
		// Sobrecarga por si se quiere invocar sin parámetros una vez ya creada
		public static Hotel getInstancia() {
			if (instancia == null) {
				instancia = new Hotel("Hotel Central", "Av. Principal 100");
			}
			return instancia;
		}

	}

*** 11. BUILDER ***
Implementar el patrón Builder.

	public class Reserva {
		
		private int numReserva;
		private LocalDate fecha;
		private Huesped huesped;
		private Habitacion habitacion;
		private Empleado empleadoRecepcion;
		private EstadoReserva estado;
		private Puntuacion puntuacion;
		
		// Constructor PRIVADO: Solo puede ser invocado desde el Builder
		private Reserva(Builder builder) {
			this.numReserva = builder.numReserva;
			this.fecha = builder.fecha;
			this.huesped = builder.huesped;
			this.habitacion = builder.habitacion;
			this.empleadoRecepcion = builder.empleadoRecepcion;
			this.estado = builder.estado;
			this.puntuacion = builder.puntuacion;
			
			// Vinculación automática en la lista de reservas del huésped (1..*)
			if (this.huesped != null) {
				this.huesped.agregarReserva(this);
			}
		}
	}

*** 12. FACADE ***
Actividad opcional: 
Implementar una fachada en el trabajo de gestión hotelera para evitar que el cliente interactúe de forma directa con los métodos internos del sistema hotelero (por ejemplo, facade para gestionar reservas).
En el caso de realizar la implementación, ajusta el diagrama de clases contemplando la clase de fachada creada.

	public class HotelFacade {
	  
	   private Hotel hotel;
	   // Constructor público
	   public HotelFacade() {
	       // Obtiene la única instancia del Hotel
	       this.hotel = Hotel.getInstancia();
	   }
	   
	   // Facilita la creación y registro de una habitación en el hotel.
	   public Habitacion registrarHabitacionEnHotel(int numero, TipoHabitacion tipo, float precio) {
	       Habitacion habitacion = new Habitacion(numero, tipo, precio);
	       hotel.agregarHabitacion(habitacion);
	       System.out.println("[FACADE] Habitación N° " + numero + " (" + tipo + ") agregada exitosamente al hotel.");
	       return habitacion;
	   }
	   
	   // Centraliza y simplifica la creación de una Reserva utilizando internamente el patrón Builder.
	   public Reserva gestionarNuevaReserva(int numReserva, LocalDate fecha, Huesped huesped, Habitacion habitacion, Empleado empleado, EstadoReserva estado) {
	       Reserva reserva = new Reserva.Builder()
	               .setNumReserva(numReserva)
	               .setFecha(fecha)
	               .setHuesped(huesped)
	               .setHabitacion(habitacion)
	               .setEmpleadoRecepcion(empleado)
	               .setEstadoReserva(estado)
	               .build();
	      
	       System.out.println("[FACADE] Reserva N° " + numReserva + " procesada y vinculada al huésped con éxito.");
	       return reserva;
	   }
	}

*** 13. OBSERVER ***
Implementar el patrón visto para informar a los suscriptores del hotel: Cuando las habitaciones de hotel son liberadas (tipo de habilitación liberada – costo por noche). Validar que el huésped que deja la habitación no reciba la notificación.

	// MÉTODOS DEL PATRÓN OBSERVER
	
	public void suscribir(Huesped huesped) {
		if (huesped != null && !suscriptores.contains(huesped)) {
			suscriptores.add(huesped);
		}
	}
	
	public void desuscribir(Huesped huesped) {
		suscriptores.remove(huesped);
	}
	
	// Notifica a los suscriptores que una habitación fue liberada. Validar que el huésped que deja la habitación NO reciba la notificación.
	
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
	}

