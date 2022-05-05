package piese;

import java.util.List;

public abstract class Piesa {


        @Override
	public abstract String toString();


	public abstract boolean esteNeagra();
	public abstract boolean esteAlba();

	public abstract int getPozitie();
	public abstract void setPozitie(int pozitie);

	public abstract List<Integer> mutariPosibile();
	
	public boolean margineStanga(int co) {
		if (co % 8 == 0) {
			return true;
		}
		return false;
	}

	public boolean margineDreapta(int co) {
		if ((co + 1) % 8 == 0) {
			return true;
		}
		return false;
	}

	public boolean margineSus(int co) {
		if (co >= 0 && co <= 7) {
			return true;
		}
		return false;
	}

	public boolean margineJos(int co) {
		if (co >= 56 && co <= 63) {
			return true;
		}
		return false;
	}


}
