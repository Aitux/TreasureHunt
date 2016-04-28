
public class Voleur extends Personnage {
	Voleur(int equipe, Ile ile){
		super(equipe, ile);
	}
	public void Vol(Personnage P){
		if (P.clé){
			this.clé = true;
			P.clé = false;
		}
		if(P.trésor){
			this.trésor = true;
			P.trésor = false;
		}
		perteEnergie(10);
	}
	@Override
	public String is() {
		// TODO Auto-generated method stub
		return "Voleur";
	}
	@Override
	public int isTeam() {
		// TODO Auto-generated method stub
		return equipe;
	}

}
