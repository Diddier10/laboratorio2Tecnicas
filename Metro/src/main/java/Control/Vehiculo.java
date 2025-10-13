/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author asus
 */
public class Vehiculo {
    private String tipo;
    private String ruta;
    private double tarifaBase;
    private double tarifaPorEstacion;
    private boolean esFija;
    
    public Vehiculo(String tipo, String ruta, double tarifaBase, double tarifaPorEstacion, boolean esFija) {
        if (tarifaBase < 0 || tarifaPorEstacion < 0) {
            throw new IllegalArgumentException("Las tarifas no pueden ser negativas.");
        }
        if (ruta == null || ruta.isEmpty()) {
            throw new IllegalArgumentException("La ruta no puede estar vacía.");
        }
        this.tipo = tipo;
        this.ruta = ruta;
        this.tarifaBase = tarifaBase;
        this.tarifaPorEstacion = tarifaPorEstacion;
        this.esFija = esFija;
    }

    public boolean isEsFija() {
        return esFija;
    }

    public void setEsFija(boolean esFija) {
        this.esFija = esFija;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public double getTarifaPorEstacion() {
        return tarifaPorEstacion;
    }

    public void setTarifaPorEstacion(double tarifaPorEstacion) {
        this.tarifaPorEstacion = tarifaPorEstacion;
    }
    
    public double calcularTarifa(int estaciones) throws Exception {
        if (estaciones <= 0) {
            throw new Exception("El número de estaciones debe ser mayor que cero.");
        }

        if (esFija) {
            return tarifaBase;
        } else {
            return tarifaBase + tarifaPorEstacion * estaciones; 
        }
    }

    public void mostrarRuta() {
        System.out.println(" Tipo de vehículo: " + tipo);
        System.out.println(" Ruta: " + ruta);
    }   
}
