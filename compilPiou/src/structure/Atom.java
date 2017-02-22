package structure;

public class Atom {
	
	private int code;
	private int action;
	private AtomType type;
	
	public Atom(int co, int ac, AtomType ty) {
		code = co;
		action = ac;
		type = ty;
	}
	
	public int getCode() {
		return code;
	}
	
	public int getAction() {
		return action;
	}
	
	public AtomType getType(){
		return type;
	}

}
