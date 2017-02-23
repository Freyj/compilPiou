package structure;

public class Star extends Noeud{
	private Noeud stare;
	
	public Star(Noeud st) {
		stare = st;
	}
	
	public Noeud getStare() {
		return stare;
	}

	@Override
	public String imprimNoeud(int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<indent; ++i) {
			sb.append("-");
		}
		sb.append("> Star\n");
		int newIdent = indent + 5;
		sb.append(stare.imprimNoeud(newIdent));
		
		
		return sb.toString();
	}
}
