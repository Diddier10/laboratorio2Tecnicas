/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
import Model.GestionarJugador;
import Model.Jugador;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
    


public class PruebaModelo {
    public static void main(String[] args) {
        // Crear el objeto que gestiona los jugadores
        GestionarJugador gestor = new GestionarJugador();

        // Agregar jugadores
        gestor.agregarJugador(new Jugador("Carlos", 25, 3, 8.7));
        gestor.agregarJugador(new Jugador("Maria", 30, 5, 9.2));
        gestor.agregarJugador(new Jugador("Luis", 22, 1, 7.5));

        // Consultar jugadores
        System.out.println(" Lista de jugadores registrados:");
        ArrayList<Jugador> lista = gestor.consultarJugadores();
        for (Jugador j : lista) {
            System.out.println(j);
        }

        // Actualizar jugador
        System.out.println(" Actualizando datos de Maria...");
        boolean actualizado = gestor.actualizarJugador("Maria", 31, 6, 9.5);

        if (actualizado) {
            System.out.println(" Jugador actualizado correctamente.\n");
        } else {
            System.out.println(" Jugador no encontrado.\n");
        }

        // Mostrar lista nuevamente
        System.out.println(" Lista actualizada:");
        for (Jugador j : gestor.consultarJugadores()) {
            System.out.println(j);
        }
    }
}
    

