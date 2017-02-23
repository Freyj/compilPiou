package structure;

public class Union extends Noeud{
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

	@Override
	public String imprimNoeud(int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<indent; ++i) {
			sb.append("-");
		}
		sb.append("> Conc\n");
		int newIndent = indent + 5;
		sb.append(getDroit().imprimNoeud(newIndent));
		sb.append(getGauche().imprimNoeud(newIndent));
		
		return sb.toString();
	}


}
