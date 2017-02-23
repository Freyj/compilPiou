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

	@Override
	public String imprimNoeud(int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<indent; ++i) {
			sb.append("-");
		}
		sb.append("> " + "Atome ");
		sb.append(getCode());
		sb.append(" ");
		sb.append(getAction());
		sb.append(" ");
		sb.append(getType());
		sb.append("\n");
		
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof Atom) {
			Atom test = (Atom) o;
			if (getAction() == test.getAction()) {
				if (getCode().equals(test.getCode())) {
					result = getType().equals(test.getType());
				}
			}
		}
		return result;
	}

}
