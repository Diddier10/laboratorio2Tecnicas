/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.billar;


/**
 *
 * @author asus
 */
import Control.ControladorSistema;
import Vista.MenuPrincipal;
import Vista.VentanaAgregar;
import Vista.VentanaConsultar;
import Vista.VentanaActualizar;
public class Billar {

    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        VentanaAgregar ventanaAgregar = new VentanaAgregar();
        VentanaConsultar ventanaConsultar = new VentanaConsultar();
        VentanaActualizar ventanaActualizar = new VentanaActualizar();
        
        
        ControladorSistema controlador = new ControladorSistema(
            menuPrincipal,
            ventanaAgregar,
            ventanaConsultar,
            ventanaActualizar
        );
        controlador.iniciarAplicacion();
    }
}
         
