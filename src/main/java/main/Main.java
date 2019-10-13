package main;

import datos.GestorJson;

import java.io.IOException;

public class Main {

    public static void main (String[]args) throws IOException {
        GestorJson.crearStockVacio();
        GestorJson.vaciarLibreria("libreria.json");
        interaccionUsuario.Menu.menuPrincipal();
    }
}