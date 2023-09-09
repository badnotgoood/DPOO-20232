package Procesamiento;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
		    
		    LocalTime hora = java.time.LocalTime.now();
		    DateTimeFormatter formatHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		    
		    //Header
		    factura.append("\n\n\n Cliente: " + nombreCliente);
		    factura.append("\n Dirección: " + direccionCliente);
		    factura.append("\n\n ---------------------------------------");
		    factura.append("\n Orden No. 00" + numeroPedidos + "\t\t\t" + hora.format(formatHora)); 
		    factura.append("\n ---------------------------------------");
		    
		    //Items
		    factura.append(String.format("%-40s\n","\n\n ITEM"));
		    for (Producto itemPedido : itemsPedido) {
		    	factura.append(itemPedido.generarTextoFactura());
		    }
		    
		    
		    //Footer
		    factura.append("\n ---------------------------------------\n\n");
		    
		    //Neto
		    String formatNeto = "%-35s%-10s\n";
		    String precioNeto = String.valueOf(getPrecioNetoPedido());
		    if (precioNeto.length() == 4) {
				formatNeto = "%-36s%-10s\n"; 
			}
			else if (formatNeto.length() == 6) {
				formatNeto = "%-34s%-10s\n";
			}
		    factura.append(String.format(formatNeto," Subtotal", precioNeto));
		    
		    //IVA
		    String formatIVA = "%-35s%-10s\n";
		    String IVA = String.valueOf(getPrecioIVAPedido());
		    if (IVA.length() == 4) {
				formatIVA = "%-36s%-10s\n"; 
			}
			else if (IVA.length() == 6) {
				formatIVA = "%-34s%-10s\n";
			}
		    factura.append(String.format(formatIVA," IVA 19%", IVA));
		    
		    //Total
		    String formatTotal = "%-35s%-10s\n";
		    String precioTotal = String.valueOf(getPrecioTotalPedido());
		    if (precioTotal.length() == 4) {
				formatTotal = "%-36s%-10s\n"; 
			}
			else if (precioTotal.length() == 6) {
				formatTotal = "%-34s%-10s\n";
			}
		    factura.append(String.format(formatTotal," Total", precioTotal));
		    
		    factura.append(String.format("\n\n%-10s%-10s","","Gracias por tu compra"));
		    
		    return factura.toString();
	}

	public void guardarFactura(File archivo) {
	
		////////
    }
	
	
}


