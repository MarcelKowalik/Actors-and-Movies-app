public class myHashTable<K, V> {
	/*
	 * Hash Table implementation using multiple Linked Lists stored in an array.
	 * Note that in this hashtable, no duplicate keys are allowed.
	 */
	
	protected myLinkedList<K, V>[] hashTable;
	// private myArrayList<K, V>[] hashTable;
	protected int size; // The length of the linked list array.
	protected int numberOfContents; // The number of items added into the hashtable.
	
	@SuppressWarnings("unchecked")
	public myHashTable(int size) {
		hashTable = new myLinkedList[size];
		// hashTable = new myArrayList[size];
		
		// Initialising all the linked lists in the array.
		for (int i = 0; i < size; i++) {
			hashTable[i] = new myLinkedList<K, V>();
			// hashTable[i] = new myArrayList<K, V>();
		}
		
		this.size = size;
	}
	
	public void put(K key, V value) {
		/*
		 * Adds a new item to the hash table. Uses the hash function to determine its location of storage.
		 */
		int loc = hashFunction(key);
		// System.out.println(loc);
		
		myLinkedList<K, V> list = hashTable[loc];
		int oldsize = list.size();
		
		list.add(key, value);
		
		// If the old size is equal to the new size, this means the key was a duplicate key (already existed in the hashtable).
		if (oldsize != list.size()) {
			++numberOfContents;
		}
		// System.out.println(key + " added to " + loc + " with the value of " + value);
	}
	
	public V get(K key) {
		/*
		 * Returns the item requested with the key given.
		 */
		int loc = hashFunction(key);
		V value = hashTable[loc].get(key);
		
		// System.out.println(value + " with the key of " + key + " has been found at " + loc);
		return value;
	}
	
	public void remove(K key) {
		/*
		 * Removes an item with the key given.
		 */
		int loc = hashFunction(key);
		hashTable[loc].remove(key);
		
		--numberOfContents;
		// System.out.println(key + " has been removed from " + loc);
	}
	
	protected int hashFunction(K key) {
		/*
		 * Hashing function to efficiently determine the location of storage for the object given via its key.
		 */
		return Math.abs(key.hashCode()) % size;
	}
	
	// Getters.
	
	public int size() {
		return size;
	}
	
	public int numberOfContents() {
		return numberOfContents;
	}
	
	public myLinkedList<K, V>[] getHashTable() {
		return hashTable;
	}
}
