public class Explorateur extends Personnage {
	
	
	public Explorateur(int equipe, Ile ile) {
		super(equipe, ile);
	}
	public void SouleverRocher(int x , int y){
	//	System.out.println("MercyMercyPLS");
		if (ile.tableau[x][y].getNb()==1){
	//		System.out.println("Falafel");
				if(ile.tableau[x][y].getCle()){
					this.clé = true;
					ile.tableau[x][y].setCle(false);
					System.out.println("Vous avez trouvé la clef !!");
			}
				else if(ile.tableau[x][y].getCoffre() && !ile.tableau[x][y].getTrésor()){
					System.out.println("Il y avait un trésor ici avant mais il a déjà était pris");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTrésor() == true && this.clé == false){
					System.out.println("Vous remarquez un coffre mais vous n'avez malheureusement pas la cléx");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTrésor() == true && this.clé == true){
					this.trésor = true;
					ile.tableau[x][y].setTrésor(false);
					System.out.println("Le trésor est à vous, ramenez le à votre navire !");
				}

		}
	}
}
