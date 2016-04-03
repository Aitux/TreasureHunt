
/**
 * Class qui cree et gere les differents types de parcelles
 * @author Simon, Valentin et Emeline
 *
 */
public class Parcelle {

	private int nb;
	private boolean cle;
	private boolean coffre;
	private boolean tr�sor;
	
	
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
			setTr�sor(false);
		//EAU
		}else if(element == 2){
			this.nb = 2;
			setCle(false);
			setCoffre(false);
			setTr�sor(false);

		//NAVIRE EQUIPE 1	
		}else if(element == 3){
			this.nb = 3;
			setCle(false);
			setCoffre(false);
			setTr�sor(false);

		//NAVIRE EQUIPE 2	
		}else if(element == 4){
			this.nb = 4;
			setCle(false);
			setCoffre(false);
			setTr�sor(false);

		// TERRE
		}else{
			this.nb = 5;
			setCle(false);
			setCoffre(false);
			setTr�sor(false);

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
	 * @return the tr�sor
	 */
	public boolean getTr�sor() {
		return tr�sor;
	}

	/**
	 * @param tr�sor the tr�sor to set
	 */
	public void setTr�sor(boolean tr�sor) {
		this.tr�sor = tr�sor;
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
