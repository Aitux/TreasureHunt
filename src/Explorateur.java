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
					ile.println("Vous avez trouv� la clef !!");
			}
				else if(ile.tableau[x][y].getCoffre() && !ile.tableau[x][y].getTr�sor()){
					ile.println("Il y avait un tr�sor ici avant mais il a d�j� �tait pris");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTr�sor() == true && this.cl� == false){
					ile.println("Vous remarquez un coffre mais vous n'avez malheureusement pas la clef");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTr�sor() == true && this.cl� == true){
					this.tr�sor = true;
					ile.tableau[x][y].setTr�sor(false);
					ile.println("Le tr�sor est � vous, ramenez le � votre navire ! Vite !");
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
