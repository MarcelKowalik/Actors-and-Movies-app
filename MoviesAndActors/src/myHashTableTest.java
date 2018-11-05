import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class myHashTableTest {
	
	private myHashTable<String, String> hashTable;

	@BeforeEach
	void setUp() throws Exception {
		hashTable = new myHashTable<String, String>(5);
	}

	@Test
	void testConstructor() {
		assertNotNull(hashTable);
	}
	
	@Test
	void testPutAndGet() {
		hashTable.put("Some Key", "Some Value");
		assertEquals("Some Value", hashTable.get("Some Key"));
		
		hashTable.put("Another Key", "Another Value");
		assertEquals("Another Value", hashTable.get("Another Key"));
		
		hashTable.put("Key 3", "Value");
		assertEquals("Value", hashTable.get("Key 3"));
		
		hashTable.put("Some Key", "Some Value replaced with this Value");
		assertEquals("Some Value replaced with this Value", hashTable.get("Some Key"));
	}

	@Test
	void testRemove() {
		hashTable.put("Key 4", "Value 4");
		assertEquals("Value 4", hashTable.get("Key 4"));
		hashTable.remove("Key 4");
		assertEquals(null, hashTable.get("Key 4"));
		
		hashTable.put("Key 5", "Value 5");
		assertEquals("Value 5", hashTable.get("Key 5"));
		hashTable.remove("Key 5");
		assertEquals(null, hashTable.get("Key 5"));
		
		hashTable.put("Key 6", "Value 6");
		assertEquals("Value 6", hashTable.get("Key 6"));
		hashTable.remove("Key 6");
		assertEquals(null, hashTable.get("Key 6"));
		
		hashTable.put("Last Key", "Last Value");
		assertEquals("Last Value", hashTable.get("Last Key"));
		hashTable.remove("Last Key");
		assertEquals(null, hashTable.get("Last Key"));
	}
}
