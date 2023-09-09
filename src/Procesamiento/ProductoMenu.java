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
		String precioProducto = String.valueOf(getPrecio());

		String format = "%-35s%-10s\n";

		if (precioProducto.length() == 4) {
			format = "%-36s%-10s\n";
		}
		else if (precioProducto.length() == 6) {
			format = "%-34s%-10s\n";
		}

		factura.append(String.format(format, " 1 " + nombreProducto, precioProducto));

		return factura.toString();

	}

}
