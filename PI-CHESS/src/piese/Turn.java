package piese;

import java.util.ArrayList;
import java.util.List;
import tabla.Tabla;

public class Turn extends Piesa {

    private int pozitie;
    private final Alianta alianta;
    private final Tabla tabla;

    public Turn(int pozitie, Alianta alianta, Tabla tabla) {
        this.pozitie = pozitie;
        this.alianta = alianta;
        this.tabla = tabla;
    }

    @Override
    public int getPozitie() {
        return this.pozitie;
    }

    @Override
    public void setPozitie(int pozitie) {
        this.pozitie = pozitie;
    }

    @Override
    public String toString() {
        if (this.esteAlba()) {
            return "WR";
        } else {
            return "BR";
        }
    }

    @Override
    public boolean esteNeagra() {
        return this.alianta == Alianta.NEGRU;
    }

    @Override
    public boolean esteAlba() {
        return this.alianta == Alianta.ALB;
    }

    @Override
    public List<Integer> mutariPosibile() {
        List<Integer> mutariPosibile = new ArrayList<>();
        int p = this.getPozitie();
        int dest = p;
        while (!margineDreapta(dest)) {
            dest += 1;
            if (tabla.getSpatii().get(dest).getPiesa() != null) {

                if (tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()) {
                    break;
                } else {
                    mutariPosibile.add(dest);
                    break;
                }

            } else {
                mutariPosibile.add(dest);
            }
        }

        while (!margineStanga(dest)) {
            dest -= 1;
            if (tabla.getSpatii().get(dest).getPiesa() != null) {

                if (tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()) {
                    break;
                } else {
                    mutariPosibile.add(dest);
                    break;
                }

            } else {
                mutariPosibile.add(dest);
            }
        }

        dest = p;

        while (!margineSus(dest)) {
            dest -= 8;
            if (tabla.getSpatii().get(dest).getPiesa() != null) {

                if (tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()) {
                    break;
                } else {
                    mutariPosibile.add(dest);
                    break;
                }

            } else {
                mutariPosibile.add(dest);
            }
        }

        dest = p;

        while (!margineJos(dest)) {
            dest += 8;
            if (tabla.getSpatii().get(dest).getPiesa() != null) {

                if (tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()) {
                    break;
                } else {
                    mutariPosibile.add(dest);
                    break;
                }

            } else {
                mutariPosibile.add(dest);
            }
        }

        return mutariPosibile;
    }

}
