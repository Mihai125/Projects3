package piese;

import java.util.ArrayList;
import java.util.List;
import tabla.Tabla;

public class Pion extends Piesa {

    private int pozitie;
    private final Alianta alianta;
    private final Tabla tabla;

    public Pion(int pozitie, Alianta alianta, Tabla tabla) {
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
            return "WP";
        } else {
            return "BP";
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
        int dest;
        if (this.esteAlba()) {

            if (!margineSus(p)) {
                dest = p - 8;
                if (tabla.getPiesa(dest) == null) {
                    mutariPosibile.add(dest);
                }

                if (!margineStanga(p)) {
                    dest = p - 9;
                    if (tabla.getPiesa(dest) != null) {
                        if (tabla.getPiesa(dest).esteNeagra()) {
                            mutariPosibile.add(dest);
                        }
                    }
                }

                if (!margineDreapta(p)) {
                    dest = p - 7;
                    if (tabla.getPiesa(dest) != null) {
                        if (tabla.getPiesa(dest).esteNeagra()) {
                            mutariPosibile.add(dest);
                        }
                    }
                }
            }

        } else if (this.esteNeagra()) {
            if (!margineJos(p)) {
                dest = p + 8;
                if (tabla.getPiesa(dest) == null) {
                    mutariPosibile.add(dest);
                }

                if (!margineStanga(p)) {
                    dest = p + 7;
                    if (tabla.getPiesa(dest) != null) {
                        if (tabla.getPiesa(dest).esteAlba()) {
                            mutariPosibile.add(dest);
                        }
                    };
                }

                if (!margineDreapta(p)) {
                    dest = p + 9;
                    if (tabla.getPiesa(dest) != null) {
                        if (tabla.getPiesa(dest).esteAlba()) {
                            mutariPosibile.add(dest);
                        }
                    }
    
                }
            }

        }

        return mutariPosibile;
    }

}
