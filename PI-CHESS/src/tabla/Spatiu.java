package tabla;

import piese.Piesa;
public class Spatiu {
	private final int index;
        private Piesa piesa;
        
	public Spatiu(int index, Piesa piesa){
            this.index = index;
            this.piesa = piesa;
        }
        
	public boolean esteOcupat(){
            return this.piesa != null;
        }
        
        public int getIndex(){
            return this.index;
        }
        
	public Piesa getPiesa(){
            return this.piesa;
        }
        
        public void setPiesa(Piesa piesa){
            this.piesa = piesa;
        }
}
