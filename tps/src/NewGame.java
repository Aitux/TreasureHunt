package src;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class NewGame {
	private Icon icon, ruleIc;
	private Integer tailleIle = new Integer(10);
	private double tauxRocs;
	private static boolean test;
	private boolean sandbox = false;
	/**
	 * Le constructeur g�re le lancement de la partie.
	 */
	public NewGame(){
		test = true;
		
		int equipe = 0;
		/**
		 * Attention tailleIle prend en compte l'eau (i.e. que une ile de 15 ne fera que 13 de large + 1 d'eau de chaque cot�)
		 */

		while(!Menu()){}
		String[] img = new String[] {"rocher.png","mer.png","1.navire.png","2.navire.png","arbre.png","1.explorateur.png",
				"2.explorateur.png","1.voleur.png","2.voleur.png","1.guerrier.png",
				"2.guerrier.png","1.piegeur.png", "2.piegeur.png","fog.png"};
		Plateau plateau = new Plateau(img,tailleIle+2, true);
		Ile ile = new Ile(tailleIle+2);
		ile.setTauxRoc(tauxRocs);
	//	if(sandbox) ile.sandbox();
		ile.genererIle();
		ile.checkIle();
		ile.addPlateau(plateau);
//		ile.RencontrePossible(ile.getTableau());
		ile.weatherOnIslandByTeam();
		plateau.setJeu(ile.getTableau());
		plateau.affichage();
		ile.initChamp();
//		ile.winCondition();
		while(!WellMet(equipe, ile)){
			ile.setJoueur(equipe);
			ile.weatherOnIslandByTeam();	
			while(test){
			int[][] comp1 = ile.getTableau();
			plateau.setJeu(ile.getTableau());
			ile.caseClicked(plateau);
			int[][] comp2 = ile.getTableau();
			ile.weatherOnIslandByTeam();
			if(!compareTableau(comp1, comp2)) test = false; 
			}
			equipe++;
			test = true;
		}
		JOptionPane.showMessageDialog(null, "Le joueur "+((equipe%2)+1)+"a gagn� !");
	}



	private boolean WellMet(int equipe, Ile ile) {
		if(ile.GGWP()) return true;
		return false;
	}



	/**
	 * G�re l'affichage du menu
	 * @return un boolean qui permet de savoir quand une partie est lanc�
	 */

	@SuppressWarnings("static-access")
	private boolean Menu(){
		String[] test = {"Nouvelle Partie", "R�gles du jeu","SandBox", "Quitter"};
		icon = new ImageIcon("steve.png");
		int choice1 = JOptionPane.showOptionDialog(null, "Que souhaitez vous faire ?", "TreasureHunt 2016",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, icon, test, test[0]);
		switch(choice1){
		case 0:
			JOptionPane jop = new JOptionPane();
			String[] taille = {"10 cases", "15 cases", "20 cases"};
			String rang = (String) jop.showInputDialog(null,"Quelle taille voulez vous pour l'�le ? :","Option", JOptionPane.QUESTION_MESSAGE, null, taille, taille[0]);
			//	jop.showMessageDialog(null, "Vous avez demand� une �le de "+rang,"Option", JOptionPane.INFORMATION_MESSAGE);
			tailleIle = Integer.parseInt(rang.substring(0, 2));	
			String[] tauxRoc = new String[]{"10%", "20%","30%","40%"};
			rang = (String) jop.showInputDialog(null,"Quel taux de rocher voulez vous pour l'ile ? : ", "Taux Rocher", JOptionPane.QUESTION_MESSAGE, null, tauxRoc, tauxRoc[0]);
			tauxRocs = ((double) (Integer.parseInt(rang.substring(0,2))))/100;
			return true;

		case 1: 
			//	JOptionPane.showMessageDialog(null, "WIP");
			ruleIc = new ImageIcon("rule.png");
			JOptionPane.showMessageDialog(null, null, "Rules Treasure Hunt", JOptionPane.INFORMATION_MESSAGE, ruleIc);
			return false;
		case 3: System.exit(0);
		case 2: tailleIle = 5; tauxRocs = 0.1; this.sandbox = true;return true;
		}
		return false;
	}

	private boolean compareTableau(int[][] t1, int[][] t2){
	//	System.out.println(tableauToString(t2));
	//	System.out.println(tableauToString(t1));
		for(int i = 0; i<t1.length;i++){
			for(int j = 0 ; j<t1[0].length;j++){
				if(t1[i][j]!=t2[i][j]) return false;
			}
		}
		return true;
		
	}
	public String tableauToString(int[][] t1){
		String s = "";
		for(int i = 0; i<t1.length;i++){
			s += "\n";
			for(int j = 0 ; j<t1[0].length;j++){
				s+=t1[i][j]+ "|";
			}
		}
		return s;
	}
}
