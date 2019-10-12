package interaccionUsuario;

import java.util.Scanner;

public class Menu {
	static Scanner teclado=new Scanner(System.in);

	public static void menuPrincipal() {
		mostartMenuPrincipal();
	}

	public static String leerTitulo(){
		System.out.println("Ingrese el Titulo del libro:");
		return teclado.next();
	}

	public static String leerAutor(){
		System.out.println("Ingrese el autor del libro");
		return teclado.next();
	}

	public static double leerPrecio(){
		return leerNro("Ingrese el precio de venta del libro:");
	}

	private static double leerNro(String txt) {
		System.out.println(txt);
		while (!teclado.hasNextDouble()) {
			System.out.println("Error! debe ingresar un número");
			teclado.nextLine();
		}
		double  number = teclado.nextDouble();
		return number;

	}

	public static void mostartMenuPrincipal(){
		System.out.println("--------------------MENÚ--------------------");
		System.out.println("( 1 )   Ingresar un nuevo libro al stock");
		System.out.println("( 2 )   Mostrar los libros en stock");
		System.out.println("( 3 )   Vender libro (elimina el libro del stock)");
		System.out.println("            Ingrese una opción:");
	}

}