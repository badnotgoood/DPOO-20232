package Aplicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Procesamiento.Combo;
import Procesamiento.Ingrediente;
import Procesamiento.Producto;
import Procesamiento.ProductoMenu;
import Procesamiento.ProductoAjustado;
import Procesamiento.Restaurante;

public class Aplicacion {

	private Restaurante restaurante;

	public static void main(String[] args) {
	}

	public void mostrarMenu() {

		System.out.println("\nBIENVENIDO\n");
		System.out.println("1. Ver menú\n");
		System.out.println("2. Hacer un pedido\n");
		System.out.println("0. Salir de la aplicación\n");
	}

	public void ejecutarOpcion(int opcionSeleccionada) {
		boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenu();
				int opcion = Integer.parseInt(input("Selecciona una opción"));
				
				if (opcion == 1)
					verMenuRestaurante();
				
				else if (opcion == 2)
					hacerPedido();
				
				else if (opcion == 0) {
					System.out.println("Saliendo de la aplicación...");
					continuar = false;
				}
				
				else {
					System.out.println("Por favor seleccione una opción válida");
				}
			}

			catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de los números de las opciones");
			}
		}
	}

	public void verMenuRestaurante() {

	}

	public void hacerPedido() {

		System.out.println("\n\n -- Realiza tu pedido --");
		String direccionCliente = input("\n Dirección de entrega");
		String nombreCliente = input("\n Ingresa tu nombre");
		
		StringBuilder menuPedidos = new StringBuilder();
		menuPedidos.append("\n -- Menú --");
		menuPedidos.append("\n 1. Combos");
		menuPedidos.append("\n 2. Productos");
		menuPedidos.append("\n 3. Bebidas");
		
		menuPedidos.append("\n 0. Guardar Pedido");
		
		
		restaurante.inciarPedido(nombreCliente, direccionCliente);
		
		String displayMenuPedidos = menuPedidos.toString();
		
		do { 
		
		System.out.println(displayMenuPedidos);
		int opcionMenu = Integer.valueOf(input("\n\n Seleciona una opción"));
		
		switch(opcionMenu) {
			
			// Agregar un Combo
			case 1:
				
				//build menu combo
				StringBuilder menuCombos = new StringBuilder();
				int indexCombo = 0;
				for (Combo combo : restaurante.getCombos()) {
					indexCombo ++;
					menuCombos.append(" " + String.valueOf(indexCombo) + ". ");
					menuCombos.append(combo.getNombre() + "\n");
					menuCombos.append(" 0. Cancelar");
				}
				
				//select and add
				boolean continuarCombo = true;
				while (continuarCombo) {
					try {
						//display menu combo
						System.out.println(menuCombos.toString());
						int seleccion = Integer.parseInt(input("\n Selecciona una opción"));
						//cancel
						if (seleccion == 0) {
							continuarCombo = false;
						}
						//select combo
						else {
							Combo elCombo = restaurante.getCombos().get(seleccion-1);
							//confirmation
							String confirmAdd = input("\n ¿Agregar " + elCombo.getNombre() + " al pedido? (Y/N)");
							//yes
							if (confirmAdd.replaceAll(" ", "").equalsIgnoreCase("y")) {
								restaurante.getPedidoEnCurso().agregarProducto(elCombo);
								continuarCombo = false;
								System.out.println("\n\n " + elCombo.getNombre() + "agregado al pedido");
							}
							//no
							if (confirmAdd.replaceAll(" ", "").equalsIgnoreCase("n")) {
								System.out.println("\n\n " + elCombo.getNombre() + "no se agregó al pedido");
							}
						}					
					} catch (NumberFormatException e) {
							System.out.println("Debes seleccionar uno de los números de las opciones");}
				}
			break;
			
			// Agregar un Producto
			case 2:
				
				//build menu producto
				StringBuilder menuProductos = new StringBuilder();
				int indexProducto = 0;
				for (ProductoMenu producto : restaurante.getMenuBase()) {
					indexProducto ++;
					menuProductos.append(" " + String.valueOf(indexProducto) + ". ");
					menuProductos.append(producto.getNombre() + "\n");	
					menuCombos.append(" 0. Cancelar");
				}
			
				//select producto
				boolean continuarProducto = true;
				while (continuarProducto) {
					try {
						//display menu producto
						System.out.println(menuProductos.toString());
						int seleccionProducto = Integer.parseInt(input("\n Selecciona una opción"));
						//cancel
						if (seleccionProducto == 0) {
							continuarProducto = false;
						}
						//select producto
						else {
							ProductoMenu elProducto = restaurante.getMenuBase().get(seleccionProducto-1);
							Producto productoFinal = elProducto;
							boolean modificado = false;
		
							// Modificar un Producto
							String confirmMod = input("\n ¿Quieres personalizar tu(s)" + elProducto.getNombre() + " ? (Y/N)");
							boolean continuarMod = true;
							while (continuarMod) {
								try {
									//yes
									if (confirmMod.replaceAll(" ", "").equalsIgnoreCase("y")) {
										modificado = true;
										
										//build ingredientes menu
										StringBuilder menuIngredientes = new StringBuilder();
										int indexIngrediente = 0;
										for (Ingrediente ingrediente : restaurante.getIngredientes()) {
											indexIngrediente ++;
											menuIngredientes.append(" " + String.valueOf(indexProducto) + ". ");
											menuIngredientes.append(ingrediente.getNombre() + "\n");
											menuIngredientes.append(" 0. Cancelar");
										}
										
										//select ingrediente
										boolean continuarIngrediente = true;
										while (continuarIngrediente) {
											try { 
												System.out.println(menuIngredientes.toString());
												int seleccionIngrediente = Integer.parseInt(input("\n Selecciona una opción"));
												//cancel
												if (seleccionIngrediente == 0) {
													continuarIngrediente = false;
												}
												else {
													Ingrediente elIngrediente = restaurante.getIngredientes().get(seleccionIngrediente-1);
													//escoger acción
													String accionIngrediente = input("\n ¿Qué quieres hacer?\n 1. Agregar ingredientes\n 2. Eliminar ingredientes");
													
													ProductoAjustado elProductoAjustado = (elProducto instanceof ProductoMenu)
													? new ProductoAjustado(elProducto)
										            : elProductoAjustado;
										
													//agregar
													if (Integer.valueOf(accionIngrediente) == 1) {	
														elProductoAjustado.agregarIngrediente(elIngrediente);
													}
													//eliminar
													if (Integer.valueOf(accionIngrediente) == 2) { 
														elProductoAjustado.eliminarIngrediente(elIngrediente);
													}
													
													String procesoIngredientes = input("\n ¿Quieres agraegar/eliminar algo más? (Y/N)");
													if (procesoIngredientes.replaceAll(" ", "").equalsIgnoreCase("y")) {
														System.out.println(" Modificación exitosa");
														
													}
													if (procesoIngredientes.replaceAll(" ", "").equalsIgnoreCase("n")) {
														System.out.println(" Modificación guardada");
														continuarIngrediente = false;
														continuarMod = false;
														productoFinal = elProductoAjustado;
													}													
												
												}

											} catch (NumberFormatException e) {
												System.out.println("Debes seleccionar uno de los números de las opciones");}
										}
									}
									
									//no
									if (confirmMod.replaceAll(" ", "").equalsIgnoreCase("n")) {
										continuarMod = false;
									}
									
								} catch (NumberFormatException e) {
									System.out.println("Debes seleccionar uno de los números de las opciones");}
							}

							String confirmAdd = input("\n ¿Agregar " + productoFinal.getNombre() + " al pedido? (Y/N)");
							//yes
							if (confirmAdd.replaceAll(" ", "").equalsIgnoreCase("y")) {
								restaurante.getPedidoEnCurso().agregarProducto(productoFinal);
								continuarCombo = false;
								System.out.println("\n\n " + productoFinal.getNombre() + "agregado al pedido");
							}
							//no
							if (confirmAdd.replaceAll(" ", "").equalsIgnoreCase("n")) {
								System.out.println("\n\n " + productoFinal.getNombre() + "no se agregó al pedido");
							}
						}
						} catch (NumberFormatException e) {
							System.out.println("Debes seleccionar uno de los números de las opciones");}
		
				}
			break;
			
			case 0:
				if (restaurante.getPedidoEnCurso() == null) {
					System.out.println(" \nPrimero debes iniciar un pedido");
				}
				else {					
					System.out.println(" \nDisfruta por tu pedido")
				}
				break;
			} 			
		} while (opcionMenu != 0)
	}

	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
