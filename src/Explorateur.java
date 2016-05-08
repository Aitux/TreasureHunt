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
					ile.println("Vous avez trouvé la clef !!");
			}
				else if(ile.tableau[x][y].getCoffre() && !ile.tableau[x][y].getTrésor()){
					ile.println("Il y avait un trésor ici avant mais il a déjà était pris");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTrésor() == true && this.clé == false){
					ile.println("Vous remarquez un coffre mais vous n'avez malheureusement pas la clef");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTrésor() == true && this.clé == true){
					this.trésor = true;
					ile.tableau[x][y].setTrésor(false);
					ile.println("Le trésor est à vous, ramenez le à votre navire ! Vite !");
				}
				perteEnergie(4);
		}
	}
	@Override
	public String is() {
		// TODO Auto-generated method stub
		return "Explorateur";
	}
	@Override
	public int isTeam() {
		// TODO Auto-generated method stub
		return equipe;
	}
}
