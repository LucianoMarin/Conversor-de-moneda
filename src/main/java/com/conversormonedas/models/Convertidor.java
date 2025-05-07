package com.conversormonedas.models;

public class Convertidor extends Moneda{

    private String nombreDivisaDestino;
    private double monedaConvertida;

    public Convertidor() {
    }

    public Convertidor(APIExchange datosMoneda, String nombreDivisaDestino) {
        super(datosMoneda);
        this.nombreDivisaDestino = nombreDivisaDestino;
        this.monedaConvertida = datosMoneda.conversion_rates().get(nombreDivisaDestino);
    }

    public String getNombreDivisaDestino() {
        return nombreDivisaDestino;
    }

    public void setNombreDivisaDestino(String nombreDivisaDestino) {
        this.nombreDivisaDestino = nombreDivisaDestino;
    }

    public double getMonedaConvertida() {
        return monedaConvertida;
    }

    public void setMonedaConvertida(double monedaConvertida) {
        this.monedaConvertida = monedaConvertida;
    }
}
