
public class Voleur extends Personnage {

	Voleur(int equipe) {
		super(equipe);
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
	}

}
