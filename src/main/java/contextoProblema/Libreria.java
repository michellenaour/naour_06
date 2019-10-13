package contextoProblema;


import datos.GestorJson;
import interaccionUsuario.Menu;

import java.io.IOException;
import java.util.ArrayList;

public class Libreria {
    public static ArrayList<Libro> libros = new ArrayList<Libro>();
    static int cod=0;
    public Libreria(){}

    public static void crearLibreri(){

    }


    public static void agregarNuevoLibro() throws IOException {
        int codigo= crearNuevoId();
        String titulo= Menu.leerTitulo();
        String autor=Menu.leerAutor();
        double precio=Menu.leerPrecio();
        Libro libroNuevo = new Libro(codigo,titulo,autor, precio);
        libros.add(libroNuevo);
        GestorJson.agregarLibroArchivo(libroNuevo);
    }

    public static void mostrarLibros() throws IOException {
        int lineas= GestorJson.contarLineas();
        ArrayList<String> librosGuardados= GestorJson.vectorLineas();
        for(int i =0;i<librosGuardados.size();i++){
            System.out.println(librosGuardados.get(i));
        }
    }

    public static void venderLibro() throws IOException {
        int codigo= Menu.leerCodigoVenta();
        int index=buscarIndex(codigo);
        if (index>=0){
            libros.remove(index);
            GestorJson.borrarLibroArchivo(codigo);}
        else {System.out.println("no se encontro un libro con el código:"+codigo);}
    }

    private static int buscarIndex(int codigo){
       int index =0;
        for(int i=0; i<libros.size(); i++){

            if(libros.get(i).getCodigo()==codigo){
                return i;
            }

        }
     return -1;
    }

    private static int crearNuevoId(){
        cod++;
        return cod;
    }


}
