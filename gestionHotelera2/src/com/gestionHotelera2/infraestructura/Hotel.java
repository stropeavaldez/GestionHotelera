package com.gestionHotelera2.infraestructura;

import java.util.ArrayList;
import java.util.List;

import com.gestionHotelera2.gestion.Puntuacion;
import com.gestionHotelera2.personas.Huesped;

public class Hotel {

    private static Hotel instancia;          
    private static int cantHotel = 0;        

    private String nombre;
    private String direccion;
    private int cantEstrellas;               
    private List<Puntuacion> listPuntuaciones = new ArrayList<>();
    private List<Habitacion> listHabitaciones = new ArrayList<>();
    private List<Huesped> listSuscriptores = new ArrayList<>();
   
    private boolean destruido = false;

    private Hotel(String nombre, String direccion, int cantEstrellas) {
        if (cantEstrellas < 0 || cantEstrellas > 5) {
            throw new IllegalArgumentException("Las estrellas deben estar entre 0 y 5");
        }
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantEstrellas = cantEstrellas;
        cantHotel++;                         
    }
    
    public static Hotel getInstancia(String nombre, String direccion, int cantEstrellas) {
        if (instancia == null) {
            instancia = new Hotel(nombre, direccion, cantEstrellas);
        }
        return instancia;
    }

    public static int getCantHotel() { 
    	return cantHotel; 
    	}

    public String getNombre() { 
    	return nombre; 
    	}
    
    public void setNombre(String nombre) {
    	this.nombre = nombre; 
    	}

    public String getDireccion() { 
    	return direccion; 
    	}
    
    public void setDireccion(String direccion) { 
    	this.direccion = direccion; 
    	}


    // Recalcula el promedio (0 a 5), actualiza cantEstrellas y lo devuelve
    public int calcularPromedioPuntuaciones() {
        
    	if (listPuntuaciones.isEmpty()) {
            return cantEstrellas;            
        }
        int suma = 0;
        
        for (Puntuacion p : listPuntuaciones) {
            suma += p.getValor();
        }
        cantEstrellas = Math.round((float) suma / listPuntuaciones.size());
        return cantEstrellas;
    }

    public void agregarPuntuacion(Puntuacion puntuacion) {
        verificarActivo();
        if (puntuacion != null && !listPuntuaciones.contains(puntuacion)) {
            listPuntuaciones.add(puntuacion);
            calcularPromedioPuntuaciones();  
        }
    }

    public List<Puntuacion> verPuntuaciones() {
        verificarActivo();
        return new ArrayList<>(listPuntuaciones);
    }

    // ---------------- Habitaciones ----------------

    public void agregarHabitacion(Habitacion habitacion) {
        verificarActivo();
        if (habitacion == null || buscarHabitacion(habitacion.getNumero()) != null) {
            throw new IllegalArgumentException("Habitación nula o con número ya existente");
        }
        listHabitaciones.add(habitacion);
    }

    // Busca la habitacion por numero (que no cambia) y actualiza tipo y precio
    public void modificarHabitacion(int numero, TipoHabitacion tipo, float precio) {
        verificarActivo();
        Habitacion h = buscarHabitacion(numero);
        if (h == null) {
            throw new IllegalArgumentException("No existe la habitación Nº " + numero);
        }
        h.setTipoHabitacion(tipo);
        h.setPrecio(precio);
    }

    public void eliminarHabitacion(Habitacion habitacion) {
        verificarActivo();
        if (listHabitaciones.size() <= 1) {
            throw new IllegalStateException("El hotel debe tener al menos una habitación");
        }
        listHabitaciones.remove(habitacion);
    }

    public List<Habitacion> verHabitaciones() {
        verificarActivo();
        return new ArrayList<>(listHabitaciones);
    }

    private Habitacion buscarHabitacion(int numero) {
        for (Habitacion h : listHabitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }

    public void suscribir(Huesped huesped) {
        verificarActivo();
        if (huesped != null && !listSuscriptores.contains(huesped)) {   
            listSuscriptores.add(huesped);
        }
    }

    public void desuscribir(Huesped huesped) {
        verificarActivo();
        listSuscriptores.remove(huesped);
    }

    public List<Huesped> verSuscriptores() {
        verificarActivo();
        return new ArrayList<>(listSuscriptores);
    }

    // Avisa a todos los suscriptores, EXCEPTO al huesped que deja la habitacion
    public void notificarHabitacionLiberada(Habitacion habitacion, Huesped huespedSaliente) {
        verificarActivo();
        for (Huesped suscriptor : listSuscriptores) {
            boolean esElSaliente = huespedSaliente != null
                    && suscriptor.getId() == huespedSaliente.getId(); 
            if (!esElSaliente) {
                suscriptor.recibirNotificacion(habitacion);
            }
        }
    }

    public void destruir() {
        verificarActivo();
        listHabitaciones.clear();
        listPuntuaciones.clear();
        listSuscriptores.clear();
        destruido = true;
        instancia = null;                    
        cantHotel--;
    }

    private void verificarActivo() {
        if (destruido) {
            throw new IllegalStateException("El hotel '" + nombre + "' fue destruido: ya no se puede operar con él");
        }
    }
}
