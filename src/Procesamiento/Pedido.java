package Procesamiento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {

	private static int numeroPedidos;

	private int idPedido;

	private String nombreCliente;

	private String direccionCliente;

	private ArrayList<Producto> itemsPedido = new ArrayList<>();

	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = numeroPedidos += 1;
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void agregarProducto(Producto nuevoItem) {
		ArrayList<Producto> itesmPedido = this.itemsPedido;
		itesmPedido.add(nuevoItem);
	}

	private int getPrecioNetoPedido() {
		int precioNeto = 0;
		for (Producto item : itemsPedido) {
			precioNeto += item.getPrecio();
		}
		return precioNeto;
	}

	private int getPrecioTotalPedido() {
		int precioNeto = getPrecioNetoPedido();
		int precioIVAPedido = getPrecioIVAPedido();
		int precioTotal = precioNeto + precioIVAPedido;
		return precioTotal;
	}

	private int getPrecioIVAPedido() {
		int precioNeto = getPrecioNetoPedido();
		int IVAPedido = (int) (precioNeto * 0.19);
		return IVAPedido;
	}

	private String generarTextoFactura() {
		    StringBuilder factura = new StringBuilder();

		    // Header
		    factura.append("======================================\n");
		    factura.append("Orden #: ").append(idPedido).append("\n");
		    factura.append("Cliente: ").append(nombreCliente).append("\n");
		    factura.append("Dirección: ").append(direccionCliente).append("\n");
		    factura.append("======================================\n");
		    
		    int centro = (24 - (">>ORDER<<".length())) / 2;
		    factura.append(String.format("%" + (">>PEDIDO<<".length() + centro) + "s", ">>PEDIDO<<")).append("\n");

		    for (Producto item : itemsPedido) {
		    	factura.append(item.generarTextoFactura());
		    	}
		
		    int maxWidth = 24;
		    factura.append(String.format("%-" + (maxWidth - 5) + "s$%.2f\n", "SUBTOTAL:", getPrecioNetoPedido()));
		    factura.append(String.format("%-" + (maxWidth - 3) + "s$%.2f\n", "IVA:", getPrecioIVAPedido()));
		    factura.append("============");
		    factura.append(String.format("%-" + (maxWidth - 5) + "s$%.2f\n", "TOTAL:", getPrecioTotalPedido()));

		    return factura.toString();
		}

	public void guardarFactura(File archivo) {
	
		try {
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(generarTextoFactura());
            bw.close();

            System.out.println("\nSu factura se ha guardado con éxito.\n");
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
}


