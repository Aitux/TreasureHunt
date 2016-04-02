
/**
 * Class qui cree et gere les differents types de parcelles
 * @author Simon, Valentin et Emeline
 *
 */
public class Parcelle {

	private int nb;
	boolean cle;
	boolean coffre;
	boolean trésor;
	
	
	/**
	 * Affecte un type a une parcelle
	 * @param element
	 */
	public Parcelle(int element){
	
		// ROCHERS
		if(element == 1){
			this.nb = 1;
			cle = false;
			coffre = false;
			trésor = false;
		//EAU
		}else if(element == 2){
			this.nb = 2;
			cle = false;
			coffre = false;
			trésor = false;

		//NAVIRE EQUIPE 1	
		}else if(element == 3){
			this.nb = 3;
			cle = false;
			coffre = false;
			trésor = false;

		//NAVIRE EQUIPE 2	
		}else if(element == 4){
			this.nb = 4;
			cle = false;
			coffre = false;
			trésor = false;

		// TERRE
		}else{
			this.nb = 5;
			cle = false;
			coffre = false;
			trésor = false;

		}
	}
	
	/**
	 * Renvoie le type de la parcelle
	 * @return int
	 */
	public int getNb(){
		return this.nb;
	}
	
	/**
	 * Modifie le type de la parcelle
	 * @param nb
	 */
	public void setNb(int nb){
		this.nb = nb;
	}
}
