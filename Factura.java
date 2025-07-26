import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {
    private Cliente cliente;
    private Servicio[] servicios;
    private double total;
    private Date fecha;

    // Constructor
    public Factura(Cliente cliente, Servicio[] servicios) {
        this.cliente = cliente;
        this.servicios = servicios;
        this.fecha = new Date();
        calcularTotal();
    }

    // Método para calcular el total de la factura
    private void calcularTotal() {
        for (Servicio servicio : servicios) {
            total += servicio.getPrecio();
        }
    }

    // Generar reporte HTML
    public String generarReporteHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h1>Factura</h1>");
        html.append("<p>Cliente: ").append(cliente.getNombre()).append("</p>");
        html.append("<p>Placa: ").append(cliente.getPlacaVehiculo()).append("</p>");
        html.append("<p>Fecha: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(fecha)).append("</p>");
        html.append("<ul>");
        for (Servicio servicio : servicios) {
            html.append("<li>").append(servicio.toString()).append("</li>");
        }
        html.append("</ul>");
        html.append("<p>Total: S/").append(total).append("</p>");
        html.append("</body></html>");
        return html.toString();
    }

    // Generar solo las filas de datos para el CSV
    public String generarDatosCSV() {
        StringBuilder csv = new StringBuilder();
        for (Servicio servicio : servicios) {
            csv.append(cliente.getNombre()).append(",")
               .append(cliente.getPlacaVehiculo()).append(",")
               .append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(fecha)).append(",")
               .append(servicio.getNombre()).append(",")
               .append(servicio.getPrecio()).append("\n");
        }
        // Agregar fila para el total
        csv.append(", , ,Total,").append(total).append("\n");
        return csv.toString();
    }

    @Override
    public String toString() {
        return "Factura para " + cliente.getNombre() + " con total: S/" + total;
    }
}
