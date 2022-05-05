package tabla;

import java.util.ArrayList;
import java.util.List;

import piese.*;

public class Tabla {

    private List<Spatiu> spatii;

    public Tabla(List<Spatiu> spatii) {
        this.spatii = spatii;
    }

    public List<Spatiu> getSpatii() {
        return this.spatii;
    }

    public static Tabla creeazaTablaStandard() {
        List<Spatiu> listaSpatii = new ArrayList<>();
        Tabla tabla = new Tabla(listaSpatii);
        // Initializare tabla cu spatii goale
        for (int i = 0; i < 64; i++) {
            listaSpatii.add(i, new Spatiu(i, null));
        }

        // Piese speciale sus
        listaSpatii.get(0).setPiesa(new Turn(0, Alianta.NEGRU, tabla));
        listaSpatii.get(1).setPiesa(new Cal(1, Alianta.NEGRU, tabla));
        listaSpatii.get(2).setPiesa(new Nebun(2, Alianta.NEGRU, tabla));
        listaSpatii.get(3).setPiesa(new Rege(3, Alianta.NEGRU, tabla));
        listaSpatii.get(4).setPiesa(new Regina(4, Alianta.NEGRU, tabla));
        listaSpatii.get(5).setPiesa(new Nebun(5, Alianta.NEGRU, tabla));
        listaSpatii.get(6).setPiesa(new Cal(6, Alianta.NEGRU, tabla));
        listaSpatii.get(7).setPiesa(new Turn(7, Alianta.NEGRU, tabla));
        // Pioni sus
        listaSpatii.get(8).setPiesa(new Pion(8, Alianta.NEGRU, tabla));
        listaSpatii.get(9).setPiesa(new Pion(9, Alianta.NEGRU, tabla));
        listaSpatii.get(10).setPiesa(new Pion(10, Alianta.NEGRU, tabla));
        listaSpatii.get(11).setPiesa(new Pion(11, Alianta.NEGRU, tabla));
        listaSpatii.get(12).setPiesa(new Pion(12, Alianta.NEGRU, tabla));
        listaSpatii.get(13).setPiesa(new Pion(13, Alianta.NEGRU, tabla));
        listaSpatii.get(14).setPiesa(new Pion(14, Alianta.NEGRU, tabla));
        listaSpatii.get(15).setPiesa(new Pion(15, Alianta.NEGRU, tabla));

        // Pioni jos
        listaSpatii.get(48).setPiesa(new Pion(48, Alianta.ALB, tabla));
        listaSpatii.get(49).setPiesa(new Pion(49, Alianta.ALB, tabla));
        listaSpatii.get(50).setPiesa(new Pion(50, Alianta.ALB, tabla));
        listaSpatii.get(51).setPiesa(new Pion(51, Alianta.ALB, tabla));
        listaSpatii.get(52).setPiesa(new Pion(52, Alianta.ALB, tabla));
        listaSpatii.get(53).setPiesa(new Pion(53, Alianta.ALB, tabla));
        listaSpatii.get(54).setPiesa(new Pion(54, Alianta.ALB, tabla));
        listaSpatii.get(55).setPiesa(new Pion(55, Alianta.ALB, tabla));

        // Piese speciale jos
        listaSpatii.get(56).setPiesa(new Turn(56, Alianta.ALB, tabla));
        listaSpatii.get(57).setPiesa(new Cal(57, Alianta.ALB, tabla));
        listaSpatii.get(58).setPiesa(new Nebun(58, Alianta.ALB, tabla));
        listaSpatii.get(59).setPiesa(new Rege(59, Alianta.ALB, tabla));
        listaSpatii.get(60).setPiesa(new Regina(60, Alianta.ALB, tabla));
        listaSpatii.get(61).setPiesa(new Nebun(61, Alianta.ALB, tabla));
        listaSpatii.get(62).setPiesa(new Cal(62, Alianta.ALB, tabla));
        listaSpatii.get(63).setPiesa(new Turn(63, Alianta.ALB, tabla));

        tabla.spatii = listaSpatii;
        return tabla;
    }

    public Piesa getPiesa(int co) {
        if (this.getSpatii().get(co).esteOcupat()) {
            return this.getSpatii().get(co).getPiesa();
        } else {
            return null;
        }
    }

    public void mutare(Piesa piesa, int destinatie) {

        this.getSpatii().get(piesa.getPozitie()).setPiesa(null);
        this.getSpatii().get(destinatie).setPiesa(piesa);
        piesa.setPozitie(destinatie);
        System.out.println("piesa mutata la " + destinatie);
    }

  

}
