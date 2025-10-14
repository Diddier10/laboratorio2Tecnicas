package Control;

import Model.GestionarJugador;
import Model.Jugador;
import Vista.MenuPrincipal;
import Vista.VentanaAgregar;
import Vista.VentanaConsultar;
import Vista.VentanaActualizar;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    
    public ControladorSistema() {
        this.menuPrincipal = null;
        this.ventanaAgregar = null;
        this.ventanaConsultar = null;
        this.ventanaActualizar = null;
        this.gestionarJugador = new GestionarJugador();
    }

   
    private void configurarListeners() {
        menuPrincipal.getBtnAgregar().addActionListener(e -> mostrarVentanaAgregar());
        menuPrincipal.getBtnConsultar().addActionListener(e -> mostrarVentanaConsultar());
        menuPrincipal.getBtnActualizar().addActionListener(e -> mostrarVentanaActualizar());
        ventanaAgregar.getBtnGuardar().addActionListener(e -> flujoCreacionSwing());
        ventanaAgregar.getBtnVolver().addActionListener(e -> volverAlMenu(ventanaAgregar));
        ventanaConsultar.getBtnVolver().addActionListener(e -> volverAlMenu(ventanaConsultar));
        ventanaConsultar.getBtnActualizar().addActionListener(e -> flujoConsultaSwing());
        ventanaActualizar.getBtnGuardar().addActionListener(e -> flujoActualizacionSwing());
        ventanaActualizar.getBtnVolver().addActionListener(e -> volverAlMenu(ventanaActualizar));
    }

    public void iniciarAplicacionSwing() {
        menuPrincipal.setVisible(true);
    }

    private void flujoCreacionSwing() {
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

    private void flujoActualizacionSwing() {
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
                ventanaActualizar.mostrarError(" Valores inválidos");
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
            ventanaActualizar.mostrarError(" Error de formato en los datos");
        } catch (Exception e) {
            ventanaActualizar.mostrarError(" Error: " + e.getMessage());
        }
    }

    private void flujoConsultaSwing() {
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
        flujoConsultaSwing();
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

 
    public void iniciarAplicacionConsola() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Agregar jugador");
            System.out.println("2. Consultar jugadores");
            System.out.println("3. Actualizar jugador");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    flujoCreacionConsola(scanner);
                    break;
                case "2":
                    flujoConsultaConsola();
                    break;
                case "3":
                    flujoActualizacionConsola(scanner);
                    break;
                case "4":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private void flujoCreacionConsola(Scanner scanner) {
        System.out.println("\n--- Agregar Jugador ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Años de experiencia: ");
        int experiencia = Integer.parseInt(scanner.nextLine());
        System.out.print("Promedio: ");
        double promedio = Double.parseDouble(scanner.nextLine());

        if (edad <= 0 || experiencia < 0 || promedio < 0 || promedio > 5) {
            System.out.println(" Valores inválidos.");
            return;
        }

        Jugador nuevoJugador = new Jugador(nombre, edad, experiencia, promedio);
        gestionarJugador.agregarJugador(nuevoJugador);
        System.out.println(" Jugador agregado correctamente.");
    }

    private void flujoConsultaConsola() {
        System.out.println("\n--- Lista de Jugadores ---");
        ArrayList<Jugador> lista = gestionarJugador.consultarJugadores();
        if (lista.isEmpty()) {
            System.out.println("ℹ️ No hay jugadores registrados.");
        } else {
            for (Jugador j : lista) {
                System.out.println(j);
            }
        }
    }

    private void flujoActualizacionConsola(Scanner scanner) {
        System.out.println("\n--- Actualizar Jugador ---");
        System.out.print("Nombre a buscar: ");
        String nombreBuscar = scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Nueva experiencia: ");
        int experiencia = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuevo promedio: ");
        double promedio = Double.parseDouble(scanner.nextLine());

        boolean actualizado = gestionarJugador.actualizarJugador(nombreBuscar, edad, experiencia, promedio);
        if (actualizado) {
            System.out.println(" Jugador actualizado correctamente.");
        } else {
            System.out.println(" Jugador no encontrado.");
        }
    }
}