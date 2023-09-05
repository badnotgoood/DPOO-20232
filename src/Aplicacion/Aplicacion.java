package Aplicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Procesamiento.Restaurante;

public class Aplicacion {

	private Restaurante restaurante;

	public static void main(String[] args) {
		
	}

	public void mostrarMenu() {

		System.out.println("\nBIENVENIDO\n");
		System.out.println("1. Ver menú\n");
		System.out.println("2. Hacer un pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("0. Salir de la aplicación\n");
	}

	public void ejecutarOpcion(int opcionSeleccionada) {
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion = Integer.parseInt(input("Selecciona una opción"));
				if (opcion == 1)
					verMenuRestaurante();
				else if (opcion == 2)
					hacerPedido();
				else if (opcion == 3)
					agregarElemento();
				else if (opcion == 0);{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;}				
				else {
					System.out.println("Por favor seleccione una opción válida.");}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	public void verMenuRestaurante() {
		
	}
	
	
		
	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
			} 
		catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
			}
		return null;
	}
}
