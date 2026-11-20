package com.gestionHotelera2.gestion;

public class Puntuacion {

    private Reserva reserva;
    private int valor;

    public Puntuacion(Reserva reserva, int valor) {
        if (reserva == null) {
            throw new IllegalArgumentException("La puntuación necesita una reserva");
        }
        this.reserva = reserva;
        setValor(valor);
    }

    public Reserva getReserva() { 
    	return reserva; 
    }

    public int getValor() { 
    	return valor; 
    	}
    
    public void setValor(int valor) {
        if (valor < 0 || valor > 5) {
            throw new IllegalArgumentException("El valor debe estar entre 0 y 5");
        }
        this.valor = valor;
    }
}