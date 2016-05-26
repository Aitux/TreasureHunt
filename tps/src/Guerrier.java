package src;
import java.util.Random;

public class Guerrier extends Personnage{

	public Guerrier(int equipe, Ile ile) {
		super(equipe, ile);
		}
public void AttaqueEpee(Personnage P){
	P.energie = P.energie - 5; // 100% chance de toucher et valeur 5 à modifier ?
}
public void AttaqueMasse(Personnage P){
	Random r = new Random();
	int valeur = r.nextInt(100);
	if(valeur > 50){
		P.energie = P.energie - 10; //50% de chance de toucher et valeur 10
	}
	else {
		System.out.println("Echec de l'attaque avec une masse");
	}
	
}
	@Override
	public String is() {
		return "Guerrier";
	}

	@Override
	public int isTeam() {
		return equipe;
	}

}
