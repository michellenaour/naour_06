package datos;

import contextoProblema.Libro;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GestorJsonTest {
    private GestorJson gestor;
    private Libro libro;
    @Before
    public void setUp() throws Exception {
        GestorJson.vaciarLibreria();
        this.gestor = new GestorJson();
        this.libro= new Libro();

        this.libro.setTitulo("La Odisea");
        this.libro.setAutor("Homero");
        this.libro.setPrecio(9890);
        this.libro.setCodigo(1);
    }

    @Test
    public void testDecode() {
        Libro resultadoObtenido;
        try {

            GestorJson.agregarLibroArchivo(this.libro);
            ArrayList<String> listaLibros = GestorJson.vectorLineas();
            resultadoObtenido = GestorJson.decode(listaLibros, (listaLibros.size()-1));

            assertEquals(this.libro.toString(), resultadoObtenido.toString());

        } catch (IOException ex) {

            fail("No se pudo deserializar el .json");

        }
    }

    @Test
    public void borrarLibroArchivo() {
        ArrayList<String> listaLibros;

        try {

            listaLibros = GestorJson.vectorLineas();
            GestorJson.generarLibreria(listaLibros);
            GestorJson.borrarLibroArchivo(1);
            int resultadoObtenido = GestorJson.contarLineas();

            assertEquals(0,resultadoObtenido);

        } catch (IOException ex) {

            fail("No se pudo borrar el libro del .json");

        }
    }
}