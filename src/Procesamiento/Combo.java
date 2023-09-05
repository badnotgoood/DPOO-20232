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
		factura.append(nombreCombo).append("\n");
		int maxWidth = 24;
		
		String precioCombo = String.format("$%.2f", getPrecio());
		String Comboln = String.format("%=" + (maxWidth + 3) + "s\n", nombreCombo, precioCombo);
		factura.append(Comboln);

		for (Producto item : itemsCombo) {
			String nombreItem = "+ " + item.getNombre();
			String precioItem = String.format("%30s$%-5.2f\n", "", (item.getPrecio()*this.descuento));
			String itemln = String.format("%=" + (maxWidth + 3) + "s\n", nombreItem, precioItem);
			factura.append(itemln);
		}
		return factura.toString();
	}

}
