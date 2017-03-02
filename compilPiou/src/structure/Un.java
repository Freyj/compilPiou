package structure;

public class Un extends Noeud{
	private Noeud unique;
	
	public Un(Noeud noeud) {
		unique = noeud;
	}
	
	public Noeud getNoeud() {
		return unique;
	}

	@Override
	public String imprimNoeud(int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<indent; ++i) {
			sb.append("-");
		}
		sb.append("> Unique\n");
		int newIdent = indent + 5;
		sb.append(unique.imprimNoeud(newIdent));		
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}
	
	
}
