public class Explorateur extends Personnage {
	
	
	public Explorateur(int equipe, Ile ile) {
		super(equipe, ile);
	}
	public void SouleverRocher(int x , int y){
	//	System.out.println("MercyMercyPLS");
		if (ile.tableau[x][y].getNb()==1){
	//		System.out.println("Falafel");
				if(ile.tableau[x][y].getCle()){
					this.cl� = true;
					ile.tableau[x][y].setCle(false);
					System.out.println("Vous avez trouv� la clef !!");
			}
				else if(ile.tableau[x][y].getCoffre() && !ile.tableau[x][y].getTr�sor()){
					System.out.println("Il y avait un tr�sor ici avant mais il a d�j� �tait pris");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTr�sor() == true && this.cl� == false){
					System.out.println("Vous remarquez un coffre mais vous n'avez malheureusement pas la cl�x");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTr�sor() == true && this.cl� == true){
					this.tr�sor = true;
					ile.tableau[x][y].setTr�sor(false);
					System.out.println("Le tr�sor est � vous, ramenez le � votre navire !");
				}

		}
	}
}
