import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import tps.MyMouseEvent;
import tps.Plateau;

public class NewGame {
	private Icon icon, ruleIc;
	private static Integer tailleIle = new Integer(10);
	private static double tauxRocs;


	/**
	 * Le constructeur gère le lancement de la partie.
	 */
	public NewGame(){
		/**
		 * Attention tailleIle prend en compte l'eau (i.e. que une ile de 15 ne fera que 13 de large + 1 d'eau de chaque coté)
		 */

		while(!Menu()){}
		String[] img = new String[] {"images/rocher.png","images/mer.png","images/1.navire.png","images/2.navire.png","images/arbre.png","images/1.explorateur.png","images/2.explorateur.png"};
		Plateau plateau = new Plateau(img,tailleIle+2, true);
		Ile ile = new Ile(tailleIle+2);
		ile.setTauxRoc(tauxRocs);
		ile.genererIle();
		ile.checkIle();
		//		ile.RencontrePossible(ile.getTableau());
		plateau.setJeu(ile.getTableau());
		plateau.affichage();
		ile.initChamp();
//		ile.winCondition();
		while(true){
			plateau.setJeu(ile.getTableau());
			deplacer(plateau, ile);
		}
	}

	private int[] getPerso(Plateau s){
		int[] coo = new int[2];
		s.println("Séléctionnez une case");
		InputEvent event = s.waitEvent();
		coo[0]= s.getX((MouseEvent)	event);
		coo[1]= s.getY((MouseEvent) event);
		return coo;
	}
	

	
	private int[] getFinal(Plateau s){
		int[] coo = new int[2];
		s.println("Faites une action");
		InputEvent event = s.waitEvent();
		coo[0]= s.getX((MouseEvent)	event);
		coo[1]= s.getY((MouseEvent) event);
		return coo;
	}

	public void deplacer(Plateau s, Ile ile){
		int[] A = getPerso(s);
		int[] B = getFinal(s);
		System.out.println(A[0]+" "+A[1]+" "+B[0]+" "+B[1]);
	}


	/**
	 * Gère l'affichage du menu
	 * @return un boolean qui permet de savoir quand une partie est lancé
	 */

	@SuppressWarnings("static-access")
	private boolean Menu(){
		String[] test = {"Nouvelle Partie", "Rêgles du jeu", "Quitter"};
		icon = new ImageIcon("images/steve.png");
		int choice1 = JOptionPane.showOptionDialog(null, "Que souhaitez vous faire ?", "TreasureHunt 2016",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, icon, test, test[0]);
		switch(choice1){
		case 0:
			JOptionPane jop = new JOptionPane();
			String[] taille = {"10 cases", "15 cases", "20 cases"};
			String rang = (String) jop.showInputDialog(null,"Quelle taille voulez vous pour l'île ? :","Option", JOptionPane.QUESTION_MESSAGE, null, taille, taille[0]);
		//	jop.showMessageDialog(null, "Vous avez demandé une île de "+rang,"Option", JOptionPane.INFORMATION_MESSAGE);
			tailleIle = Integer.parseInt(rang.substring(0, 2));	
			String[] tauxRoc = new String[]{"10%", "20%","30%","40%"};
			rang = (String) jop.showInputDialog(null,"Quel taux de rocher voulez vous pour l'ile ? : ", "Taux Rocher", JOptionPane.QUESTION_MESSAGE, null, tauxRoc, tauxRoc[0]);
			tauxRocs = ((double) (Integer.parseInt(rang.substring(0,2))))/100;
			return true;

		case 1: 
			//			JOptionPane.showMessageDialog(null, "WIP");
			ruleIc = new ImageIcon("images/rule.png");
			JOptionPane.showMessageDialog(null, null, "Rules Treasure Hunt", JOptionPane.INFORMATION_MESSAGE, ruleIc);
			return false;
		case 2: System.exit(0);
		}
		return false;
	}


}
