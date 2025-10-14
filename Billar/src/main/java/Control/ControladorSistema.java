package Control;

import Model.GestionarJugador;
import Model.Jugador;
import Vista.MenuPrincipal;
import Vista.VentanaAgregar;
import Vista.VentanaConsultar;
import Vista.VentanaActualizar;
import javax.swing.*;
import java.util.ArrayList;


public class ControladorSistema {
    private final GestionarJugador gestionarJugador;
    private final MenuPrincipal menuPrincipal;
    private final VentanaAgregar ventanaAgregar;
    private final VentanaConsultar ventanaConsultar;
    private final VentanaActualizar ventanaActualizar;

    public ControladorSistema(MenuPrincipal menuPrincipal,
                             VentanaAgregar ventanaAgregar,
                             VentanaConsultar ventanaConsultar,
                             VentanaActualizar ventanaActualizar) {

        this.menuPrincipal = menuPrincipal;
        this.ventanaAgregar = ventanaAgregar;
        this.ventanaConsultar = ventanaConsultar;
        this.ventanaActualizar = ventanaActualizar;

        this.gestionarJugador = new GestionarJugador();

        configurarListeners();
    }

    
    private void configurarListeners() {
        menuPrincipal.getBtnAgregar().addActionListener(e -> mostrarVentanaAgregar());
        menuPrincipal.getBtnConsultar().addActionListener(e -> mostrarVentanaConsultar());
        menuPrincipal.getBtnActualizar().addActionListener(e -> mostrarVentanaActualizar());
        ventanaAgregar.getBtnGuardar().addActionListener(e -> {
            flujoCreacion();
        });
        ventanaAgregar.getBtnVolver().addActionListener(e -> volverAlMenu(ventanaAgregar));
        ventanaConsultar.getBtnVolver().addActionListener(e -> volverAlMenu(ventanaConsultar));
        ventanaConsultar.getBtnActualizar().addActionListener(e -> flujoConsulta());
        ventanaActualizar.getBtnGuardar().addActionListener(e -> {
            flujoActualizacion();
        });
        ventanaActualizar.getBtnVolver().addActionListener(e -> volverAlMenu(ventanaActualizar));
    }

    
    public void iniciarAplicacion() {
        menuPrincipal.setVisible(true);
    }

    private void flujoCreacion() {
        try {
            String nombre = ventanaAgregar.getTxtNombre().getText().trim();
            String edadStr = ventanaAgregar.getTxtEdad().getText().trim();
            String experienciaStr = ventanaAgregar.getTxtExperiencia().getText().trim();
            String promedioStr = ventanaAgregar.getTxtPromedio().getText().trim();

            if (nombre.isEmpty() || edadStr.isEmpty() || experienciaStr.isEmpty() || promedioStr.isEmpty()) {
                ventanaAgregar.mostrarError(" Todos los campos son obligatorios");
                return;
            }

            int edad = Integer.parseInt(edadStr);
            int experiencia = Integer.parseInt(experienciaStr);
            double promedio = Double.parseDouble(promedioStr);

            if (edad <= 0 || experiencia < 0 || promedio < 0 || promedio > 5) {
                ventanaAgregar.mostrarError(" Valores inválidos:\n- Edad > 0\n- Experiencia >= 0\n- Promedio entre 0 y 5");
                return;
            }

            Jugador nuevoJugador = new Jugador(nombre, edad, experiencia, promedio);
            gestionarJugador.agregarJugador(nuevoJugador);

            ventanaAgregar.mostrarExito(" Jugador agregado correctamente");
            limpiarCamposAgregar();
        } catch (NumberFormatException e) {
            ventanaAgregar.mostrarError(" Error de formato:\n- Edad y Experiencia: números enteros\n- Promedio: número decimal");
        } catch (Exception e) {
            ventanaAgregar.mostrarError(" Error inesperado: " + e.getMessage());
        }
    }

    
    private void flujoActualizacion() {
        try {
            String nombreBuscar = ventanaActualizar.getTxtNombreBuscar().getText().trim();
            if (nombreBuscar.isEmpty()) {
                ventanaActualizar.mostrarError("️ Ingrese el nombre del jugador a actualizar");
                return;
            }

            String nombre = ventanaActualizar.getTxtNombreActualizado().getText().trim();
            String edadStr = ventanaActualizar.getTxtEdadActualizada().getText().trim();
            String experienciaStr = ventanaActualizar.getTxtExperienciaActualizar().getText().trim();
            String promedioStr = ventanaActualizar.getTxtPromedioActualizar().getText().trim();

            if (nombre.isEmpty() || edadStr.isEmpty() || experienciaStr.isEmpty() || promedioStr.isEmpty()) {
                ventanaActualizar.mostrarError("️ Todos los campos son obligatorios");
                return;
            }

            int edad = Integer.parseInt(edadStr);
            int experiencia = Integer.parseInt(experienciaStr);
            double promedio = Double.parseDouble(promedioStr);

            if (edad <= 0 || experiencia < 0 || promedio < 0 || promedio > 5) {
                ventanaActualizar.mostrarError(" Valores inválidos:\n- Edad > 0\n- Experiencia >= 0\n- Promedio entre 0 y 5");
                return;
            }

            boolean actualizado = gestionarJugador.actualizarJugador(nombreBuscar, edad, experiencia, promedio);
            if (actualizado) {
                ventanaActualizar.mostrarExito(" Jugador actualizado correctamente");
                limpiarCamposActualizar();
            } else {
                ventanaActualizar.mostrarError(" Jugador no encontrado: " + nombreBuscar);
            }
        } catch (NumberFormatException e) {
            ventanaActualizar.mostrarError(" Error de formato:\n- Edad y Experiencia: números enteros\n- Promedio: número decimal");
        } catch (Exception e) {
            ventanaActualizar.mostrarError(" Error inesperado: " + e.getMessage());
        }
    }

    private void flujoConsulta() {
        ArrayList<Jugador> listaJugadores = gestionarJugador.consultarJugadores();
        ventanaConsultar.mostrarListaCompleta(listaJugadores);

        if (listaJugadores.isEmpty()) {
            ventanaConsultar.mostrarError("️ No hay jugadores registrados.");
        } else {
            ventanaConsultar.mostrarExito(" Lista de jugadores actualizada.");
        }
    }

    private void mostrarVentanaAgregar() {
        limpiarCamposAgregar();
        menuPrincipal.setVisible(false);
        ventanaAgregar.setVisible(true);
    }

    private void mostrarVentanaConsultar() {
        flujoConsulta();
        menuPrincipal.setVisible(false);
        ventanaConsultar.setVisible(true);
    }
    
    private void mostrarVentanaActualizar() {
        limpiarCamposActualizar();
        menuPrincipal.setVisible(false);
        ventanaActualizar.setVisible(true);
    }

    private void volverAlMenu(JFrame ventana) {
        ventana.setVisible(false);
        menuPrincipal.setVisible(true);
    }

    private void limpiarCamposAgregar() {
        ventanaAgregar.getTxtNombre().setText("");
        ventanaAgregar.getTxtEdad().setText("");
        ventanaAgregar.getTxtExperiencia().setText("");
        ventanaAgregar.getTxtPromedio().setText("");
    }
    
    private void limpiarCamposActualizar() {
        ventanaActualizar.getTxtNombreBuscar().setText("");
        ventanaActualizar.getTxtNombreActualizado().setText("");
        ventanaActualizar.getTxtEdadActualizada().setText("");
        ventanaActualizar.getTxtExperienciaActualizar().setText("");
        ventanaActualizar.getTxtPromedioActualizar().setText("");
    }
}