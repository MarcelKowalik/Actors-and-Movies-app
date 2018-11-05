public class Actor {
	/*
	 * Actor class to store data about a single actor.
	 */
     
    private String name;
    private String dateofbirth;
    private char gender;
    private String nationality;
    private Movies movies;
     
    public Actor(String name, String dateofbirth, char gender, String nationality) {
    	setName(name);
    	setDateofbirth(dateofbirth);
    	setGender(gender);
    	setNationality(nationality);
        setMovies(new Movies(5));
    }
    
    public Actor(String name, String dateofbirth, char gender, String nationality, Movies movies) {
    	setName(name);
    	setDateofbirth(dateofbirth);
    	setGender(gender);
    	setNationality(nationality);
    	setMovies(movies);
    }
    
    public String toString() {
    	return name + " (" + gender + ")\nNationality: " + nationality + ",\nDate of Birth: " + dateofbirth;
    }
    
    // Getters and Setters.
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDateofbirth() {
        return dateofbirth;
    }
 
    public void setDateofbirth(String dateofbirth) {
    	// Follows a DD/MM/YYYY format.
    	if (dateofbirth.charAt(2) == '/' && dateofbirth.charAt(5) == '/' && dateofbirth.length() == 10) {
    		this.dateofbirth = dateofbirth;
    	}
    }
 
    public char getGender() {
        return gender;
    }
 
    public void setGender(char gender) {
        this.gender = gender;
    }
 
    public String getNationality() {
        return nationality;
    }
 
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}
}