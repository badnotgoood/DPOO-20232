package Procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurante {

	private Pedido pedidoEnCurso;

	private ArrayList<Pedido> pedidos = new ArrayList<>();

	private ArrayList<Combo> combos = new ArrayList<>();

	private ArrayList<Ingrediente> ingredientes = new ArrayList<>();

	private ArrayList<ProductoMenu> menuBase = new ArrayList<>();

	public Restaurante() throws IOException {

		File archivoIngredientes = new File("./data/ingredientes.txt");
		File archivoMenu = new File("./data/menu.txt");
		File archivoCombos = new File("./data/combos.txt");
		cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	}

	public void inciarPedido(String nombreCliente, String direccionCliente) {
		Pedido elPedido = new Pedido(nombreCliente, direccionCliente);
		pedidoEnCurso = elPedido;

	}

	public void cerrarYGuardarPedido() {
		pedidos.add(elPedido);

	}

	public Pedido getPedidoEnCurso() {

		return pedidoEnCurso;
	}

	public ArrayList<ProductoMenu> getMenuBase() {

		return menuBase;
	}

	// EDIT
	public ArrayList<Ingrediente> getIngredientes() {

		return ingredientes;
	}

	// EDIT
	public ArrayList<Combo> getCombos() {

		return combos;
	}

	public ArrayList<Ingrediente> getIngredientes() {

		return ingredientes;
	}

	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos)
			throws IOException {

		cargarMenu(archivoMenu);
		cargarIngredientes(archivoIngredientes);
		cargarCombos(archivoCombos);
	}

	private void cargarIngredientes(File archivoIngredientes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String line = br.readLine();

		line = br.readLine();

		while (line != null) {
			String[] data = line.split(";");
			String nombreIngrediente = data[0];
			int costoAdicional = Integer.parseInt(data[1]);
			Ingrediente elIngrediente = new Ingrediente(nombreIngrediente, costoAdicional);
			ingredientes.add(elIngrediente);
			line = br.readLine();
		}

		br.close();
	}

	private void cargarMenu(File archivoMenu) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String line = br.readLine();

		line = br.readLine();
		while (line != null) {
			String[] data = line.split(";");
			String nombreProducto = data[0];
			int precioBase = Integer.parseInt(data[1]);
			ProductoMenu elProducto = new ProductoMenu(nombreProducto, precioBase);
			menuBase.add(elProducto);
			line = br.readLine();
		}

		br.close();
	}

	private void cargarCombos(File archivoCombos) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String line = br.readLine();

		while (line != null) {
			String[] data = line.split(";");
			String nombreCombo = data[0];
			double descuento = (Integer.parseInt(data[1].split("%")[0])) / 100;
			Combo elCombo = new Combo(nombreCombo, descuento);

			for (int i = 2; i < data.length; i++) {
				String nombreProductoCombo = data[i];
				for (Producto productoBase : menuBase) {
					String nombreProducto = productoBase.getNombre();
					if (nombreProducto == nombreProductoCombo) {
						elCombo.agregarItemACombo(productoBase);
					}
				}
			}
			combos.add(elCombo);
			line = br.readLine();
		}

		br.close();
	}
}
