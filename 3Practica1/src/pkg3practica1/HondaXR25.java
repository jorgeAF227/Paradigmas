package pkg3practica1;

import java.util.ArrayList;

public class HondaXR25 extends Moto {

    public boolean frenosABS;
    public boolean launchControl;
    public ArrayList<Copiloto> listaCopiloto = new ArrayList<>();

    public HondaXR25(boolean frenosABS, boolean launchControl) {
        this.frenosABS = frenosABS;
        this.launchControl = launchControl;
    }

    public void agregar(Copiloto copiloto) {
        listaCopiloto.add(copiloto);
    }

    public boolean isFrenosABS() {
        return frenosABS;
    }

    public void setFrenosABS(boolean frenosABS) {
        this.frenosABS = frenosABS;
    }

    public boolean isLaunchControl() {
        return launchControl;
    }

    public void setLaunchControl(boolean launchControl) {
        this.launchControl = launchControl;
    }

    public ArrayList<Copiloto> getListaCopiloto() {
        return listaCopiloto;
    }

    public void setListaCopiloto(ArrayList<Copiloto> listaCopiloto) {
        this.listaCopiloto = listaCopiloto;
    }

   

    public void esquivarObstaculo(int metros) {

    }

}
