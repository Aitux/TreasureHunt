public class Explorateur extends Personnage {
	
		
	
	public Explorateur(int equipe, Ile ile) {
		super(equipe, ile);
	}
	public void SouleverRocher(int x , int y){
		if (ile.tableau[x][y].getNb()==1 && (PosX == x-1 || PosX == x+1 || PosY == y-1 || PosY == y+1) ){
				if(ile.tableau[x][y].cle){
					this.cl� = true;
					ile.tableau[x][y].cle = false;
			}
				else if(ile.tableau[x][y].coffre && ile.tableau[x][y].coffre == false){
					System.out.println("Il y avait un tr�sor ici avant mais il a d�j� �tait pris");
				}
				else if(ile.tableau[x][y].coffre && ile.tableau[x][y].coffre == true && this.cl� == false){
					System.out.println("Vous remarquer un coffre mais vous n'avez malheureusement pas la cl�");
				}
				else if(ile.tableau[x][y].coffre && ile.tableau[x][y].coffre == true && this.cl� == false){
					this.tr�sor = true;
					ile.tableau[x][y].coffre = false;
				}

		}
	}
}
