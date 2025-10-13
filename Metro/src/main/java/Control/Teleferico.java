/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author asus
 */
public class Teleferico implements SistemaTarifario{
     private Vehiculo vehiculo;

    public Teleferico() {
    }
    public Teleferico(String ruta) {
        this.vehiculo = new Vehiculo("Teleférico", ruta, 1000, 400, false);
    }

    public double calcularTarifa(int estaciones) throws Exception {
        return vehiculo.calcularTarifa(estaciones);
    }

    public void mostrarRuta() {
        System.out.println("Ruta de conexion veredal");
    }
}
