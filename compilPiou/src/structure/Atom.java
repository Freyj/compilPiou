package structure;

public class Atom extends Noeud{
	
	private String code;
	private int action;
	private AtomType type;
	
	public Atom(String co, int ac, AtomType ty) {
		code = co;
		action = ac;
		type = ty;
	}
	
	public String getCode() {
		return code;
	}
	
	public int getAction() {
		return action;
	}
	
	public AtomType getType(){
		return type;
	}

}
