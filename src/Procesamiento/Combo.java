package Procesamiento;

import java.util.ArrayList;

public class Combo implements Producto {

	private double descuento;

	private String nombreCombo;

	private ArrayList<Producto> itemsCombo;

	public Combo(String nombreCombo, double descuento) {
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
		
	}

	public void agregarItemACombo(Producto itemCombo) {
		itemsCombo.add(itemCombo);
	}

	@Override
	public int getPrecio() {
		int precio = 0;
		for (Producto itemCombo : itemsCombo) {
			precio += itemCombo.getPrecio();
		}
		int precioCombo = (int) (precio * this.descuento);
		return precioCombo;
	}

	@Override
	public String getNombre() {
		return this.nombreCombo;
	}

	@Override
	public String generarTextoFactura() {
		StringBuilder factura = new StringBuilder();
		
		String nombreCombo = getNombre();
		String precioCombo = String.valueOf(getPrecio());
		
		String format = "%-35s%-10s\n";
		
		if (precioCombo.length() == 4) {
			format = "%-36s%-10s\n"; 
		}
		else if (precioCombo.length() == 6) {
			format = "%-34s%-10s\n";
		}
		
		factura.append(String.format(format," 1 " + nombreCombo, precioCombo));
		
		for (Producto itemCombo : itemsCombo) {
			
			String nombreItem = itemCombo.getNombre();
			String precioItem = String.valueOf(itemCombo.getPrecio());
			
			String formatItem = "%-35s%-10s\n";
			
			if (precioItem.length() == 4) {
				formatItem = "%-36s%-10s\n"; 
			}
			else if (precioItem.length() == 6) {
				formatItem = "%-34s%-10s\n";
			}
			
			factura.append(String.format(formatItem,"    " + nombreItem, precioItem));
		}
		
		return factura.toString();
	}

}
