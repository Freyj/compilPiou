package structure;

public class Conc extends Noeud{
	private Noeud gauche;
	private Noeud droit;
	
	public Conc(Noeud ga, Noeud dr) {
		gauche = ga;
		droit = dr;
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
		sb.append(getGauche().imprimNoeud(newIndent));
		sb.append(getDroit().imprimNoeud(newIndent));
		
		return sb.toString();
		
	}

	@Override
	public boolean equals(Object n) {
		boolean result = false;
		if (n instanceof Conc) {
			Conc test = (Conc) n;
			if (getGauche().equals(test.getGauche())){
				if (getDroit().equals(test.getDroit())) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
