import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.time.Duration;
import java.time.LocalDateTime;

import java.io.Serializable;
import java.util.UUID;

import java.util.*;

abstract class Vehiculo {
    private String placa;
    private String numeroTicket;

    public Vehiculo(String placa, String numeroTicket){
        this.placa = placa;
        this.numeroTicket = numeroTicket;
    }

    public String getPlaca(){
        return placa;
    }

    public String getNumeroTicket(){
        return numeroTicket;
    }

    public abstract double calcularTarifa(double horas);
}

class Moto extends Vehiculo {

    private double montoBase = 100.0;
    private double precioHora = 50.0;

    public Moto(String placa, String numeroTicket){
        super(placa, numeroTicket);
    }
    
    @Override 
    public double calcularTarifa(double horas){
        return (montoBase) + (precioHora * horas);
    }

}

class Auto extends Vehiculo {

    private double montoBase = 100.0;
    private double precioHora = 75.0;

    public Auto(String placa, String numeroTicket){
        super(placa, numeroTicket);
    }

    @Override

    public double calcularTarifa(double horas){
        return montoBase + (precioHora * horas);
    }

}

class Tarifa {

    private double tarifaMoto = 50.0;
    private double tarifaAuto = 75.0;

    public double calcularTarifa(Vehiculo vehiculo, LocalDateTime HoraEntrada, LocalDateTime HoraSalida) throws TiempoEstacionamientoInvalido{

        Duration tiempoTranscurrido = Duration.between(HoraEntrada, HoraSalida);

        long minutos = tiempoTranscurrido.toMinutes();

        if(minutos <= 0){
            throw new TiempoEstacionamientoInvalido("Tiempo de estacionamiento inválido!");
        }

        long cantHoras = (minutos + 59) / 60;

        return vehiculo.calcularTarifa(cantHoras);
    }

}


class TicketParqueo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idTicket;
    private Vehiculo vehiculo;
    private LocalDateTime horaEntrada;


    public TicketParqueo(String id, Vehiculo vehiculo, LocalDateTime horaEntrada){
        this.idTicket = UUID.randomUUID().toString();
        this.vehiculo = vehiculo;
        this.horaEntrada = LocalDateTime.now();
    }

    public String getID(){
        return idTicket;
    }

    public LocalDateTime getHoraEntrada(){
        return horaEntrada;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }
    
}

class Parqueo {

    private int Capacidad;
    private int cuposDisponibles;

    private ArrayList<TicketParqueo> vehiculosParqueados;

    public Parqueo(int Capacidad){
        this.Capacidad = Capacidad;
        this.cuposDisponibles = cuposDisponibles;
        
        vehiculosParqueados = new ArrayList<>();
    }

    public synchronized void ingresarVehiculo(TicketParqueo ticket) throws ParqueoLlenoException {

        if(cuposDisponibles <= 0){
            throw new ParqueoLlenoException("EL PARQUE SE ENCUENTRA LLENO ACTUALMENTE!!");
        }

        vehiculosParqueados.add(ticket);
        cuposDisponibles--;
    }

    public synchronized TicketParqueo sacarVehiculo(String idTicket){

        for(TicketParqueo ticket : vehiculosParqueados){

            if(ticket.getID().equals(idTicket)){
                vehiculosParqueados.remove(ticket);
                cuposDisponibles++;
                return ticket;
            }
        }

        return null;
    }

    public synchronized int getCuposDisponibles(){
        return cuposDisponibles;
    }

    public int getCapacidad(){
        return Capacidad;
    }

    public synchronized int getVehiculoEstacionados(){
        return vehiculosParqueados.size();
    }


}


public class App {
    public static void main(String[] args) throws Exception {
        
        Vehiculo v1 = new Auto("1234", "0001");
        Vehiculo v2 = new Moto("1342", "0002");

        double t1 = v1.calcularTarifa(2);
        double t2 = v2.calcularTarifa(2);

        IO.println("TIPO: AUTO" + " PLACA: " + v1.getPlaca() + " | NUMERO TICKET: " + v1.getNumeroTicket() + " | COSTO: " + t1);
        IO.println("TIPO: MOTO" + " PLACA: " + v2.getPlaca() + " | NUMERO TICKET: " + v2.getNumeroTicket() + " | COSTO: " + t2);
    }
}
