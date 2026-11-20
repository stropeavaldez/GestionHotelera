package com.gestionHotelera2.infraestructura;

public class Habitacion {

    private int numero;                
    private TipoHabitacion tipo;
    private float precio;               

    public Habitacion(int numero, TipoHabitacion tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precioInicial(tipo);  
    }

    private float precioInicial(TipoHabitacion tipo) {
        if (tipo == TipoHabitacion.SIMPLE) {
            return 50000;
        } else if (tipo == TipoHabitacion.DOBLE) {
            return 80000;
        }
        return 150000;                          
    }

    public int getNumero() { 
    	return numero; 
    	}
    
    public void setNumero(int numero) {
    	this.numero = numero;
    }

    public TipoHabitacion getTipoHabitacion() { 
    	return tipo; 
    	}
    public void setTipoHabitacion(TipoHabitacion tipo) { 
    	this.tipo = tipo; 
    	}

    public float getPrecio() { 
    	return precio; 
    	}
    
    public void setPrecio(float precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Habitación Nº " + numero + " (" + tipo + ", $" + precio + " por noche)";
    }
}
