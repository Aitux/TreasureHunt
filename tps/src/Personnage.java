package src;
/**
 * Class pour la gestion des personnages
 * @author Simon, Valentin et Emeline
 *
 */
public abstract class Personnage {

	protected Ile ile;
	protected int energie; 
	protected int equipe; 
	protected int PosX;
	protected int PosY;
	protected boolean clé;
	protected boolean trésor;
	protected boolean estVivant;
	protected int piege;
	
	public Personnage (int equipe, Ile ile){
		this.equipe = equipe;
		if(equipe == 1){
			for(int i=0;i<ile.tableau.length;i++){
				for(int j=0;j<ile.tableau.length;j++){
					if(ile.tableau[i][j].getNb()==3){
						PosX = i+1;
						PosY = j;
					}
				}
			}
		} else if (equipe ==2){
			for(int i=0;i<ile.tableau.length;i++){
				for(int j=0;j<ile.tableau.length;j++){
					if(ile.tableau[i][j].getNb()==4){
						PosX = i;
						PosY = j+1;
					}
				}
			}
		}
		this.energie = 100;
		this.ile = ile;
	}
	public abstract String is();
	
	public abstract int isTeam();

	
	public void obtiensTrésor(){
		trésor = true;
	}
	public void obtiensClé(){
		clé = true;
	}
	
	public void perteEnergie(int energie){
		this.energie -= energie;
		if(this.energie<=0){
			 estVivant = false;
		}
	}
	
	public void gainEnergie(int energie){
		this.energie += energie;
		if(this.energie >= 100){
			this.energie=100;
		}
	}
	
	public int getEnergie(){
		return this.energie;
	}
	
	public boolean getEstVivant() {
		return this.estVivant;
	}

}
