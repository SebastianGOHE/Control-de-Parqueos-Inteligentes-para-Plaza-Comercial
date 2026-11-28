import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Cliente {
    
    private ObjectInputStream Entrada;
    private ObjectOutputStream Salida;
    private Socket Cliente;
    private ReceptorMsj receptor;

    public Cliente(ReceptorMsj receptor, String host, int puerto) {

        this.receptor = receptor;

        try {
            Cliente = new Socket();
            Cliente.connect(new InetSocketAddress(host, puerto), 2000);

            Salida = new ObjectOutputStream(Cliente.getOutputStream());
            Salida.flush();

            Entrada = new ObjectInputStream(Cliente.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void escuchar() {
        try {
            while (true) {
                Msj msj = (Msj) Entrada.readObject();
                receptor.recibirMsj(msj);
            }
        } catch (Exception e) {
            System.out.println("LA CONEXION CON EL SERVIDOR HA FINALIZADO!");
        }
    }

    public void escucharServidor() {

        Thread hilo = new Thread(new Runnable() {
            public void run() {
                escuchar();
            }
        });
        hilo.start();
    }

    public synchronized void enviar(Msj msj) throws IOException {
        Salida.writeObject(msj);
        Salida.flush();
    }

    public void cerrar() {
        try {
            Cliente.close();
        } catch (IOException e) {
            IO.println("ERROR!");
        }
    }

}
