package datos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import contextoProblema.Libreria;
import contextoProblema.Libro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class GestorJson {


    public static void crearStockVacio(){
        if(new File("libreria.json").exists()){}
        else{
            try{
                Files.write(Paths.get("libreria.json"), new String().getBytes());
            }
            catch(IOException e) {}
        }
    }

    public static void llenarJSONArray(JSONArray array, Object obj) {
        array.add(obj);

    }

    public static JSONObject encode(int Id,String titulo, String autor,double precio){
        JSONObject obj = new JSONObject();
        obj.put("codigoId", Id);
        obj.put("Titulo", titulo);
        obj.put("Autor", autor);
        obj.put("Precio", precio);

        return obj;
    }

    public static void saveFile(JSONObject obj) throws IOException {

        String saltoLinea = System.getProperty("line.separator");

        ArrayList<String> lineas = vectorLineas();
        String textoViejo = "";

        for (int x = 0; x < lineas.size(); x++) {
            textoViejo += lineas.get(x) + saltoLinea;
        }

        try (FileWriter file = new FileWriter("recetario.json")) {

            file.write(textoViejo + obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static Libro decode(ArrayList<String> lineas, int n) {
        Libro l = new Libro();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(lineas.get(n));

            JSONObject jsonObject = (JSONObject) obj;
            int codigo = (int) jsonObject.get("codigo");
            String titulo = (String) jsonObject.get("titulo");
            String autor = (String) jsonObject.get("autor");
            double precio = (double ) jsonObject.get("precio");

            l.setCodigo(codigo);
            l.setTitulo(titulo);
            l.setAutor(autor);
            l.setPrecio(precio);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * Cuenta la cantidad de lineas dentro del archivo .json.
     *
     * @return int Cantidad de lineas del archivo .json.
     */

    public static int contarLineas() throws FileNotFoundException, IOException {
        int numLineas = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "libreria.json";
        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        while (linea != null) {
            numLineas = numLineas + 1;
            linea = reader.readLine();
        }
        return numLineas;
    }

    /**
     * Convierte el .json en texto plano.
     *
     * @return  ArrayList Contiene el contenido del .json, de manera que cada linea es un String en el ArrayList.
     *
     */

    public static ArrayList<String> vectorLineas() throws FileNotFoundException, IOException {

        int numLineas = 0;
        int contador = 0;
        String fichero = new File("").getAbsolutePath() + File.separator + "libreria.json";

        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        String linea = reader.readLine();

        numLineas = contarLineas();

        ArrayList<String> datos = new ArrayList<String>();

        while (linea != null && contador < numLineas) {
            datos.add(linea);
            linea = reader.readLine();
            contador++;
        }
        return datos;
    }

    /**
     * Genera Recetario con el contenido del archivo .json.
     *
     * @param lineas Contiene el contenido del .json, de manera que cada linea es un String en el ArrayList.
     * @return Recetario Recetario que contiene todas las recetas del .json.
     *
     */

    public static Libreria  generarLibreria(ArrayList<String> lineas) {

        Libreria  libreria = new Libreria();
        Libreria.libros.clear();

        for (int x = 0; x < lineas.size(); x++) {

            libreria.libros.add(decode(lineas, x));

        }
        return libreria;
    }

    /**
     * Añande una receta al archivo .json.
     *
     * @param libro Receta para ser agregada al .json.
     *
     */

    public static void agregarLibroArchivo(Libro libro) throws IOException {
        int codigo = libro.getCodigo();
        String titulo = libro.getTitulo();
        String autor = libro.getAutor();
        double precio = libro.getPrecio();
        saveFile(encode(codigo,titulo,autor,precio));
    }

    /**
     * Elimina una receta del .json, pero no afecta a las demás recetas.
     *
     * @param codigo Nombre de la receta a eliminar.
     *
     */

    public static void borrarLibroArchivo(int codigo) throws IOException{

        ArrayList<Libro> libreria = Libreria.libros;

        for(int i=0; i<libreria.size(); i++){

            if(libreria.get(i).getCodigo()==codigo){
                libreria.remove(i);
            }

        }

        vaciarLibreria("libreria.json");

        for(int i=0; i<libreria.size(); i++){

            agregarLibroArchivo(libreria.get(i));

        }

    }

    public static void vaciarLibreria(String dir){

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(dir));
            bw.write("");
            bw.close();
        }catch(IOException e){}

    }





}