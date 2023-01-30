
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * TODO: Add instance variables 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */
	private Node<K, V> root;
	
	public BST() {
		this.root = null;
	}

	public Node<K,V> getRoot() {
		return this.root;
	}
	
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		if(key == null) {
			throw new IllegalArgumentException("Put call made using null key");
		}
		
		Node<K, V> node = searchInBST(root, key);
		if(node != null) {
			return false;
		}
		
		root = insertKeyIntoBST(root, key, value);
		return true;
	}
	
	/*
	 * @param node The node whose size need to be computed
	 * @return size Returns size of the node
	*/
	private int size(Node<K, V> node) {
		return node == null ? 0:node.getSize();
	}

	/*
	 * @param node Root of subtree in which key needs to be inserted
	 * @param key The key to be inserted
	 * @param value The value to be inserted
	 * @return root of subtree in which the key and value pair is inserted
	*/
	private Node<K, V> insertKeyIntoBST(Node<K, V> node, K key, V value) {
		if(node == null) {
			node = new Node<K, V>(key, value, 1);
			return node;
		}
		
		//compare the current node with key to determine the direction in BST
		int comparisonWithCurrentNode = key.compareTo(node.getKey());
		if(comparisonWithCurrentNode < 0) {
			node.left = insertKeyIntoBST(node.getLeft(), key, value);
		} else if(comparisonWithCurrentNode > 0) {
			node.right = insertKeyIntoBST(node.getRight(), key, value);
		} else {
			node.value = value;
		}
		
		node.size = 1 + size(node.getLeft()) + size(node.getRight());
		return node;
	}
	
	/*
	 * @param node Root of subtree in which key needs to be searched
	 * @param key The key to be searched
	 * @return node which corresponds to the key being searched
	*/
	private Node<K, V> searchInBST(Node<K, V> node, K key) {
		if(node == null) {
			return null;
		}
		
		//compare the current node with key to determine the direction in BST
		int comparison = key.compareTo(node.getKey());
		if(comparison < 0) {
			return searchInBST(node.getLeft(), key);
		} else if(comparison > 0) {
			return searchInBST(node.getRight(), key);
		}
		
		return node;
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException("Can't replace for null key");
		}
		
		Node<K, V> node = searchInBST(root, key);
		if(node!=null) {
			node.setValue(newValue);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException("Can't replace for null key");
		}
		
		Node<K, V> node = searchInBST(root, key);
		if(node!=null) {
			root = deleteNodeWithGivenKey(root, key);
			return true;
		}
		
		//key doesn't exist in BST
		return false;
	}

	/*
	 * @param node Root of subtree in which key needs to be deleted
	 * @param key The key to be deleted
	 * @return node which corresponds to the key being deleted
	*/
	private Node<K, V> deleteNodeWithGivenKey(Node<K, V> node, K key) {
		if(node == null) {
			return node;
		}
		
		//compare the current node with key to determine the direction in BST
		int comparison = key.compareTo(node.getKey());
		if(comparison < 0) {
			node.left = deleteNodeWithGivenKey(node.getLeft(), key);
		} else if(comparison > 0) {
			node.right = deleteNodeWithGivenKey(node.getRight(), key);
		} else {
			if(node.getLeft() == null) {
				
				return node.getRight();
			}
			
			if(node.getRight() == null) {
				return node.getLeft();
			}
			
			node.key = findMinKey(node.getRight());
			node.right = deleteNodeWithGivenKey(node.getRight(), node.getKey());
		}
		
		node.size = 1 + size(node.getLeft()) + size(node.getRight());
		return node;
	}

	/*
	 * @param node Root of subtree in which minimum key needs to be searched
	 * @return minimum key value in the subtree
	*/
	private K findMinKey(Node<K, V> node) {
		//go to leftmost node
		K minKey = node.key;
        while (node.left != null)
        {
            minKey = root.left.key;
            root = root.left;
        }
        return minKey;
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException("Can't set for null key");
		}
		
		Node<K, V> node = searchInBST(root, key);
		
		if(node == null) {
			put(key, value);
		} else {
			node.setValue(value);
		}
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException("Can't get for null key");
		}
		
		Node<K, V> node = searchInBST(root, key);
		
		if(node == null) {
			return null;
		} 
		
		return node.getValue();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		if(root == null) {
			return 0;
		}
		
		return root.getSize();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException("Can't find for null key");
		}
		
		Node<K, V> node = searchInBST(root, key);
		
		if (node == null) {
			return false;
		}
		return true;
	}
	
	/*
	 * @param node Root of subtree in which order needs to be computed
	 * @param inorderKeys Result which contains the ordered keys
	*/
	private void inorder(Node<K, V> node, List<K> inorderKeys) {
		if(node == null) {
			return;
		}
		
		inorder(node.left, inorderKeys);
		inorderKeys.add(node.getKey());
		inorder(node.right, inorderKeys);
	}

	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do in order traversal of the tree
	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		List<K> inorderKeys = new ArrayList<>();
		inorder(root, inorderKeys);
		return inorderKeys;
	}

	static class Node<K extends Comparable<? super K>, V> implements DefaultMap.Entry<K, V> {
		/* 
		 * TODO: Add instance variables and constructor
		 */
		private K key;
		private V value;
		private Node<K, V> left;
		private Node<K, V> right;
		private int size;
		
		public Node(K key, V value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
			this.left = null;
			this.right = null;
		}
		
		public Node<K, V> getLeft() {
			return left;
		}
		
		public void setLeft(Node<K, V> left) {
			this.left = left;
		}

		public Node<K, V> getRight() {
			return right;
		}

		public void setRight(Node<K, V> right) {
			this.right = right;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return this.key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return this.value;
		}

		@Override
		public void setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + ", left=" + left + ", right=" + right + ", size=" + size
					+ "]";
		}
	}
}