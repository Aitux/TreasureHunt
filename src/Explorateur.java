public class Explorateur extends Personnage {
	
	
	public Explorateur(int equipe, Ile ile) {
		super(equipe, ile);
	}
	public void SouleverRocher(int x , int y){
		if (ile.tableau[x][y].getNb()==1 && (PosX == x-1 || PosX == x+1 || PosY == y-1 || PosY == y+1) ){
				if(ile.tableau[x][y].getCle()){
					this.cl� = true;
					ile.tableau[x][y].setCle(false);
			}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTr�sor() == false){
					System.out.println("Il y avait un tr�sor ici avant mais il a d�j� �tait pris");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTr�sor() == true && this.cl� == false){
					System.out.println("Vous remarquer un coffre mais vous n'avez malheureusement pas la cl�");
				}
				else if(ile.tableau[x][y].getCoffre() && ile.tableau[x][y].getTr�sor() == true && this.cl� == false){
					this.tr�sor = true;
					ile.tableau[x][y].setTr�sor(false);
				}

		}
	}
}
