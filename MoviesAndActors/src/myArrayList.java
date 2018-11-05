public class myArrayList<K, V> {
	/*
	 * ArrayList implemention to be used for a HashTable.
	 * P.S Was not required in the project at the end.
	 */
	
	private myALObject<K, V>[] list; // The resizable array used for the ArrayList.
	private int length;
	
	@SuppressWarnings("unchecked")
	public myArrayList() {
		length = 0;
		
		list = new myALObject[length];
	}
	
	@SuppressWarnings("unchecked")
	public void add(K key, V value) {
		/*
		 * Adds a new object to the list.
		 */
		boolean duplicateFound = false;
		
		if (!isEmpty()) {
			// Checking for duplicate objects with the same key if list isn't empty.
			for (int i = 0; i < length; i++) {
				if (list[i].getKey().equals(key)) {
					// Duplicate found, replace the original value with the new one.
					list[i].setValue(value);
					duplicateFound = true;
					
					break;
				}
			}
		}
		
		if (!duplicateFound) {
			// No duplicate found, automatically add it to the end of the list.
			++length;
			
			myALObject<K, V>[] oldlist = list;
			// Creating a newly sized array to fit the new object.
			list = new myALObject[length];
			
			for (int i = 0; i < length - 1; i++) {
				list[i] = oldlist[i];
			}
			
			list[length - 1] = new myALObject<K, V>(key, value);
		}
	}
	
	public V get(K key) {
		/*
		 * Gets an object's value associated with the given key from the list.
		 */
		myALObject<K, V> objFound = null;
		
		if (!isEmpty()) {
			for (int i = 0; i < length; i++) {
				if (list[i].getKey().equals(key)) {
					objFound = list[i];
					
					break;
				}
			}
		}
		
		if (objFound.getKey().equals(key)) {
			return objFound.getValue();
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void remove(K key) {
		/*
		 * Removes an object from the list.
		 */
		boolean objRemoved = false;
		
		for (int i = 0; i < length; i++) {
			if (list[i].getKey().equals(key)) {
				// Object found in the list, remove its reference.
				list[i] = null;
				objRemoved = true;
			} else if (objRemoved) {
				// Shift all elements back one spot if object is removed.
				list[i - 1] = list[i];
			}
		}
		
		if (objRemoved) {
			// If object was found and removed, change size of array back to one less.
			--length;
			
			myALObject<K, V>[] oldlist = list;
			list = new myALObject[length];
			
			for (int i = 0; i < length; i++) {
				list[i] = oldlist[i];
			}
		}
	}
	
	public boolean isEmpty() {
		return length == 0;
	}
	
	public int size() {
		return length;
	}
	
	private class myALObject<K, V> {
		
		private K key;
		private V value;
		
		public myALObject(K key, V value) {
			setKey(key);
			setValue(value);
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
