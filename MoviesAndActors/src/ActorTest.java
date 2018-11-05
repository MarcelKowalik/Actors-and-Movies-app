import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActorTest {
	
	private Movies movies;
	private Actor validActor;
	private Actor invalidActor;

	@BeforeEach
	void setUp() throws Exception {
		movies = new Movies(2);
		
		Movie movie1 = new Movie("randommovie", "2011", Float.parseFloat("122.2"), "randomgenre", "randomdescription", new URL("http://www.random.org"));
		Movie movie2 = new Movie("anothermovie", "2005", 100, "anothergenre", "anotherdescription", new URL("http://wit.ie"));
		
		movies.put(movie1.getTitle(), movie1);
		movies.put(movie2.getTitle(), movie2);
		
		/*
		 * Required arguments;
		 * String name, String dateofbirth, char gender, String nationality
		 */
		validActor = new Actor(
				"Wei Kit Wong",
				"14/05/1999",
				'M',
				"Irish",
				movies
		);
		
		invalidActor = new Actor(
				"Marcel Kowalik",
				"01011997", // Must follow DD/MM/YYYY format.
				'M',
				"Polish",
				movies
		);
	}

	@Test
	void testConstructor() {
		assertNotNull(validActor);
		
		assertEquals("Marcel Kowalik", invalidActor.getName());
		assertEquals(null, invalidActor.getDateofbirth());
		assertEquals('M', invalidActor.getGender());
		assertEquals("Polish", invalidActor.getNationality());
		assertEquals(movies, invalidActor.getMovies());
	}
	
	@Test
	void testSetters() {
		invalidActor.setName("Wei Kit Wong");
		assertEquals("Wei Kit Wong", invalidActor.getName());
		
		invalidActor.setDateofbirth("14/05/1999");
		assertEquals("14/05/1999", invalidActor.getDateofbirth());
		
		invalidActor.setGender('F');
		assertEquals('F', invalidActor.getGender());
		
		invalidActor.setMovies(movies);
		assertEquals(movies, invalidActor.getMovies());
	}
}
