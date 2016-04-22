import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import tps.Plateau;
/**
 * Class qui genere l'ile
 * @author Simon, Valentin et Emeline
 *
 */
public class Ile {

	protected Parcelle[][] tableau;
	private static double tauxRoc = 0.1;
	private static ArrayList<Personnage> Team1 = new ArrayList<>();
	private static ArrayList<Personnage> Team2 = new ArrayList<>();
	private Plateau p;

	/**
	 * Genere les coordonnees des rochers ainsi que leur nombre voulu
	 * @param nbRochers
	 * @return int[][]
	 */
	private int[][] genererRochers(int nbRochers){
		int[][] tabRochers = new int[nbRochers+2][2];   // nombre de rochers voulus et +2 pour la cle et le coffre
		Random r = new Random();
		for (int i = 0; i < nbRochers; i++) {
			boolean present = true;
			// verifie qu'un rocher n'est pas deja present Ã  l'endroit vise
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
	 * Verifie que tout les rochers sont accessibles par l'explorateur et qu'il est possible de sortir des bateaux
	 */

	public void checkIle(){
		int cpt;
		for(int i = 0; i < this.tableau.length; i++){
			cpt = 0;
			for(int j = 0; j < this.tableau.length; j++){
				cpt = 0;
				if(this.tableau[i][j].getNb() == 1){ 
					for(int k= i-1; k < i+1; k++){
						for(int l = j-1; l < j+1; l++){
							if(this.tableau[k][l].getNb() == 1){
								cpt+=1;
								if(cpt >= 4 ){
									this.tableau[k][l].setNb(5);
								}
							}
						}
					}
				}
			}
		}
	}
	public void initChamp(){
		Team1.add(new Explorateur(1,this));
		Team2.add(new Explorateur(2, this));
		Team1.add(new Explorateur(2,this));
		Team2.add(new Voleur(2, this));
	}

	public void deplacerChamp(int x, int y, int x1, int y1){
		int tmp;

		//	System.out.println(this.tableau[y][x].getNb()+"---"+this.tableau[y1][x1].getNb());
		if(this.tableau[y][x].getNb() == 6 || this.tableau[y][x].getNb() == 7){
			if(this.tableau[y1][x1].getNb() == 5 && ((x1 == x+1 && y1 == y) || (x1 == x-1 && y1 == y) || (x1 == x && y1 == y+1) || (x1 == x && y1 == y-1))){
				tmp = this.tableau[y1][x1].getNb();
				this.tableau[y1][x1].setNb(this.tableau[y][x].getNb());
				this.tableau[y][x].setNb(tmp);
				//				System.out.println("Taunty");

			}
		}

		if(this.tableau[y1][x1].getNb() == 1 && this.tableau[y][x].getNb() == 7  &&( (x1 == x+1 && y1 == y) || (x1 == x-1 && y1 == y) || (x1 == x && y1 == y+1) || (x1 == x && y1 == y-1))){
			CheckCaillasse(x1,y1,1);
		}
		if(this.tableau[y1][x1].getNb() == 1 && this.tableau[y][x].getNb() == 6  &&( (x1 == x+1 && y1 == y) || (x1 == x-1 && y1 == y) || (x1 == x && y1 == y+1) || (x1 == x && y1 == y-1))){
			CheckCaillasse(x1,y1,2);
		}

	}

	private void CheckCaillasse(int x1, int y1, int equipe){
		if(Team1.get(0).equipe == equipe){
			System.out.println("Taunty e1");
			Explorateur e1 = (Explorateur) Team1.get(0);
			e1.SouleverRocher(y1, x1);
		}
		if(Team2.get(0).equipe == equipe){
			System.out.println("Taunty e2");
			Explorateur e2 = (Explorateur) Team2.get(0);
			e2.SouleverRocher(y1, x1);
		}
	}

	/**
	 * Verifie qu'il y ait un chemin qui permet aux équipes de se rencontrer #RECURSIVITE :)
	 * @param tableauIle
	 * @return boolean
	 */

	//	public boolean RencontrePossible(int[][] tableauIle){
	//		if()
	//			return true;
	//	}




	/**
	 * Genere la mer, les bateaux des 2 equipes, les rochers et leur pourcentage ainsi que la terre
	 */
	public void genererIle(){
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
								//								System.out.println("Wallah");
								this.tableau[i][j].setCle(true);
							}else if(i == tabRochers[nbRochers+1][0] && j == tabRochers[nbRochers+1][1]){
								//								System.out.println("Cpasmwa");
								this.tableau[i][j].setCoffre(true);
								this.tableau[i][j].setTrésor(true);
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
		tauxRoc = taux;
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
	private int[] getCase(Plateau s){
		int[] coo = new int[2];
		s.println("Séléctionnez une case");
		InputEvent event = s.waitEvent();
		coo[0]= s.getX((MouseEvent)	event);
		coo[1]= s.getY((MouseEvent) event);
		return coo;
	}


	/**
	 * permet de récupérer les coordonnées du deuxième clique du joueur
	 * @param s
	 * @return coo 
	 */
	private int[] getFinal(Plateau s){
		int[] coo = new int[2];
		s.println("Faites une action");
		InputEvent event = s.waitEvent();
		coo[0]= s.getX((MouseEvent)	event);
		coo[1]= s.getY((MouseEvent) event);
		return coo;
	}
	/**
	 * Lance une action différente en fonction de l'endroit ou à cliqué le joueur
	 * @param s
	 */
	public void selectCase(Plateau s){
		int[] select = getCase(s);
		switch(isSelected(select)){
		case 0: break; // Rien
		case 1: break; // Rocher
		case 2: break; // Eau
		case 3: navire(1); break; // Navire Equipe 1
		case 4: navire(2); break; // Navire Equipe 2
		case 5: break; // Terre
		case 6: explo(1); break; // Explorateur Equipe 1
		case 7: explo(2); break; // Explorateur Equipe 1
		case 8: break; // Futur Voleur Equipe 1
		case 9: break; // Futur Voleur Equipe 2
		case 10: break; // Futur Guerrier Equipe 1
		case 11: break; // Futur Guerrier Equipe 2
		case 12: break; // Futur Piegeur Equipe 1
		case 13: break; // Futur Piegeur Equipe 2
		}
	}

	private int isSelected(int[] s){
		return tableau[s[0]][s[1]].getNb();
	}

	public void addPlateau(Plateau plateau){
		this.p = plateau;
	}
	private void navire(int equipe){

		if(equipe == 1){
			String[] t1 = new String[Team1.size()];
			String rang =  (String) JOptionPane.showInputDialog(null,"Quelle unité voulez-vous faire sortir ? :","Sortie du Navire", JOptionPane.QUESTION_MESSAGE, null, t1, t1[0]);
			switch(rang){
			case "Explorateur" : Team1.remove("Explorateur"); break;
			}
		}else{
			int i = 0;
			String[] t2 = new String[Team2.size()];
			for(Personnage p : Team2){
				t2[i] = p.is();
				i++;
			}
			String rang =  (String) JOptionPane.showInputDialog(null,"Quelle unité voulez-vous faire sortir ? :","Sortie du Navire", JOptionPane.QUESTION_MESSAGE, null, t2, t2[0]);
		}
	}

	public void explo(int equipe){

	}

}
