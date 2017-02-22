package structure;

public class Un extends Noeud{
	private Noeud unique;
	
	public Un(Noeud noeud) {
		unique = noeud;
	}
	
	public Noeud getNoeud() {
		return unique;
	}
	
	
}
