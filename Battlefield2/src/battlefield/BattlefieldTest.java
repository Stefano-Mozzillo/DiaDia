package battlefield;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/* Modificare la classe Position affinche' 
 * il primo test abbia successo (vedi DOMANDA 1) 
 */
public class BattlefieldTest {
	
	private Battlefield field;
	private Battlefield field2;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
		this.field2 = new Battlefield(2);
		Robot w= new Walker(new Position(0, 0));
		Robot c= new Chaser(new Position(1, 0));
		field2.addRobot(w);
		field2.addRobot(c);
	}

	@Test
	public void testAddWalker() {
		assertEquals(0, this.field.getAllRobot().size());
		this.field.addRobot(new Walker(new Position(0,0)));
		assertEquals(1, this.field.getAllRobot().size());
	}
	
	@Test
	public void testRaggruppaRobotDiDueTipiDiversi() {
		Map<Class, Set<Robot>> mappa = field2.raggruppaRobotPerTipo();
		assertEquals(mappa.size(),2);
		assertTrue(mappa.containsKey(Walker.class));
		assertTrue(mappa.containsKey(Chaser.class));
		assertEquals(mappa.get(Walker.class).size(), 1);
		assertEquals(mappa.get(Chaser.class).size(), 1);
	}

}
