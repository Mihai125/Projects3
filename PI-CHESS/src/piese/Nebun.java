package piese;

import java.util.ArrayList;
import java.util.List;
import tabla.Tabla;

public class Nebun extends Piesa {
	private int pozitie;
	private final Alianta alianta;
        private final Tabla tabla;

	public Nebun(int pozitie, Alianta alianta, Tabla tabla) {
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
			return "WB";
		} else {
			return "BB";
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
		
		
		while (!margineDreapta(dest) && !margineSus(dest)) {
			dest -= 7;
                        if(tabla.getSpatii().get(dest).getPiesa() != null){
                            
                            if(tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()){
                                break;
                            }else{
                                mutariPosibile.add(dest);
                                break;
                            }
                            
                        }else{
                            mutariPosibile.add(dest);
                        }
			
		}
		dest = p;
		while (!margineDreapta(dest) && !margineJos(dest)) {
			dest += 9;
                        if(tabla.getSpatii().get(dest).getPiesa() != null){
                            
                            if(tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()){
                                break;
                            }else{
                                mutariPosibile.add(dest);
                                break;
                            }
                            
                        }else{
                            mutariPosibile.add(dest);
                        }
			
		}
		dest = p;
		while (!margineStanga(dest) && !margineSus(dest)) {
			dest -= 9;
                        
			 if(tabla.getSpatii().get(dest).getPiesa() != null){
                            
                            if(tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()){
                                break;
                            }else{
                                mutariPosibile.add(dest);
                                break;
                            }
                            
                        }else{
                            mutariPosibile.add(dest);
                        }
		}
		dest = p;
		while (!margineStanga(dest) && !margineJos(dest)) {
			dest += 7;
			 if(tabla.getSpatii().get(dest).getPiesa() != null){
                            
                            if(tabla.getPiesa(dest).esteAlba() && this.esteAlba() || tabla.getPiesa(dest).esteNeagra() && this.esteNeagra()){
                                break;
                            }else{
                                mutariPosibile.add(dest);
                                break;
                            }
                            
                        }else{
                            mutariPosibile.add(dest);
                        }
		}
			
		return mutariPosibile;
	}
        

}
