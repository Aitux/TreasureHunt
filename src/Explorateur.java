public class Explorateur extends Personnage {
	
	
	public Explorateur(int equipe, Ile ile) {
		super(equipe, ile);
	}
	public void SouleverRocher(int x , int y){
		if (ile.tableau[x][y].getNb()==1 && (PosX == x-1 || PosX == x+1 || PosY == y-1 || PosY == y+1) ){
				if(ile.tableau[x][y].getCle()){
					this.clé = true;
					ile.tableau[x][y].setCle(false);
			}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTrésor() == false){
					System.out.println("Il y avait un trésor ici avant mais il a déjà était pris");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTrésor() == true && this.clé == false){
					System.out.println("Vous remarquer un coffre mais vous n'avez malheureusement pas la clé");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTrésor() == true && this.clé == false){
					this.trésor = true;
					ile.tableau[x][y].setTrésor(false);
				}

		}
	}
}
