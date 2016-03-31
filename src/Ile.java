import java.util.Random;
/**
 * Class qui genere l'ile
 * @author Simon, Valentin et Emeline
 *
 */
public class Ile {

	private Parcelle[][] tableau;
	private static double tauxRoc = 0.1;
	
	/**
	 * Genere les coordonnees des rochers ainsi que leur nombre voulu
	 * @param nbRochers
	 * @return int[][]
	 */
	int[][] genererRochers(int nbRochers){
		int[][] tabRochers = new int[nbRochers+2][2];   // nombre de rochers voulus et +2 pour la cle et le coffre
		Random r = new Random();
		for (int i = 0; i < nbRochers; i++) {
			boolean present = true;
			// verifie qu'un rocher n'est pas deja present à l'endroit vise
			while(present){
				present = false;
				tabRochers[i][0] = r.nextInt(this.tableau.length - 2) + 1;
				tabRochers[i][1] = r.nextInt(this.tableau.length - 2) + 1;
				for (int j = 0; j < i; j++) {
					if(tabRochers[j][0] == tabRochers[i][0] && tabRochers[j][1] == tabRochers[i][1]){
						present = true;
					}
				}
				if(tabRochers[i][0] == 1 && tabRochers[i][1] == (this.tableau.length-2) || tabRochers[i][1] == 1 && tabRochers[i][0] == (this.tableau.length-2)){
					present = true;
				}
			}
		}
		
		// CACHER CLE ET COFFRE SOUS UN ROCHER DE LA MAP
		int rocherCle, rocherCoffre;
		rocherCle = r.nextInt(nbRochers);
		tabRochers[nbRochers][0] = tabRochers[rocherCle][0];
		tabRochers[nbRochers][1] = tabRochers[rocherCle][1];
		
		do{
			rocherCoffre = r.nextInt(nbRochers);
		}while(rocherCle == rocherCoffre);
		
		tabRochers[nbRochers+1][0] = tabRochers[rocherCoffre][0];
		tabRochers[nbRochers+1][1] = tabRochers[rocherCoffre][1];
		
		
		return tabRochers;
	}
	
	/**
	 * Genere la mer, les bateaux des 2 equipes, les rochers et leur pourcentage ainsi que la terre
	 */
	public void genererIle(){
		System.out.println(tauxRoc);
		int nbRochers = (int) ((this.tableau.length - 2) * (this.tableau[0].length - 2) * tauxRoc);
		int[][] tabRochers = this.genererRochers(nbRochers);
		for (int i = 0; i < this.tableau.length; i++) {
			for (int j = 0; j < this.tableau.length; j++) {
				if(i == 0 || j == 0 || i == this.tableau.length-1 || j == this.tableau.length-1){
					this.tableau[i][j]= new Parcelle(2); // EAU
				}else if(i == 1 && j == this.tableau.length-2){
					this.tableau[i][j]= new Parcelle(3); //NAVIRE EQUIPE 1
				}else if(i == this.tableau.length-2 && j == 1){
					this.tableau[i][j] = new Parcelle(4); //NAVIRE EQUIPE 2
				}else{
					boolean estpresent = false;
					for(int[] tab : tabRochers) {
						if(tab[0] == i && tab[1] == j){
							estpresent = true;
							this.tableau[i][j] = new Parcelle(1); //ROCHERS
							
							if(i == tabRochers[nbRochers][0] && j == tabRochers[nbRochers][1]){
								this.tableau[i][j].cle = true;
							}else if(i == tabRochers[nbRochers+1][0] && j == tabRochers[nbRochers+1][1]){
								this.tableau[i][j].coffre = true;
							}
						}
					}
					if(!estpresent){
						this.tableau[i][j] = new Parcelle(5); //TERRE
					}
				}
			}
		}   if(this.tableau[1][this.tableau.length-3].getNb()==1 || this.tableau[2][this.tableau.length-2].getNb()==1 || 
				this.tableau[this.tableau.length-3][1].getNb()==1 || this.tableau[this.tableau.length-2][2].getNb()==1){
			genererIle();
		}
	}
	
	/**
	 * Renvoie un tableau d'entier a deux dimensions pour faciliter l'affichage (utilisation de la class plateau)
	 * @return int[][]
	 */
	public int[][] getTableau(){
		int[][] jeu = new int[this.tableau.length][this.tableau[0].length];
		for (int i = 0; i < this.tableau.length; i++) {
			for (int j = 0; j < this.tableau[0].length; j++) {
				jeu[i][j] = this.tableau[i][j].getNb();
				
			}
			
		}
		return jeu;
	}
	public void setTauxRoc(double taux){
		this.tauxRoc = taux;
	}
	/**
	 * Constructeur de la class Ile
	 */			
	public Ile(){
		this.tableau = new Parcelle[10][10];
	}
	
	public Ile(int n){
		this.tableau = new Parcelle[n][n];
	}
	
	public Ile(int n, int m){
		this.tableau = new Parcelle[n][m];
	}
}
