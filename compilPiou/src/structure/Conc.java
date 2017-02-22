package structure;

public class Conc extends Noeud{
	private Noeud droit;
	private Noeud gauche;
	
	public Conc(Noeud dr, Noeud ga) {
		droit = dr;
		gauche = ga;
	}
	
	public Noeud getGauche() {
		return gauche;
	}
	
	public Noeud getDroit() {
		return droit;
	}
}
