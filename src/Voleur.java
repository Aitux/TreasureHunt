
public class Voleur extends Personnage {
	Voleur(int equipe, Ile ile){
		super(equipe, ile);
	}
	public void Vol(Personnage P){
		if (P.cl�){
			this.cl� = true;
			P.cl� = false;
		}
		if(P.tr�sor){
			this.tr�sor = true;
			P.tr�sor = false;
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
