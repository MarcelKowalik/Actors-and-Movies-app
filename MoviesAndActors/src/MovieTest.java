import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieTest {
	
	private Actors actors;
	private Movie validMovie;
	private Movie invalidMovie;

	@BeforeEach
	void setUp() throws Exception {
		// Initialising all the actors in the movie.
		actors = new Actors(5);
		
		Actor jackieChan = new Actor("Jackie Chan", "01/01/1954", 'M', "Chinese");
		Actor danielWu = new Actor("Daniel Wu", "01/01/1974", 'M', "Chinese");
		
		actors.put(jackieChan.getName(), jackieChan);
		actors.put(danielWu.getName(), danielWu);
		
		/*
		 * Required arguments;
		 * String title, String yearOfRelease, float runningTime, String genre, String description, URL posterImage
		 */
		validMovie = new Movie(
				"Shinjuku Incident",
				"2009",
				119,
				"Drama",
				"A simple Chinese immigrant wages a perilous war against one of the most powerful criminal organizations on the planet.",
				new URL("https://upload.wikimedia.org/wikipedia/en/9/94/ShinjukuIncidentPoster.jpg"),
				actors
		);
		
		invalidMovie = new Movie(
				"Shinjuku Incident",
				"notAYear",
				191, // More than 3 hours.
				"Drama",
				"A simple Chinese immigrant wages a perilous war against one of the most powerful criminal organizations on the planet.",
				new URL("https://upload.wikimedia.org/wikipedia/en/9/94/ShinjukuIncidentPoster.jpg"),
				actors
		);
	}
	
	@Test
	void testConstructor() {
		assertNotNull(validMovie);
		
		assertEquals("Shinjuku Incident", invalidMovie.getTitle());
		assertEquals(null, invalidMovie.getYearOfRelease());
		assertEquals(0, invalidMovie.getRunningTime());
		assertEquals("Drama", invalidMovie.getGenre());
		assertEquals("A simple Chinese immigrant wages a perilous war against one of the most powerful criminal organizations on the planet.", invalidMovie.getDescription());
		assertEquals(actors, invalidMovie.getActors());
	}
	
	@Test
	void testSetters() {
		invalidMovie.setTitle("Shinjuku Incident");
		assertEquals("Shinjuku Incident", invalidMovie.getTitle());
		
		invalidMovie.setYearOfRelease("2009");
		assertEquals("2009", invalidMovie.getYearOfRelease());
		
		invalidMovie.setRunningTime(119);
		assertEquals(119, invalidMovie.getRunningTime());
		
		invalidMovie.setGenre("Drama");
		assertEquals("Drama", invalidMovie.getGenre());
		
		invalidMovie.setDescription("A simple Chinese immigrant wages a perilous war against one of the most powerful criminal organizations on the planet.");
		assertEquals("A simple Chinese immigrant wages a perilous war against one of the most powerful criminal organizations on the planet.", invalidMovie.getDescription());
		
		invalidMovie.setActors(actors);
		assertEquals(actors, invalidMovie.getActors());
	}
}
