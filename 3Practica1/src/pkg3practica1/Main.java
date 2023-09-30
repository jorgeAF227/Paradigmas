package pkg3practica1;

public class Main {

    public static void main(String[] args) {

        Conductor conductor1 = new Conductor();
        Conductor conductor2 = new Conductor();

        conductor1.setNombre("Juan");
        conductor1.setEdad(24);

        conductor2.setNombre("Maria");
        conductor2.setEdad(24);
        // por composicion
        HondaTypeR hondaTypeR = new HondaTypeR("Motor123", conductor1);
        hondaTypeR.listaConductor.add(conductor2);

        System.out.println("----Auto-----");
        System.out.println("Motor :" + hondaTypeR.N_motor);
        System.out.println("Lista de conductores:");
        for (Conductor conductor : hondaTypeR.listaConductor) { // for-each
            System.out.println("Nombre: " + conductor.getNombre() + ", Edad: " + conductor.getEdad());

        }
        //por agregacion
        HondaXR25 hondaXR25 = new HondaXR25(true, false);

        Copiloto copiloto1 = new Copiloto();
        copiloto1.setNombre("Ana");
        copiloto1.setPosibleConductor(false);

        Copiloto copiloto2 = new Copiloto();
        copiloto2.setNombre("Carlos");
        copiloto2.setPosibleConductor(true);

        hondaXR25.agregar(copiloto1);
        hondaXR25.agregar(copiloto2);

        // Acceder a la información del objeto HondaXR25
        System.out.println("----Moto-----");
        System.out.println("Frenos ABS: " + hondaXR25.isFrenosABS());
        System.out.println("Launch Control: " + hondaXR25.isLaunchControl());
        System.out.println("Lista de copilotos:");
        for (Copiloto copiloto : hondaXR25.getListaCopiloto()) { //for- each
            System.out.println("Nombre: " + copiloto.getNombre() + ", ¿Posible conductor? " + copiloto.isPosibleConductor());
        }

    }
}
