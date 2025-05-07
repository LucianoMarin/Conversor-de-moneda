package com.conversormonedas;

import com.conversormonedas.models.APIExchange;
import com.conversormonedas.models.Convertidor;
import com.conversormonedas.models.Moneda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.net.URI;
public class principal {

    public static void main(String[] args) throws IOException, InterruptedException {

        programa();

    }



    public static void programa() throws IOException, InterruptedException {

        try {
            Scanner input = new Scanner(System.in);

            Convertidor datosMoneda=new Convertidor();

            float cantidadDinero=0;




            String programa = """
                    BIENVENIDO AL PROGRAMA DE CONVERSION DE MONEDA, PORFAVOR SELECCIONE UNA OPCION:
                    1- DOLAR => PESO ARGENTINO
                    2- PESO ARGENTINO => DOLAR
                    3- DOLAR => REAL BRASILEÑO
                    4- REAL BRASILEÑO => DOLAR
                    5- DOLAR => PESO COLOMBIANO
                    6- PESO COLOMBIANO => DOLAR
                    7- SALIR
                    """;

            int ingresoMenu = 0;

            do {

                System.out.println(programa);
                ingresoMenu = input.nextInt();


                switch (ingresoMenu) {
                    case 1:
                        System.out.println("CONVERTIR DOLAR A PESO ARGENTINO");

                        cantidadDinero=input.nextFloat();
                        datosMoneda= (Convertidor) respuestaAPI("USD","ARS");
                        MensajeConversion(cantidadDinero,datosMoneda);

                        break
                        ;
                    case 2:
                        System.out.println("CONVERTIR PESO ARGENTINO A DOLAR");

                        cantidadDinero=input.nextFloat();
                        datosMoneda= (Convertidor) respuestaAPI("ARS","USD");
                        MensajeConversion(cantidadDinero,datosMoneda);

                        break
                        ;
                    case 3:
                        System.out.println("CONVERTIR DOLAR A REAL BRASILEÑO");

                        cantidadDinero=input.nextFloat();
                        datosMoneda= (Convertidor) respuestaAPI("USD","BRL");
                        MensajeConversion(cantidadDinero,datosMoneda);

                        break
                        ;
                    case 4:
                        System.out.println("CONVERTIR REAL BRASILEÑO A DOLAR");

                        cantidadDinero=input.nextFloat();
                        datosMoneda= (Convertidor) respuestaAPI("BRL","USD");
                        MensajeConversion(cantidadDinero,datosMoneda);
                        break
                        ;
                    case 5:
                        System.out.println("CONVERTIR DOLAR A PESO COLOMBIANO");

                        cantidadDinero=input.nextFloat();
                        datosMoneda= (Convertidor) respuestaAPI("USD","COP");
                        MensajeConversion(cantidadDinero,datosMoneda);
                        break
                        ;
                    case 6:
                        System.out.println("CONVERTIR PESO COLOMBIANO A DOLAR");
                        cantidadDinero=input.nextFloat();
                        datosMoneda= (Convertidor) respuestaAPI("COP","USD");
                        MensajeConversion(cantidadDinero,datosMoneda);
                        break
                        ;
                    case 7:
                        System.out.println("PROGRAMA FINALIZADO");
                        break
                        ;
                    default:
                        System.out.println("SELECCIONE OPCION VALIDA EN EL MENU!\n");
                        break
                        ;


                }

            } while (ingresoMenu != 7 );

        }catch(Exception ex){

            System.out.println("ERROR: SE CERRO PROGRAMA, SOLO SE ACEPTAN VALORES NUMERICOS! ");

        }


    }


    public static void MensajeConversion(float cantidadDinero, Convertidor datosMoneda ){

        System.out.println("Cambiar "+cantidadDinero+" "+datosMoneda.getNombreDivisaOrigen()+
                " => SON: "+datosMoneda.getMonedaConvertida()*cantidadDinero+""+datosMoneda.getNombreDivisaDestino()+"\n");

    }

    public static Moneda respuestaAPI(String monedaOrigen,String monedaDestino) throws IOException, InterruptedException {

        String apiKey="2ff7be96a37fcf0fbc6d9cde";
        String url="https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+monedaOrigen;




        HttpClient cliente=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse <String> response=cliente
                .send(request,HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DOTS).create();
        String respuesta = response.body();
        APIExchange datosMonedaOrigen = gson.fromJson(respuesta, APIExchange.class);

        Convertidor miConversion = new Convertidor(datosMonedaOrigen,monedaDestino);


        return miConversion;

    }



}


