public class Movies extends myHashTable<String, Movie> {
	/*
	 * Movies class to store a list of movies.
	 */

	public Movies(int size) {
		super(size);
	}
	
	public String toString() {
		String str = "All movies:\n";
		
		for (int i = 0; i < size; i++) {
			for (String key : hashTable[i]) {
				str += "\n" + hashTable[i].get(key).toString() + "\n";
			}
		}
		
		return str;
	}
	
	public Movies searchMoviesByTitle(String title) {
		/*
		 * Searches for a list of movies by their title.
		 */
		Movies movies = new Movies(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Movie value = this.get(key);
				
				if (value.getTitle().contains(title)) {
					movies.put(key, value);
				}
			}
		}
		
		return movies;
	}
	
	public Movies searchByYear(String yearOfRelease) {
		/*
		 * Searches for a list of movies based on the year given.
		 */
		Movies movies = new Movies(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Movie value = this.get(key);
				
				if (value.getYearOfRelease().equals(yearOfRelease)) {
					movies.put(key, value);
				}
			}
		}
		
		return movies;
	}
	
	public Movies searchByRunningTimes(float runningTime) {
		/*
		 * Searches for a list of movies with the running time given.
		 */
		Movies movies = new Movies(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Movie value = this.get(key);
				
				if (value.getRunningTime() == runningTime) {
					movies.put(key, value);
				}
			}
		}
		
		return movies;
	}
	
	public Movies searchByGenre(String genre) {
		/*
		 * Searches for a list of movies with the genre given.
		 */
		Movies movies = new Movies(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Movie value = this.get(key);
				
				if (value.getGenre().equals(genre)) {
					movies.put(key, value);
				}
			}
		}
		
		return movies;
	}
}
