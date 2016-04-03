
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

		// TERRE
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

	/**
	 */
	public void setCle(boolean cle) {
		this.cle = cle;
	}
}
