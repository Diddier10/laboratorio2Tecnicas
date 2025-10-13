/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author asus
 */
public class BusArticulado implements SistemaTarifario{
    Vehiculo vehiculo;

    public BusArticulado() {
    }
    
    public BusArticulado(String ruta) {
        this.vehiculo = new Vehiculo("Bus Articulado", ruta, 2950, 0, true);
    }

    public double calcularTarifa(int estaciones) throws Exception {
        return vehiculo.calcularTarifa(estaciones);
    }

    public void mostrarRuta() {
        System.out.println("Ruta troncal");
    }
}
           

