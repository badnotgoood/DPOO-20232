package Procesamiento;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {

	private ProductoMenu base;

	private ArrayList<Ingrediente> agregados = new ArrayList<>();

	private ArrayList<Ingrediente> eliminados = new ArrayList<>();

	public ProductoAjustado(ProductoMenu base) {

		this.base = base;
	}
	
	//EDIT
	public void agregarIngrediente(Ingrediente item) {
		this.agregados.add(item);
	}

	//EDIT
	public void eliminarIngrediente(Ingrediente item) {
		this.eliminados.add(item);
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
		String precioProducto = String.valueOf(getPrecio());

		String format = "%-35s%-10s\n";

		if (precioProducto.length() == 4) {
			format = "%-36s%-10s\n";
		} 
		else if (precioProducto.length() == 6) {
			format = "%-34s%-10s\n";
		}

		factura.append(String.format(format, " 1 " + nombreProducto, precioProducto));
		
		if (agregados.isEmpty() == false) { 
		
			for (Ingrediente agregado : agregados) { 
				
				String formatAgregado = "%-35s%-10s\n";
						
				String nombreAgregado = agregado.getNombre();
				String costoAgregado = String.valueOf(agregado.getCostoAdicional());
				
				if (precioProducto.length() == 4) {
					formatAgregado = "%-36s%-10s\n";
				}
				else if (precioProducto.length() == 6) {
					formatAgregado = "%-34s%-10s\n";
				}
				
				factura.append(String.format(formatAgregado,"    + " + nombreAgregado, costoAgregado));
			}
		}
				
		
		if (eliminados.isEmpty() == false) {
			
			for (Ingrediente eliminado : eliminados) { 
				
				String nombreEliminado = eliminado.getNombre();
				
				factura.append(String.format("%-39s%-10s\n","    - " + nombreEliminado,"0"));
			}
		}		
		
		return factura.toString();
	}

}
