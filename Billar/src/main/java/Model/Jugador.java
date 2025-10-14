/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
public class Jugador {
    private String nombre;
    private int edad;
    private int aniosExperiencia;
    private double promedio;

    public Jugador() {
    }

    public Jugador(String nombre, int edad, int aniosExperiencia, double promedio) {
        this.nombre = nombre;
        this.edad = edad;
        this.aniosExperiencia = aniosExperiencia;
        this.promedio = promedio;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("nombre=").append(nombre);
        sb.append(", edad=").append(edad);
        sb.append(", aniosExperiencia=").append(aniosExperiencia);
        sb.append(", promedio=").append(promedio);
        sb.append('}');
        return sb.toString();
    }
    
    
}
