import java.awt.Color;
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

	protected Parcelle[][] tableau, fog, printable;
	private static double tauxRoc = 0.1;
	private static ArrayList<Personnage> onField = new ArrayList<>();
	private static ArrayList<Personnage> Team1 = new ArrayList<>();
	private static ArrayList<Personnage> Team2 = new ArrayList<>();
	private Plateau p;
	private int joueur;

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
		Team1.add(new Voleur(1,this));
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
		int w = 0;
		int i =0 ;
		boolean test = false;
		String[] Field = new String[onField.size()];
		for(Personnage p : onField){
			Field[w] = p.is();
			w++;
		}

		while(!test){
			if(Field[i].equals("Explorateur") && onField.get(i).equipe == equipe) test = true;
			else i++;
		}	//System.out.println("Taunty");			
		((Explorateur) onField.get(i)).SouleverRocher(y1, x1); 
	}


	/**
	 * Verifie qu'il y ait un chemin qui permet aux équipes de se rencontrer #RECURSIVITE :)
	 * @param tableauIle
	 * @return boolean
	 */


	public boolean RencontrePossible(int[][] tableauIle){
		//TODO 
		//	if()
		return true;
	}




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
								this.tableau[i][j].setCle(true);
							}else if(i == tabRochers[nbRochers+1][0] && j == tabRochers[nbRochers+1][1]){
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

		genererFog();
	}

	private void genererFog() {
		fog = new Parcelle[tableau.length][tableau[0].length];
		for(int i = 0; i<fog.length;i++){
			System.out.println();
			for(int j = 0; j<fog[0].length; j++){
				if(i == 0 || j == 0 || i == this.fog.length-1 || j == this.fog.length-1){
					this.fog[i][j]= new Parcelle(2); // EAU
				}else this.fog[i][j] = new Parcelle (14); // Fog of War
			}
		}
	}

	/**
	 * Renvoie un tableau d'entier a deux dimensions pour faciliter l'affichage (utilisation de la class plateau)
	 * @return int[][]
	 */
	public int[][] getTableau(){
		int[][] jeu = new int[this.printable.length][this.printable[0].length];
		for (int i = 0; i < this.printable.length; i++) {
			for (int j = 0; j < this.printable[0].length; j++) {
				jeu[i][j] = this.printable[i][j].getNb();
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
		try{
			coo[0]= s.getX((MouseEvent)	event);
			coo[1]= s.getY((MouseEvent) event);
		} catch(ClassCastException e){
			p.clear();
			p.println("Veuillez ne pas utiliser le clavier svp !");
		}
		return coo;
	}
	/**
	 * permet de récupérer les coordonnées du deuxième clique du joueur
	 * @param s
	 * @return coo 
	 */
	private int[] getFinal(Plateau s, int[] select){
		int[] coo = new int[2];
		putHighLight(select, isSelected(select));
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
	public void caseClicked(Plateau s){
		if(joueur == 0){
			int[] select = getCase(s);
			switch(isSelected(select)){
			case 0: break; // Rien
			case 1: break; // Rocher
			case 2: break; // Eau
			case 3: int ch = navireDeb(1);spawn(ch, 1, select); break; // Navire Equipe 1
			case 4: break; // Navire Equipe 2
			case 5: break; // Terre
			case 6: explo(1, select); break; // Explorateur Equipe 1
			case 7: break; // Explorateur Equipe 1
			case 8: voleur(1,select); break; // Futur Voleur Equipe 1
			case 9:  break; // Futur Voleur Equipe 2
			case 10: break; // Futur Guerrier Equipe 1
			case 11: break; // Futur Guerrier Equipe 2
			case 12: break; // Futur Piegeur Equipe 1
			case 13: break;// Futur Piegeur Equipe 2
			case 14: break; // Fog of War
			}
		}else if(joueur == 1){
			int[] select = getCase(s);
			switch(isSelected(select)){
			case 0: break; // Rien
			case 1: break; // Rocher
			case 2: break; // Eau
			case 3: break; // Navire Equipe 1
			case 4: int ch1 = navireDeb(2);spawn(ch1,2, select); break; // Navire Equipe 2
			case 5: break; // Terre
			case 6: break; // Explorateur Equipe 1
			case 7: explo(2, select); break; // Explorateur Equipe 1
			case 8: break; // Futur Voleur Equipe 1
			case 9: voleur(2,select);break; // Futur Voleur Equipe 2
			case 10: break; // Futur Guerrier Equipe 1
			case 11: break; // Futur Guerrier Equipe 2
			case 12: break; // Futur Piegeur Equipe 1
			case 13: break;// Futur Piegeur Equipe 2
			case 14: break; // Fog of War	
			}
		}

	}
	private int isSelected(int[] s){
		return tableau[s[1]][s[0]].getNb();
	}

	public void addPlateau(Plateau plateau){
		this.p = plateau;
	}
	private int navireDeb(int equipe){
		String rang;
		if(equipe == 1){
			int i = 0;
			int j = 0;
			String[] t1 = new String[Team1.size()];
			for(Personnage p : Team1){
				t1[i] = p.is();
				i++;
			}
			if(t1.length!=0){
				rang =  (String) JOptionPane.showInputDialog(null,"Quelle unité voulez-vous faire sortir ? :","Sortie du Navire", JOptionPane.QUESTION_MESSAGE, null, t1, t1[0]);
			}else {
				rang = null;
			}
			if(rang != null){
				switch(rang){
				//		case JOptionPane.CANCEL_OPTION : break;
				case "Explorateur" : while(!t1[j].equals("Explorateur")) {
					j++;
				}
				onField.add(Team1.get(j));
				Team1.remove(j); return 6 ;
				case "Voleur": while(!t1[j].equals("Voleur")){
					j++;
				}
				onField.add(Team1.get(j));
				Team1.remove(j); return 8; 
				default: break; 
				}
			}
		}else{
			int i = 0;
			int j = 0;
			String[] t2 = new String[Team2.size()];
			for(Personnage p : Team2){
				t2[i] = p.is();
				i++;
			}
			if(t2.length!=0){
				rang =  (String) JOptionPane.showInputDialog(null,"Quelle unité voulez-vous faire sortir ? :","Sortie du Navire", JOptionPane.QUESTION_MESSAGE, null, t2, t2[0]);
			}else rang  = null;
			if(rang != null){
				switch(rang){
				//		case JOptionPane.CANCEL_OPTION : break;
				case "Explorateur" : while(!t2[j].equals("Explorateur")) {
					j++;
				}
				onField.add(Team2.get(j));
				Team2.remove(j); return 7 ;
				case "Voleur": while(!t2[j].equals("Voleur")){
					j++;
				}
				onField.add(Team2.get(j));
				Team2.remove(j); return 9; 
				default: break; 
				}
			}
		}
		return -1;
	}

	private void putHighLight(int[] i, int j) {
		if(j == 8 || j == 9 || j == 3 || j == 4){		
			for(int l = i[0]-1; l<=i[0]+1;l++){
				for(int k = i[1]-1;k<=i[1]+1;k++){
					if(tableau[k][l].isAccessible(tableau[k][l].getNb())){
						p.setHighlight(l,k,Color.GREEN);
					}
				}
			}

		}else if(j == 6 || j == 7){
			for(int l = i[0]-1; l<=i[0]+1;l++){
				for(int k = i[1]-1;k<=i[1]+1;k++){
					if(tableau[k][l].isAccessible(tableau[k][l].getNb()) && ((l == i[0] && k == i[1]+1) || (l == i[0] && k == i[1]-1) || (l == i[0]-1 && k == i[1]) || (l == i[0]+1 && k == i[1]))){
						p.setHighlight(l,k,Color.GREEN);
					} else if(tableau[k][l].getNb() == 1 &&((l == i[0] && k == i[1]+1) || (l == i[0] && k == i[1]-1) || (l == i[0]-1 && k == i[1]) || (l == i[0]+1 && k == i[1])) ){
						p.setHighlight(l, k, Color.BLUE);
					}
				}
			}

		}else{
			for(int l = i[0]-1; l<=i[0]+1;l++){
				for(int k = i[1]-1;k<=i[1]+1;k++){
					if(tableau[k][l].isAccessible(tableau[k][l].getNb()) && ((l == i[0] && k == i[1]+1) || (l == i[0] && k == i[1]-1) || (l == i[0]-1 && k == i[1]) || (l == i[0]+1 && k == i[1]))){
						p.setHighlight(l,k,Color.GREEN);
					}
				}
			}
		}
	}

	private void spawn(int ch, int i, int[] select) {
		if(ch == -1){

		}else{
			if(i == 1){
				int[] coo =	getFinal(p, select);
				if( tableau[coo[1]][coo[0]].isAccessible(tableau[coo[1]][coo[0]].getNb()) && (coo[0]==select[0]+1 || coo[0] == select[0] || coo[0]==select[0]-1)){
					tableau[coo[1]][coo[0]].setNb(ch);
				}

			}else{
				int[] coo =	getFinal(p, select);
				if( tableau[coo[1]][coo[0]].isAccessible(tableau[coo[1]][coo[0]].getNb()) && (coo[0]==select[0]+1 || coo[0] == select[0] || coo[0]==select[0]-1)){
					tableau[coo[1]][coo[0]].setNb(ch);
				}
			}
		}

	}
	/**
	 * Execute les actions possible par l'explorateur Deplacer/Verifier les 
	 * @param equipe de l'explorateur qui veut faire une action
	 * @param select[] tableau d'int contenant le deuxième clique du joueur
	 */

	private void explo(int equipe, int[] select){
			deplacementExplo(select, equipe);
	}

	private void voleur(int equipe, int[] select){
			deplacementVoleur(select, equipe);
		}

	private void deplacementVoleur(int[] select, int equipe) {
		// TODO Auto-generated method stub
		int tmp;
		int[] coo = getFinal(p, select);
		int x1 = coo[0], y1 = coo[1], x = select[0], y = select[1];
		if(this.tableau[coo[1]][coo[0]].getNb() == 5 && ((x1 == x+1 ) || (x1 == x-1) || (y1 == y+1)|| (y1 == y-1))){
			tmp = this.tableau[coo[1]][coo[0]].getNb(); //						
			this.tableau[coo[1]][coo[0]].setNb(this.tableau[select[1]][select[0]].getNb());
			this.tableau[select[1]][select[0]].setNb(tmp);
		}else if(this.tableau[coo[1]][coo[0]].getNb() == 1 && ((x1 == x+1 && y1 == y) || (x1 == x-1 && y1 == y) || (x1 == x && y1 == y+1) || (x1 == x && y1 == y-1))) vol(x1,y1,equipe); 
		if(((tableau[y1][x1].getNb()  == 3 && equipe == 1) || (tableau[y1][x1].getNb()== 4 && equipe == 2)) && (coo[0]==select[0]+1 || coo[0] == select[0] || coo[0]==select[0]-1)) embBateau(y, x, equipe, "Voleur");
		else energieLost(equipe, "Voleur");
	}
	
	public boolean GGWP(){
		if(joueur == 0){
			for(Personnage p : Team1){
				if(p.trésor) return true;
			}
		}else{
			for(Personnage p : Team2){
				if(p.trésor) return true;
			}
		}
		
		return false;
	}
	
	private void deplacementExplo(int[] select, int equipe) {
		int tmp;
		int[] coo = getFinal(p, select);
		int x1 = coo[0], y1 = coo[1], x = select[0], y = select[1];
		if(this.tableau[coo[1]][coo[0]].getNb() == 5 && ((x1 == x+1 && y1 == y) || (x1 == x-1 && y1 == y) || (x1 == x && y1 == y+1) || (x1 == x && y1 == y-1))){
			tmp = this.tableau[coo[1]][coo[0]].getNb(); //						
			this.tableau[coo[1]][coo[0]].setNb(this.tableau[select[1]][select[0]].getNb());
			this.tableau[select[1]][select[0]].setNb(tmp);				
		}else if(this.tableau[coo[1]][coo[0]].getNb() == 1 && ((x1 == x+1 && y1 == y) || (x1 == x-1 && y1 == y) || (x1 == x && y1 == y+1) || (x1 == x && y1 == y-1)))CheckCaillasse(x1,y1,equipe);
		if(((tableau[y1][x1].getNb()  == 3 && equipe == 1) || (tableau[y1][x1].getNb()== 4 && equipe == 2)) && (coo[0]==select[0]+1 || coo[0] == select[0] || coo[0]==select[0]-1)) embBateau(y, x, equipe, "Explorateur");
		else energieLost(equipe, "Explorateur");

	}
	private void vol(int x1, int y1, int equipe){
		int w = 0;
		int i =0 ;
		boolean test = false;
		String[] Field = new String[onField.size()];
		for(Personnage p : onField){
			Field[w] = p.is();
			w++;
		}

		while(!test){
			if(Field[i].equals("Explorateur") && onField.get(i).equipe == equipe) test = true;
			else i++;
		}	//System.out.println("Taunty");			
		((Explorateur) onField.get(i)).SouleverRocher(y1, x1); 
	}


	private void energieLost(int equipe, String perso) {
		int w =0, i = 0 ;
		boolean test = false;
		String[] Field = new String[onField.size()];
		for(Personnage p : onField){
			Field[w] = p.is();
			w++;
		}

		while(!test){
			if(Field[i].equals(perso) && onField.get(i).equipe == equipe) test = true;
			else i++;
		}	//System.out.println("Taunty");			
		onField.get(i).perteEnergie(1);
		p.println(perso+" "+equipe + " energie restante = "+onField.get(i).energie);
	}


	private void embBateau(int x, int y, int e, String perso) {
		// TODO Auto-generated method stub
		System.out.println(perso);
		int w = 0;
		int i =0 ;
		boolean test = false;
		String[] Field = new String[onField.size()];
		for(Personnage p : onField){
			Field[w] = p.is();
			w++;
		}

		while(!test){
			if(Field[i].equals(perso) && onField.get(i).equipe == e) test = true;
			else i++;
		}	//System.out.println("Taunty");	

		if(e == 1){ Team1.add(onField.get(i));}
		else if (e == 2){ Team2.add(onField.get(i));}
		onField.remove(i);
		tableau[x][y].setNb(5);
	}
	public void println(String s){
		p.println(s);
	}
	public void weatherOnIslandByTeam(){
		int e = joueur % 2;
		if(e == 0){
			printable = new Parcelle[fog.length][fog[0].length];
			for(int i = 0; i<fog.length;i++){
				for(int j = 0;j<fog[0].length; j++){
					if(isPresent(i,j,e)){
						printable[i][j] =  tableau[i][j];
					}else printable[i][j] = fog[i][j];

				}
			}
		} else if(e == 1){
			printable = new Parcelle[fog.length][fog[0].length];
			for(int i = 0; i<fog.length;i++){
				for(int j = 0;j<fog[0].length; j++){
					if(isPresent(i,j,e)){
						printable[i][j] =  tableau[i][j];
					}else printable[i][j] = fog[i][j];

				}
			}
		}
	}
	/**
	 * Verifie le positionnement de tout les objets essentiels au jeu 
	 * @param i
	 * @param j
	 * @param e
	 * @return boolean
	 */
	private boolean isPresent(int i, int j, int e) {
		if(e == 0){
			if(tableau[i][j].getNb() != 2){
				for(int k = i-1; k<=i+1; k++){
					for(int l = j-1;l<=j+1;l++){
						if(tableau[k][l].getNb() == 3 || tableau[k][l].getNb() == 6 ||  tableau[k][l].getNb() == 8 || tableau[k][l].getNb() == 10|| tableau[k][l].getNb() == 12) return true;
					}
				}
			}
		}
		if(e == 1){
			if(tableau[i][j].getNb() != 2){
				for(int k = i-1; k<=i+1; k++){
					for(int l = j-1;l<=j+1;l++){
						if(tableau[k][l].getNb() == 4 || tableau[k][l].getNb() == 7 ||  tableau[k][l].getNb() == 9 || tableau[k][l].getNb() == 11|| tableau[k][l].getNb() == 13) return true;

					}}}}
		return false;
	}

	public void setJoueur(int i){
		int e = i%2;
		this.joueur = e;
	}
	
	private boolean isClose(int x, int y, int x1, int y1){
		return false;
	}
}
