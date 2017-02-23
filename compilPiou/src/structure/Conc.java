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

	@Override
	public boolean equals(Object n) {
		boolean result = false;
		if (n instanceof Conc) {
			Conc test = (Conc) n;
			if (getDroit().equals(test.getDroit())){
				if (getGauche().equals(test.getGauche())) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
