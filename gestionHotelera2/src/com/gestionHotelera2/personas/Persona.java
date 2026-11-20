package com.gestionHotelera2.personas;

import java.time.LocalDate;
import java.time.Period;

public abstract class Persona implements SerVivo {

    private static int contador = 0; 

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNac;
    private int edad;                  

    public Persona(String nombre, String apellido, String dni, LocalDate fechaNac) {
        this.id = ++contador;          
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        asignarFechaNac(fechaNac);
    }

    private void asignarFechaNac(LocalDate fechaNac) {
        if (fechaNac == null || fechaNac.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula ni futura");
        }
        this.fechaNac = fechaNac;
        calcularEdad();
    }

    @Override
    public int calcularEdad() {
        this.edad = Period.between(fechaNac, LocalDate.now()).getYears();
        return this.edad;
    }

    public int getId() { return id; }

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
    	asignarFechaNac(fechaNac); 
    	}

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
