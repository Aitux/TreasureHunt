
/**
 * Class qui cree et gere les differents types de parcelles
 * @author Simon, Valentin et Emeline
 *
 */
public class Parcelle {

	private int nb;
	private boolean cle;
	private boolean coffre;
	private boolean trésor;
	private boolean isVisible;

	/**
	 * Affecte un type a une parcelle
	 * @param element
	 */
	public Parcelle(int element){

		// ROCHERS
		if(element == 1){
			this.nb = 1;
			setCle(false);
			setCoffre(false);
			setTrésor(false);
			//EAU
		}else if(element == 2){
			this.nb = 2;
			setCle(false);
			setCoffre(false);
			setTrésor(false);

			//NAVIRE EQUIPE 1	
		}else if(element == 3){
			this.nb = 3;
			setCle(false);
			setCoffre(false);
			setTrésor(false);

			//NAVIRE EQUIPE 2	
		}else if(element == 4){
			this.nb = 4;
			setCle(false);
			setCoffre(false);
			setTrésor(false);

			// Explorateur Equipe 1
		}else if(element == 6){
			this.nb = 6;
			setCle(false);
			setCoffre(false);
			setTrésor(false);
			// Explorateur Equipe 2
		}else if(element == 7){
			this.nb = 7;
			setCle(false);
			setCoffre(false);
			setTrésor(false);
			//			Personnage explo2 = new Explorateur(2, ile)
			//Voleur Equipe 1
		}else if(element == 8){
			this.nb = 8;
			setCle(false);
			setCoffre(false);
			setTrésor(false);
			//Voleur Equipe 2
		}else if(element == 9){
			this.nb = 9;
			setCle(false);
			setCoffre(false);
			setTrésor(false);
			//Terre
			
		}else if(element == 14){
			this.nb = 14;
			setCle(false);
			setCoffre(false);
			setTrésor(false);
		}else{
			this.nb = 5;
			setCle(false);
			setCoffre(false);
			setTrésor(false);

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

	/**
	 * @return the trésor
	 */
	public boolean getTrésor() {
		return trésor;
	}

	/**
	 * @param trésor the trésor to set
	 */
	public void setTrésor(boolean trésor) {
		this.trésor = trésor;
	}

	/**
	 * @return the coffre
	 */
	public boolean getCoffre() {
		return coffre;
	}

	/**
	 * @param coffre the coffre to set
	 */
	public void setCoffre(boolean coffre) {
		this.coffre = coffre;
	}

	/**
	 * @return the cle
	 */
	public boolean getCle() {
		return cle;
	}
	public boolean isVisible(){
		return isVisible;
	}
	
	public void setVisibility(boolean isVisible){
		this.isVisible = isVisible;
	}

	/**
	 */
	public void setCle(boolean cle) {
		this.cle = cle;
	}
	
	public boolean isAccessible(int p){
		if(p == 5){
			return true;
		}else return false;
	}
}
