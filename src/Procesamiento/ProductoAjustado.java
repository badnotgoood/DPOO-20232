package Procesamiento;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {

	private ProductoMenu base;

	private ArrayList<Ingrediente> agregados = new ArrayList<>();

	private ArrayList<Ingrediente> eliminados = new ArrayList<>();

	public ProductoAjustado(ProductoMenu base) {

		this.base = base;
	}

	@Override
	public int getPrecio() {
		int precioAgregados = 0;
		for (Ingrediente extra : agregados) {
			precioAgregados += extra.getCostoAdicional();
		}
		return precioAgregados;
	}

	@Override
	public String getNombre() {
		return base.getNombre();
	}

	@Override
	public String generarTextoFactura() {

		StringBuilder factura = new StringBuilder();

		String nombreProducto = getNombre();
		factura.append(nombreProducto).append("\n");

		int maxWidth = 24;
		
	    String precioBaseProducto = String.format("$%.2f", base.getPrecio());
	    String productoln = String.format("%=" + (maxWidth + 3) + "s\n", nombreProducto, precioBaseProducto);
	    factura.append(productoln);

		if (agregados.isEmpty() != false) {
			for (Ingrediente ingrediente : agregados) {
				String nombreIngrediente = "+ " + ingrediente.getNombre();
				String precioIngrediente = String.format("%30s$%-5.2f\n", "", ingrediente.getCostoAdicional());
				String ingredienteln = String.format("%=" + (maxWidth + 3) + "s%s", nombreIngrediente, precioIngrediente);
				factura.append(ingredienteln);
			}
		}

		if (eliminados.isEmpty() != false) {
			for (Ingrediente ingrediente : eliminados) {
				String nombreIngrediente = "- " + ingrediente.getNombre();
				String precioIngrediente = String.format("%30s$0.00\n", "");
				String ingredienteln = String.format("%-" + (maxWidth + 3) + "s%s", nombreIngrediente, precioIngrediente);
				factura.append(ingredienteln);
			}
		}
		return factura.toString();
	}

}
