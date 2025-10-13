/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import java.util.*;
/**
 *
 * @author asus
 */
public class Lectura {
        private Scanner entrada = new Scanner(System.in);

    public int leerInt(String mensaje) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.println(mensaje);
                String texto = entrada.nextLine();
                valor = Integer.parseInt(texto);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("️Ingresar un numero entero valido...");
            }
        }
        return valor;
    }

    public String leerString(String mensaje) {
        String texto = "";
        while (true) {
            System.out.println(mensaje);
            texto = entrada.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            } else {
                System.out.println("Por favor ingrese un texto valido (no deje el campo vacio).");
            }
        }
    }

    public float leerFloat(String mensaje) {
        float valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.println(mensaje);
                String texto = entrada.nextLine();
                valor = Float.parseFloat(texto);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("️Por favor ingrese un numero decimal valido (use punto, no coma).");
            }
        }
        return valor;
    }
    
}
