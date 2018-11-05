public class Actors extends myHashTable<String, Actor> {
	/*
	 * Actors class to store a list of actors. Contains methods to search for actor with specific properties.
	 */
	
	public Actors(int size) {
		super(size);
	}
	
	public String toString() {
		String str = "All actors:\n";
		
		for (int i = 0; i < size; i++) {
			for (String key : hashTable[i]) {
				str += "\n" + hashTable[i].get(key).toString() + "\n";
			}
		}
		
		return str;
	}
	
	public Actors searchActorsByName(String name) {
		/*
		 * Searches for a list of actors by their name.
		 */
		Actors actors = new Actors(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Actor value = this.get(key);
				
				if (value.getName().contains(name)) {
					actors.put(key, value);
				}
			}
		}
		
		return actors;
	}
	
	public Actors searchByDateOfBirth(String dateofbirth) {
		/*
		 * Searches for a list of actors by their date of birth.
		 */
		Actors actors = new Actors(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Actor value = this.get(key);
				
				if (value.getDateofbirth().contains(dateofbirth)) {
					actors.put(key, value);
				}
			}
		}
		
		return actors;
	}
	
	public Actors searchByGender(char gender) {
		/*
		 * Searches for a list of actors by their gender.
		 */
		Actors actors = new Actors(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Actor value = this.get(key);
				
				if (value.getGender() == gender) {
					actors.put(key, value);
				}
			}
		}
		
		return actors;
	}
	
	public Actors searchByNationality(String nationality) {
		/*
		 * Searches for a list of actors by their nationality.
		 */
		Actors actors = new Actors(this.size);
		
		for (int i = 0; i < this.size; i++) {
			for (String key : this.hashTable[i]) {
				Actor value = this.get(key);
				
				if (value.getNationality().equals(nationality)) {
					actors.put(key, value);
				}
			}
		}
		
		return actors;
	}
}
