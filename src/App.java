

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
        return (montoBase) + (precioHora * horas);
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
