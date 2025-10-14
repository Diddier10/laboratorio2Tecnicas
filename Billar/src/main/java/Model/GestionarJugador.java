/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class GestionarJugador {
    private ArrayList<Jugador> listaJugadores;
    
    public GestionarJugador() {
        listaJugadores = new ArrayList<>();
    }
    
    public void agregarJugador(Jugador jugador) {
        listaJugadores.add(jugador);
    }
    
    public ArrayList<Jugador> consultarJugadores() {
        return listaJugadores;
    }
    
    public boolean actualizarJugador(String nombre, int edad, int aniosExperiencia, double promedio) {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                jugador.setEdad(edad);
                jugador.setAniosExperiencia(aniosExperiencia);
                jugador.setPromedio(promedio);
                return true; 
            }
        }
        return false; 
    }
    
}
