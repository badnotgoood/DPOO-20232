package Procesamiento;

public class ProductoMenu implements Producto {

	String nombre;

	int precioBase;

	ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public int getPrecio() {
		return this.precioBase;
	}

	@Override
	public String generarTextoFactura() {
		StringBuilder factura = new StringBuilder();
		String nombreProducto = getNombre();
		factura.append(nombreProducto).append("\n");
		int maxWidth = 24;
		
		String precioProducto = String.format("$%.2f", getPrecio());
		String productoln = String.format("%=" + (maxWidth + 3) + "s\n", nombreProducto, precioProducto);
		factura.append(productoln);
		return factura.toString();
	}
	
}
	



