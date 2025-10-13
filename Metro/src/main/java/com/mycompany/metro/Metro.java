/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.metro;
import Control.BusArticulado;
import Control.SistemaTarifario;
import Control.Teleferico;
import Util.Lectura;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author asus
 */
public class Metro {
    public static void procesarViaje(SistemaTarifario vehiculo, int estaciones) {
        try {
            vehiculo.mostrarRuta(); 
            System.out.println(" Estaciones recorridas: " + estaciones);

            double tarifa = vehiculo.calcularTarifa(estaciones);
            NumberFormat formatoCOP = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            System.out.println(" Tarifa total: " + formatoCOP.format(tarifa));
            System.out.println("--------------------------\n");

        } catch (Exception e) {
            System.out.println("️ Error al procesar el viaje: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Lectura lector = new Lectura();
        SistemaTarifario bus = new BusArticulado("Ruta Troncal");
        SistemaTarifario teleferico = new Teleferico("Ruta de conexion veredal");

        int estacionesBus = lector.leerInt("Ingrese el numero de estaciones del Bus:");
        int estacionesTeleferico = lector.leerInt("Ingrese el numero de estaciones del Teleferico:");
        
        procesarViaje(bus, estacionesBus);
        procesarViaje(teleferico, estacionesTeleferico);
    }
}
