
public class Piegeur extends Personnage{

	public Piegeur(int equipe, Ile ile) {
		super(equipe, ile);
	}
public void CreuserPiege(int x,int y){
	if(ile.tableau[x][y].getNb() == 5){
	//	ile.tableau[x][y].setNb(nb);
	}
}
public void BoucherPiege(int x,int y){
	// if(ile.tableau[x][y].getNb() == nb){       nb = Parcelle piege
	ile.tableau[x][y].setNb(5);
	}
//}
	@Override
	public String is() {
		return "Piegeur";
	}

	@Override
	public int isTeam() {
		return equipe;
	}

}
