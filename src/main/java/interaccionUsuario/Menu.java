package interaccionUsuario;

import contextoProblema.Libreria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	static Scanner teclado=new Scanner(System.in);

	public void menuPrincipal() {
		mostartMenuPrincipal();
		int opcion = leerOpcion();
		seleccionMenu(opcion);
	}

	public static void mostartMenuPrincipal(){
		System.out.println("--------------------MENÚ--------------------");
		System.out.println("( 1 )   Ingresar un nuevo libro al stock");
		System.out.println("( 2 )   Mostrar los libros en stock");
		System.out.println("( 3 )   Vender libro (elimina el libro del stock)");
		System.out.println("( 4 )   Salir");
		System.out.println("            Ingrese una opción:");
	}

	private void seleccionMenu(int op){
		switch (op) {
			case 1:
				Libreria.agregarNuevoLibro();
				menuPrincipal();
				break;
			case 2:
				Libreria.mostrarLibros();
				menuPrincipal();
				break;
			case 3:
				Libreria.venderLibro();
				menuPrincipal();
				break;
			case 4:
				System.out.println("Que tenga un buen día.");
				break;
			default:
				menuPrincipal();
				break;
		}
	}

	private static int leerOpcion(){
		int opcion=0;
		boolean flag = false;

		while (flag==false){
			opcion=leerInt(" la opcion que desee ejecutar.");
			if(opcion>=1 && opcion>=4){
				flag=true;
			}
		}

		return opcion;
	}

	private static int leerInt(String mensaje){
			int num;
			System.out.println("ingrese "+ mensaje);
			do{
				try{
					num=teclado.nextInt();
				}
				catch(InputMismatchException ime){
					System.out.println("error! solo puede ingresar números. \n vuelva a intentar");
					teclado.next();
					num=-1;
				}
			}
			while(num<0);
			return num;
	}
	public static String leerTitulo(){
		System.out.println("Ingrese el Titulo del libro:");
		return teclado.next();
	}

	public static String leerAutor(){
		System.out.println("Ingrese el autor del libro");
		return teclado.next();
	}

	public static int leerCodigoVenta(){
		return leerInt("ingrese el código de identificación del libro vendido.");
	}

	public static double leerPrecio(){
		return leerDouble("Ingrese el precio de venta del libro:");
	}

	private static double leerDouble(String txt) {
		System.out.println(txt);
		while (!teclado.hasNextDouble()) {
			System.out.println("Error! debe ingresar un número");
			teclado.nextLine();
		}
		double  number = teclado.nextDouble();
		return number;

	}

}