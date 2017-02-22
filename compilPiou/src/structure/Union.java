package structure;

public class Union {
	private Noeud droit;
	private Noeud gauche;
	
	public Union(Noeud dr, Noeud ga) {
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
