package battlefield;

public class Chaser extends Robot{

	
	
	public Chaser(Position p) {
		super(p);
	}
	
	
	
	
	@Override
	public Position decidiMossa(Battlefield field) {
	    Robot inseguito = cercaAvversario(field);
	    if (inseguito==null) 
	        return null; // nessuno da inseguire
	    
	    // Cerca una posizione libera VICINO all'avversario
	    return field.posizioneLiberaVicino(inseguito.getPosizione());
	}

	private Robot cercaAvversario(Battlefield field) {
		for(Position p : field.adiacenti(this.getPosizione())) {
			Robot vicino = field.getRobot(p);
			if (isAvversario(vicino) && vicino!=null) {
				return vicino;
			}
		}
		return null;
	}
	
	@Override
	public Chaser creaClone(Position p) {
		return new Chaser(p);
	}

	private boolean isAvversario(Object avvistato) {
		return avvistato.getClass() != this.getClass();
		 /* è sicuramente un Walker??? per ora SI! */
	}



	

}


