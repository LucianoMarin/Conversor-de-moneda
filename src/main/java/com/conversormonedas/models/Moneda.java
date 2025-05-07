package com.conversormonedas.models;

public class Moneda {

    String nombreDivisaOrigen;
    double valor;


    public Moneda() {
    }


    public Moneda(String nombreDivisaOrigen,double valor) {
        this.nombreDivisaOrigen = nombreDivisaOrigen;
        this.valor=valor;
    }


    public Moneda(APIExchange datosMoneda) {


        this.nombreDivisaOrigen = datosMoneda.base_code();
        this.valor= datosMoneda.conversion_rates().get(nombreDivisaOrigen);

    }

    public String getNombreDivisaOrigen() {
        return nombreDivisaOrigen;
    }

    public void setNombreDivisaOrigen(String nombre_divisa) {
        this.nombreDivisaOrigen = nombre_divisa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }



}
