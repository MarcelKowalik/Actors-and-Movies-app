import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActorsTest {
	
	private Actor andy;
	private Actor marcel;
	
	private Actors actors;

	@BeforeEach
	void setUp() throws Exception {
		// Initialising two actors which'll be added to the actors list.
		andy = new Actor("Andy", "14/05/1999", 'M', "Irish");
		marcel = new Actor("Marcel", "02/01/1997", 'M', "Polish");
		
		actors = new Actors(5);
		actors.put(andy.getName(), andy);
		actors.put(marcel.getName(), marcel);
	}

	@Test
	void testSearchActorsByName() {
		Actors actorsFound = actors.searchActorsByName("Andy");
		assertEquals(andy, actorsFound.get("Andy"));
		assertEquals(null, actorsFound.get("Marcel"));
		
		actorsFound = actors.searchActorsByName("Random");
		assertEquals(null, actorsFound.get("Andy"));
	}
	
	@Test
	void testSearchByDateOfBirth() {
		Actors actorsFound = actors.searchByDateOfBirth("14/05/1999");
		assertEquals(andy, actorsFound.get("Andy"));
		assertEquals(null, actorsFound.get("Marcel"));
		
		actorsFound = actors.searchByDateOfBirth("05/05/1999");
		assertEquals(null, actorsFound.get("Andy"));
	}
	
	@Test
	void testSearchByGender() {
		Actors actorsFound = actors.searchByGender('M');
		assertEquals(marcel, actorsFound.get("Marcel"));
		assertEquals(andy, actorsFound.get("Andy"));
		
		actorsFound = actors.searchByGender('F');
		assertEquals(null, actorsFound.get("Marcel"));
	}
	
	@Test
	void testSearchByNationality() {
		Actors actorsFound = actors.searchByNationality("Polish");
		assertEquals(marcel, actorsFound.get("Marcel"));
		assertEquals(null, actorsFound.get("Andy"));
		
		actorsFound = actors.searchByNationality("Random");
		assertEquals(null, actorsFound.get("Marcel"));
	}
}
