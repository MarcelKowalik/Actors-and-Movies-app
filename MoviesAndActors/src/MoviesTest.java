import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoviesTest {
	
	private Movie shinjukuIncident;
	private Movie disasterArtist;
	
	private Movies movies;

	@BeforeEach
	void setUp() throws Exception {
		// Initialising two random movies which'll be added to the movie list.
		shinjukuIncident = new Movie(
				"Shinjuku Incident",
				"2009",
				119,
				"Drama",
				"A simple Chinese immigrant wages a perilous war against one of the most powerful criminal organizations on the planet.",
				new URL("https://upload.wikimedia.org/wikipedia/en/9/94/ShinjukuIncidentPoster.jpg")
		);
		
		disasterArtist = new Movie(
				"Disaster Artist",
				"2017",
				104,
				"Drama",
				"I did not hit her, oh hai mark",
				new URL("https://images-na.ssl-images-amazon.com/images/M/MV5BOGNkMzliMGMtMDI5Ni00OTZkLTgyMTYtNzk5ZTY1NjVhYjVmXkEyXkFqcGdeQXVyNTAzMTY4MDA@._V1_UY1200_CR90,0,630,1200_AL_.jpg")
		);
		
		movies = new Movies(5);
		movies.put(shinjukuIncident.getTitle(), shinjukuIncident);
		movies.put(disasterArtist.getTitle(), disasterArtist);
	}

	@Test
	void testSearchMoviesByTitle() {
		Movies moviesFound = movies.searchMoviesByTitle("Shinjuku Incident");
		assertEquals(shinjukuIncident, moviesFound.get("Shinjuku Incident"));
		assertEquals(null, moviesFound.get("Disaster Artist"));
		
		moviesFound = movies.searchMoviesByTitle("Random");
		assertEquals(null, moviesFound.get("Shinjuku Incident"));
	}

	@Test
	void testSearchByYear() {
		Movies moviesFound = movies.searchByYear("2009");
		assertEquals(shinjukuIncident, moviesFound.get("Shinjuku Incident"));
		assertEquals(null, moviesFound.get("Disaster Artist"));
		
		moviesFound = movies.searchByYear("2017");
		assertEquals(null, moviesFound.get("Shinjuku Incident"));
		assertEquals(disasterArtist, moviesFound.get("Disaster Artist"));
	}

	@Test
	void testSearchByRunningTimes() {
		Movies moviesFound = movies.searchByRunningTimes(104);
		assertEquals(disasterArtist, moviesFound.get("Disaster Artist"));
		assertEquals(null, moviesFound.get("Shinjuku Incident"));
		
		moviesFound = movies.searchByRunningTimes(119);
		assertEquals(null, moviesFound.get("Disaster Artist"));
		assertEquals(shinjukuIncident, moviesFound.get("Shinjuku Incident"));
	}

	@Test
	void testSearchByGenre() {
		Movies moviesFound = movies.searchByGenre("Drama");
		assertEquals(disasterArtist, moviesFound.get("Disaster Artist"));
		assertEquals(shinjukuIncident, moviesFound.get("Shinjuku Incident"));
		
		moviesFound = movies.searchByGenre("Musical");
		assertEquals(null, moviesFound.get("Disaster Artist"));
		assertEquals(null, moviesFound.get("Shinjuku Incident"));
	}

}
