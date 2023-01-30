
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BSTTest {
	
	BST<Integer, Integer> bst;
	
	@Before
	public void setup() {
		this.bst = new BST<Integer, Integer>();
	}
	
	@Test
	public void putCalls() {
		bst.put(2, 3);
		bst.put(3, 4);
		
		assertEquals(Integer.valueOf(2), bst.getRoot().getKey());
		assertEquals(Integer.valueOf(3), bst.getRoot().getRight().getKey());
		assertEquals(null, bst.getRoot().getLeft());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void putCallException() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(null, 2);
	}
	
	@Test
	public void replaceCalls() {
		bst.put(2, 3);
		bst.put(3, 4);
		
		assertEquals(Integer.valueOf(2), bst.getRoot().getKey());
		assertEquals(Integer.valueOf(4), bst.getRoot().getRight().getValue());
		assertEquals(null, bst.getRoot().getLeft());
		
		bst.replace(3, 8);
		assertEquals(Integer.valueOf(2), bst.getRoot().getKey());
		assertEquals(Integer.valueOf(8), bst.getRoot().getRight().getValue());
		assertEquals(null, bst.getRoot().getLeft());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void replaceCallsForNullKey() {
		bst.put(2, 3);
		bst.put(3, 4);
		
		bst.replace(null, 2);
	}
	
	@Test
	public void testKeysInOrder() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
		
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 6);
		List<Integer> notExpected = Arrays.asList(2, 1, 4, 3, 6);
	
		List<Integer> inorderList = bst.keys();
		
		assertEquals(expected, inorderList);
		
		assertNotEquals(notExpected, inorderList);
	}
	
	@Test
	public void testContainsKey() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
	
		List<Integer> validKeys = Arrays.asList(2, 3, 1, 6, 4);
		for(Integer key: validKeys) {
			assertEquals(true, bst.containsKey(key));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testContainsKeyForNullKey() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
		
		bst.containsKey(null);
	}
	
	@Test
	public void testIsEmpty() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
	
		assertEquals(false, bst.isEmpty());
		
		bst = new BST<>();
		assertEquals(true, bst.isEmpty());
	}
	
	@Test
	public void testSize() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
	
		assertEquals(5, bst.size());
		
		bst.remove(3);
		assertEquals(4, bst.size());
		
		bst = new BST<>();
		assertEquals(0, bst.size());
	}
	
	@Test
	public void testRemove() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
	
		assertEquals(5, bst.size());
		
		bst.remove(3);
		assertEquals(4, bst.size());
		
		bst.remove(1);
		assertEquals(3, bst.size());
		
		bst.remove(4);
		assertEquals(2, bst.size());
		
		bst.remove(6);
		assertEquals(1, bst.size());
		
		bst.remove(2);
		assertEquals(0, bst.size());
	}
	
	@Test
	public void testRemoveKeyForValidKeys() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
		
		assertEquals(true, bst.remove(3));
		assertEquals(false, bst.remove(100));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveKeyForNullKey() {
		bst.put(2, 3);
		bst.put(3, 4);
		bst.put(1, 5);
		bst.put(6, 10);
		bst.put(4, 15);
		
		bst.remove(null);
	}
}
