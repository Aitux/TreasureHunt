
public class Voleur extends Personnage {

	Voleur(int equipe, Ile ile) {
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
	}

}
