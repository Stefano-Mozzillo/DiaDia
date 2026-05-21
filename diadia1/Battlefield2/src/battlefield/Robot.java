package battlefield;

public abstract class Robot implements Comparable<Robot> {
	private Position posizione;
	private int longevita;

	public Robot(Position p) {
		posizione = p;
		this.longevita = 0;
	}

	public Position getPosizione() {
		return this.posizione;
	}

	public int incrementaLongevita() {
		return ++this.longevita;
	}

	public int getLongevita() {
		return this.longevita;
	}
	public void passo(Battlefield field) {
		Position nuova = this.decidiMossa(field);
		if (nuova!=null) {
			Robot clone = creaClone(nuova);
			field.addRobot(clone);
		}
		this.incrementaLongevita();
		}
	
	public abstract Robot creaClone(Position p);
	public abstract Position decidiMossa(Battlefield field);

	@Override
	public int compareTo(Robot obj) {
		Robot that= (Robot)obj;
		return this.longevita-that.longevita;
	}

	
}

