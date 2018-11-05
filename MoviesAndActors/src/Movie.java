import java.net.URL;

public class Movie {
	/*
	 * Movie class to store details about a single movie.
	 */
	
	private String title;
	private String yearOfRelease;
	private float runningTime; // Minutes.
	private String genre;
	private String description;
	private URL posterImage; // URL to poster image.
	private Actors actors;
	
	public Movie(String title, String yearOfRelease, float runningTime, String genre, String description, URL posterImage) {
		setTitle(title);
		setYearOfRelease(yearOfRelease);
		setRunningTime(runningTime);
		setGenre(genre);
		setDescription(description);
		setPosterImage(posterImage);
		setActors(new Actors(5));
	}
	
	public Movie(String title, String yearOfRelease, float runningTime, String genre, String description, URL posterImage, Actors actors) {
		setTitle(title);
		setYearOfRelease(yearOfRelease);
		setRunningTime(runningTime);
		setGenre(genre);
		setDescription(description);
		setPosterImage(posterImage);
		setActors(actors);
	}
	
	public String toString() {
		return title + " (" + yearOfRelease + "):\n" + genre + ",\n" + description + ",\nRunning time is " + runningTime + " minutes.";
	}
	
	// Getters and Setters.

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(String yearOfRelease) {
		// Checking if the string is a year and if it has no letters. UPPERCASE = lowercase is impossible unless all values are numbers.
		if (yearOfRelease.length() == 4 && yearOfRelease.toUpperCase().equals(yearOfRelease.toLowerCase())) {
			this.yearOfRelease = yearOfRelease;
		}
	}

	public float getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(float runningTime) {
		// Making sure the running time doesn't exceed 3 hours.
		if (runningTime < 180) {
			this.runningTime = runningTime;
		}
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public URL getPosterImage() {
		return posterImage;
	}

	public void setPosterImage(URL posterImage) {
		this.posterImage = posterImage;
	}
	
	public Actors getActors() {
		return actors;
	}

	public void setActors(Actors actors) {
		this.actors = actors;
	}
}
