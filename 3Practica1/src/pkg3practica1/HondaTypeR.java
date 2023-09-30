package pkg3practica1;

import java.util.ArrayList;

public class HondaTypeR extends Auto {

    public String N_motor;
    public int año;
    public String color;
    public ArrayList<Conductor> listaConductor = new ArrayList<>();

    public HondaTypeR(String N_motor, int año, Conductor conductor) {
        this.N_motor = N_motor;
        this.año = año;
        this.listaConductor.add(conductor);

    }

    public HondaTypeR(String N_motor, Conductor conductor) {
        this.N_motor = N_motor;

        this.listaConductor.add(conductor);
    }

    public void agregarConductor(Conductor conductor) {
        listaConductor.add(conductor);
    }

    public String getN_motor() {
        return N_motor;
    }

    public void setN_motor(String N_motor) {
        this.N_motor = N_motor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Conductor> getListaConductor() {
        return listaConductor;
    }

   

    @Override
    public void esquivarObstaculo() {

    }
}
