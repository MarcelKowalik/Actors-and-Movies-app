import java.util.Iterator;

public class myLinkedList<K, V> implements Iterable<K> {
	/*
	 * Linked List implementation to be used for a Hashtable.
	 * 
	 * Note that a flaw with this list is that unlike a regular linked list, it also keeps the key-value pairs for a hashtable.
	 * This is used to prevent duplicate keys in the hashtable being implemented, this however results in the previous references being redundant and not used.
	 */
	
	private myLLObject<K, V> head; // Start of the list.
	private myLLObject<K, V> tail; // End of the list.
	private int size;
	
	public myLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public void add(K key, V value) {
		/*
		 * Adds a new object into the list.
		 */
		myLLObject<K, V> newObj = new myLLObject<K, V>(key, value);
		
		if (isEmpty()) {
			// List is empty. New object will be the only object in the list, so it is the first and the last of the list.
			this.head = newObj;
			this.tail = this.head;
			++size;
		} else {
			// List is not empty. Check if there are any duplicate objects with the same keys.
			myLLObject<K, V> duplicateObj = head;
			
			while (duplicateObj != null && !duplicateObj.getKey().equals(key)) {
				// Simple loop through the linked list to find a duplicate object with the same key.
				duplicateObj = duplicateObj.getNext();
			}
			
			if (duplicateObj != null && duplicateObj.getKey().equals(key)) {
				// Duplicate object found, replace the original value with the new one.
				duplicateObj.setValue(value);
			} else {
				// No duplicate object, automatically add to the end of the list. i.e. Set it as the tail.
				newObj.setPrev(this.tail);
				this.tail.setNext(newObj);
				this.tail = this.tail.getNext();
				++size;
			}
		}
	}
	
	public V get(K key) {
		/*
		 * Gets an object's value from the list via a key given.
		 */
		myLLObject<K, V> objFound = head;
		
		while (objFound != null && !objFound.getKey().equals(key)) {
			// Simple loop through the linked list to find the object with the given key.
			objFound = objFound.getNext();
		}
		
		if (objFound != null && objFound.getKey().equals(key)) {
			// Object is found.
			return objFound.getValue();
		} else {
			// Object is not found.
			return null;
		}
	}
	
	public void remove(K key) {
		/*
		 * Removes an object's reference from the list via key given.
		 */
		myLLObject<K, V> objFound = head;
		
		while (objFound != null && !objFound.getKey().equals(key)) {
			// Simple loop through the linked list to find the object with the given key.
			objFound = objFound.getNext();
		}
		
		if (objFound != null && objFound.getKey().equals(key)) {
			// Object is found.
			if (objFound.equals(this.head)) {
				if (this.head.equals(this.tail)) {
					// Only one object in the list, reset the list.
					head = null;
					tail = null;
					size = 0;
				} else {
					// Object is the first one in the list. Change the start of the list to the next reference in the list.
					this.head.getNext().setPrev(null);
					this.head = this.head.getNext();
					--size;
				}
			} else if (objFound.equals(this.tail)) {
				// Object is the last one in the list. Change the end of the list to the previous reference in the list.
				this.tail.getPrev().setNext(null);
				this.tail = this.tail.getPrev();
				--size;
			} else {
				// Remove all references to the object found.
				objFound.getPrev().setNext(objFound.getNext());
				objFound.getNext().setPrev(objFound.getPrev());
				--size;
			}
		}
	}
	
	public boolean isEmpty() {
		/*
		 * Checks if the head is empty (if it is, it means there's no object in the list).
		 */
		return (head == null && size == 0);
	}
	
	public myLLIterator<K, V> iterator() {
		/*
		 * Allows for-each looping of the list.
		 */
		return new myLLIterator<K, V>(head);
	}
	
	public int size() {
		return size;
	}
	
	private class myLLObject<K, V> {
		/*
		 * Inner class that stores the key-value pair and the next/previous object references of one object from the list.
		 */
		
		private K key; // Key used to identify the object in the hashtable.
		private V value; // Value associated with the key.
		private myLLObject<K, V> next; // Next object in the list.
		private myLLObject<K, V> prev; // Previous object in the list.
		
		public myLLObject(K key, V value) {
			setKey(key);
			setValue(value);
			setNext(null);
			setPrev(null);
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

		public myLLObject<K, V> getNext() {
			return next;
		}

		public void setNext(myLLObject<K, V> next) {
			this.next = next;
		}

		public myLLObject<K, V> getPrev() {
			return prev;
		}

		public void setPrev(myLLObject<K, V> prev) {
			this.prev = prev;
		}
	}
	
	private class myLLIterator<K, V> implements Iterator {
		/*
		 * Inner class implenting the Iterator interface to allow for-each looping.
		 */
		
		private myLLObject<K, V> currentObj;
		
		public myLLIterator(myLLObject<K, V> currentObj) {
			this.currentObj = currentObj;
		}
		
		public boolean hasNext() {
			return currentObj != null;
		}
		
		public K next() {
			myLLObject<K, V> prevObj = currentObj;
			this.currentObj = currentObj.getNext();
			
			return prevObj.getKey();
		}
	}
}
