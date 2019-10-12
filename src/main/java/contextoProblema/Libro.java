package contextoProblema;

public class Libro {

    private int codigo;
    private String titulo;
    private String autor;
    private double precio;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }



    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    public Libro(){
        this.codigo=0;
        this.autor="";
        this.titulo="";
        this.precio=0;
    }
    public Libro(int codigo, String titulo,String autor, double precio){
        this.codigo=codigo;
        this.autor=autor;
        this.titulo=titulo;
        this.precio=precio;
    }

    public String toString(){
        String saltoLinea = System.getProperty("line.separator");
        return "codigo: " + codigo + saltoLinea + "Titulo: " +titulo+ saltoLinea + "Autor: " + autor + saltoLinea + "precio:" + precio;
    }
}
