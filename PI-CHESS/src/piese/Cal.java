package piese;

import java.util.ArrayList;
import java.util.List;
import tabla.Tabla;

public class Cal extends Piesa {

    private int pozitie;
    private final Alianta alianta;
    private final Tabla tabla;

    public Cal(int pozitie, Alianta alianta, Tabla tabla) {
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
            return "WN";
        } else {
            return "BN";
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
        
        if (!margineJos(p) && !margineDreapta(p) && !margineDreapta(p + 1)) {
            dest = p + 10;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }
        }

        
        if (!margineDreapta(p) && !margineJos(p) && !margineJos(p + 8)) {
            dest = p + 17;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }
        }

        
        if (!margineStanga(p) && !margineJos(p) && !margineJos(p + 8)) {
            dest = p + 15;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }
        }

        
        if (!margineJos(p) && !margineStanga(p) && !margineStanga(p - 1)) {
            dest = p + 6;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }
        }

        
        if (!margineSus(p) && !margineStanga(p) && !margineStanga(p - 1)) {
            dest = p - 10;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }
        }

        
        if (!margineStanga(p) && !margineSus(p) && !margineSus(p - 8)) {
            dest = p - 17;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }

        }

        
        if (!margineDreapta(p) && !margineSus(p) && !margineSus(p - 8)) {
            dest = p - 15;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }
        }

        
        if (!margineSus(p) && !margineDreapta(p) && !margineDreapta(p + 1)) {
            dest = p - 6;
            if (tabla.getPiesa(dest) != null) {
                if (tabla.getPiesa(dest).esteAlba() && this.esteNeagra() || tabla.getPiesa(dest).esteNeagra() && this.esteAlba()) {
                    mutariPosibile.add(dest);
                }
            } else {
                mutariPosibile.add(dest);
            }
        }

        return mutariPosibile;
    }

}
