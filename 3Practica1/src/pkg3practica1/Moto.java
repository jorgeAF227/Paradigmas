package pkg3practica1;

public class Moto extends Vehiculo {

    public int n_ruedas = 2;
    public int comsumoEstandar;

    // Métodos
    public int getN_ruedas() {
        return n_ruedas;
    }

    public void setN_ruedas(int n_ruedas) {
        this.n_ruedas = n_ruedas;
    }

    public int getComsumoEstandar() {
        return comsumoEstandar;
    }

    public void setComsumoEstandar(int comsumoEstandar) {
        this.comsumoEstandar = comsumoEstandar;
    }

    @Override
    public void esquivarObstaculo() {

    }

}
