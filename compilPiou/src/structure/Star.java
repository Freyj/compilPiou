package structure;

public class Star extends Noeud{
	private Noeud stare;
	
	public Star(Noeud st) {
		stare = st;
	}
	
	public Noeud getStare() {
		return stare;
	}
}
