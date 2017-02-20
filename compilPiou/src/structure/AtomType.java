package structure;

public abstract class AtomType {
	protected String nom;
	protected boolean isTerminal;
	
	public String getNom() {
		return nom;
	}
	
	public boolean isTerminal() {
		return isTerminal;
	}
}
