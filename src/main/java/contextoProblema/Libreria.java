package contextoProblema;


import datos.GestorJson;
import interaccionUsuario.Menu;

import java.io.IOException;
import java.util.ArrayList;

public class Libreria {
    public static ArrayList<Libro> libros = new ArrayList<Libro>();
    static int cod=0;
    public Libreria(){}

    /**
     * llama al metodo crearNuevoLibro() y añade su retorno a libros
     * llama al metodo GestorJson.agregarLibroArchivo()
     *
     */
    public static void agregarNuevoLibro() throws IOException {
        Libro libroNuevo= crearNuevoLibro();
        libros.add(libroNuevo);
        GestorJson.agregarLibroArchivo(libroNuevo);
    }
    /**
     * llama a metodos para leer autor, titulo y precio, y para generar un nuevo codigo.
     * @return Libro nuevo libro creado con los dtos obtenido
     *
     */
    private static Libro crearNuevoLibro(){
        int codigo= crearNuevoId();
        String titulo= Menu.leerTitulo();
        String autor=Menu.leerAutor();
        double precio=Menu.leerPrecio();
        return new Libro(codigo,titulo,autor, precio);
    }
    /**
     * muestra los datos de todos los libros, que se obtienen a traves los metodos de la clase GestorJson contarLineas y vectorLineas
     */
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
    /**
     * Deserializa las lineas obtenidas de un .json y genera una Receta.
     * @param codigo el numero que ingreso el usuario del libro que desea vender.
     * @return si encuentra el libro con el codigo ingresado regresa el index del codigo, si no lo encuentra regresa -1
     */
    private static int buscarIndex(int codigo){
       int index =0;
        for(int i=0; i<libros.size(); i++){

            if(libros.get(i).getCodigo()==codigo){
                return i;
            }

        }
     return -1;
    }

    /**
     * crea un nuevo codigo de identificacion sumando 1 al codigo anterior
     * @return int codigo creado
     */
    private static int crearNuevoId(){
        cod++;
        return cod;
    }


}
