import java.io.PrintWriter;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;


public class RecaudacionDia {
    
    public static void GuardarArchivo(double total) throws IOException {
        FileWriter entrada = new FileWriter("recaudacion.txt", true);
        PrintWriter salida = new PrintWriter(entrada);

        salida.println(LocalDate.now() + " | RECAUDACION DEL DIA: RD$ " + String.format("%.2f", total));

        salida.close();
    }
}
