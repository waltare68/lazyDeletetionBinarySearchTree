package LazyBinarySearchTree;
/**
 * 
 */

/**
 * @author ROBOWALT
 *
 */
public class LazyBinarySearchTree {

	   private static final int MIN_KEY = 1;
	   private static final int MAX_KEY = 99;

	   // Inner class
	   private class TreeNode {
	       // Instance variable
	       private int key;
	       private TreeNode leftChild;
	       private TreeNode rightChild;
	       private boolean deleted;
	       private int height;

	       /**
	       * Constructor
	       */
	       public TreeNode(int key) {
	           this.key = key;
	           this.leftChild = null;
	           this.rightChild = null;
	           this.deleted = false;
	           this.height = 1;
	       }

	       /**
	       * Returns the key
	       */
	       public int getKey() {
	           return key;
	       }

	       /**
	       * Returns the left Child node
	       */
	       public TreeNode getLeftChild() {
	           return leftChild;
	       }

	       /**
	       * Returns the right Child node
	       */
	       public TreeNode getRightChild() {
	           return rightChild;
	       }

	       /**
	       * Returns the deleted
	       */
	       public boolean isDeleted() {
	           return deleted;
	       }

	       /**
	       * Returns the height
	       */
	       public int getHeight() {
	           return height;
	       }

	       /**
	       * Sets the key
	       *
	       * @param key
	       * - key to be set
	       */
	       public void setKey(int key) {
	           this.key = key;
	       }

	       /**
	       * Sets the left child node
	       *
	       * @param leftChild
	       * - the leftChild to set
	       */
	       public void setLeftChild(TreeNode leftChild) {
	           this.leftChild = leftChild;
	       }

	       /**
	       * Sets the right node
	       *
	       * @param rightChild
	       * - the rightChild to set
	       */
	       public void setRightChild(TreeNode rightChild) {
	           this.rightChild = rightChild;
	       }

	       /**
	       * Sets whether the TreeNode is deleted or not
	       *
	       * @param deleted
	       * the deleted to set
	       */
	       public void setDeleted(boolean deleted) {
	           this.deleted = deleted;
	       }

	       /**
	       * Sets the height of the node
	       *
	       * @param height
	       * the height to set
	       */
	       public void setHeight(int height) {
	           this.height = height;
	       }

	       @Override
	       public String toString() {
	           return String.valueOf(this.key);
	       }
	   }

	   // Instance variable for BinarySearchTree
	   private TreeNode root;

	   /**
	   * Default Constructor
	   */
	   public LazyBinarySearchTree() {
	       this.root = null;
	   }

	   /**
	   * Returns the height of the node in the tree.
	   *
	   * @param node
	   * - tree node
	   * @return - height of the node
	   */
	   private int height(TreeNode node) {
	       // Check if node is null
	       if (node == null)
	           return 0;
	       else
	           return node.getHeight();
	   }

	   /**
	   * Inserts a node in the tree
	   *
	   * @param currentNode
	   * - the current node in tree traversal
	   * @param key
	   * - key to be added to the tree
	   */
	   private boolean insert(TreeNode currentNode, int key) {
	       boolean logicalInsert = false;

	       if (key < currentNode.getKey()) {
	           if (currentNode.getLeftChild() != null)
	               logicalInsert = insert(currentNode.getLeftChild(), key);
	           else {
	               currentNode.setLeftChild(new TreeNode(key));
	               logicalInsert = true;
	           }

	       } else if (key > currentNode.getKey()) {
	           if (currentNode.getRightChild() != null)
	               logicalInsert = insert(currentNode.getRightChild(), key);
	           else {
	               currentNode.setRightChild(new TreeNode(key));
	               logicalInsert = true;
	           }
	       } else { // Duplicate found
	           // Check whether the node is deleted
	           if (currentNode.isDeleted()) {
	               // Set currentNode to not deleted
	               currentNode.setDeleted(false);
	               logicalInsert = true;

	           } else
	               logicalInsert = false;
	       }

	       // Update height
	       currentNode.setHeight(1 + Math.max(height(currentNode.getLeftChild()), height(currentNode.getRightChild())));
	       return logicalInsert;
	   }

	   /**
	   * Inserts a node in the tree
	   *
	   * @param key
	   * - key to be added to the tree
	   */
	   public boolean insert(int key) throws IllegalArgumentException {
	       // Check if key is within range
	       if ((key < MIN_KEY) || (key > MAX_KEY))
	           throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");

	       if (this.root == null) {
	           // If there are no nodes in the tree
	           this.root = new TreeNode(key);

	           return true;
	       } else {
	           // If there are nodes in the tree
	           return insert(this.root, key);
	       }
	   }

	   /**
	   * Deletes a key from the tree
	   *
	   * @param currentNode
	   * - the current node in tree traversal
	   * @param key
	   * - key to be searched
	   */
	   private boolean delete(TreeNode currentNode, int key) {
	       boolean deleted = true;

	       if (key < currentNode.getKey()) {
	           if (currentNode.getLeftChild() != null)
	               deleted = delete(currentNode.getLeftChild(), key);
	           else
	               deleted = false;

	       } else if (key > currentNode.getKey()) {
	           if (currentNode.getRightChild() != null)
	               deleted = delete(currentNode.getRightChild(), key);
	           else
	               deleted = false;

	       } else { // Key found
	           // Check whether the node is deleted
	           if (currentNode.isDeleted())
	               deleted = false;
	           else {
	               // Set currentNode as deleted
	               currentNode.setDeleted(true);
	               deleted = true;
	           }
	       }

	       return deleted;
	   }

	   /**
	   * Deletes a key from the tree
	   */
	   public boolean delete(int key) throws IllegalArgumentException {
	       // Check if key is within range
	       if ((key < MIN_KEY) || (key > MAX_KEY))
	           throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");

	       // Check if root ins not null
	       if (this.root != null)
	           return delete(this.root, key);
	       else
	           return false;
	   }

	   /**
	   * Checks whether a key exists in the tree and is non-deleted
	   *
	   * @param currentNode
	   * - the current node in tree traversal
	   * @param key
	   * - key to be searched
	   */
	   private boolean contains(TreeNode currentNode, int key) {
	       boolean found = true;

	       if (key < currentNode.getKey()) {
	           if (currentNode.getLeftChild() != null)
	               found = contains(currentNode.getLeftChild(), key);
	           else {
	               found = false;
	           }

	       } else if (key > currentNode.getKey()) {
	           if (currentNode.getRightChild() != null)
	               found = contains(currentNode.getRightChild(), key);
	           else {
	               currentNode.setRightChild(new TreeNode(key));
	               found = false;
	           }
	       } else { // Key found
	           // Check whether the node is deleted
	           if (currentNode.isDeleted())
	               found = false;
	           else
	               found = true;
	       }

	       return found;
	   }

	   /**
	   * Checks whether a key exists in the tree and is non-deleted
	   */
	   public boolean contains(int key) throws IllegalArgumentException {
	       // Check if key is within range
	       if ((key < MIN_KEY) || (key > MAX_KEY))
	           throw new IllegalArgumentException("Error in contains: IllegalArgumentException raised");

	       // Check if root is not null
	       if (this.root != null)
	           return contains(this.root, key);
	       else
	           return false;
	   }

	   /**
	   * Returns the height of the tree
	   */
	   public int height() {
	       // Check if root is null
	       if (this.root == null)
	           return -1;
	       else
	           return this.root.getHeight() - 1;
	   }

	   /**
	   * Finds the minimum non-deleted element
	   *
	   * @return - the minimum non-deleted element, -1 if none exists
	   */
	   private int findMin(TreeNode node, int min) {
	       // Check if node is not deleted
	       if (!node.isDeleted())
	           // Set node's key as min
	           min = node.getKey();

	       // Check if left child is not null
	       if (node.getLeftChild() != null)
	           return findMin(node.getLeftChild(), min);
	       else {
	           // Check if right child is not null and node is deleted
	           if ((node.getRightChild() != null) && node.isDeleted())
	               return findMin(node.getRightChild(), min);
	           else
	               return min;
	       }
	   }

	   /**
	   * Finds the minimum non-deleted element
	   *
	   * @return - the minimum non-deleted element, -1 if none exists
	   */
	   public int findMin() {
	       // Variable to hold minimum element
	       int min = -1;

	       // Check if root is not null
	       if (this.root != null) {
	           // Set root key as min if it is not deleted
	           if (!this.root.isDeleted())
	               min = this.root.getKey();

	           // Check if left child exists
	           if (this.root.getLeftChild() != null) {
	               min = findMin(this.root.getLeftChild(), min);

	               // Check if minimum in left subtree is not found
	               if (min == -1) {
	                   // Here since min = -1 that means that the root is also
	                   // deleted. So check the right subtree for minimum element

	                   // Get root node
	                   TreeNode node = this.root;

	                   // Check right subtree
	                   while (node.getRightChild() != null) {
	                       // Find minimum
	                       min = findMin(node.getRightChild(), min);

	                       // Check if min is found
	                       if (min != -1)
	                           break;

	                       // Move to the right child
	                       node = node.getRightChild();
	                   }
	               }
	           }
	       }

	       // Return min
	       return min;
	   }

	   /**
	   * Finds the maximum non-deleted element
	   *
	   * @return - the maximum non-deleted element, -1 if none exists
	   */
	   private int findMax(TreeNode node, int max) {
	       // Check if node is not deleted
	       if (!node.isDeleted())
	           // Set node's key as max
	           max = node.getKey();

	       // Check if right child is not null
	       if (node.getRightChild() != null)
	           return findMax(node.getRightChild(), max);
	       else {
	           // Check if left child is not null and node is deleted
	           if ((node.getLeftChild() != null) && node.isDeleted())
	               return findMax(node.getLeftChild(), max);
	           else
	               return max;
	       }
	   }

	   /**
	   * Finds the maximum non-deleted element
	   *
	   * @return - the maximum non-deleted element, -1 if none exists
	   */
	   public int findMax() {
	       // Variable to hold maximum element
	       int max = -1;

	       // Check if root is not null
	       if (this.root != null) {
	           // Set root key as max if it is not deleted
	           if (!this.root.isDeleted())
	               max = this.root.getKey();

	           // Check if right child exists
	           if (this.root.getRightChild() != null) {
	               max = findMax(this.root.getRightChild(), max);

	               // Check if maximum in right subtree is not found
	               if (max == -1) {
	                   // Here since max = -1 that means that the root is also
	                   // deleted. So check the left subtree for maximum element

	                   // Get root node
	                   TreeNode node = this.root;

	                   // Check left subtree
	                   while (node.getLeftChild() != null) {
	                       // Find maximum
	                       max = findMax(node.getLeftChild(), max);

	                       // Check if max is found
	                       if (max != -1)
	                           break;

	                       // Move to the left child
	                       node = node.getLeftChild();
	                   }
	               }
	           }
	       }

	       // Return max
	       return max;
	   }

	   /**
	   * Returns the count of elements in the tree,including deleted elements
	   */
	   private int size(TreeNode node, int count) {
	       // Check if node is not null
	       if (node != null) {
	           count += 1;

	           // Traverse left
	           count = size(node.getLeftChild(), count);

	           // Traverse right
	           count = size(node.getRightChild(), count);
	       }
	       return count;
	   }

	   /**
	   * Returns the count of elements in the tree,including deleted elements
	   */
	   public int size() {
	       // Check if root is not null
	       if (this.root != null) {
	           return size(this.root, 0);
	       }
	       return 0;
	   }

	   /**
	   * Returns the preorder traversal of the tree
	   *
	   * @param node
	   * - node of this tree
	   * @param sb
	   * - holds the string representation of node(s)
	   */
	   public StringBuffer preorder(TreeNode node, StringBuffer sb) {
	       if (node != null) {
	           // Append key at node
	           // Check if node is deleted
	           if (node.isDeleted())
	               sb.append("*" + node.getKey() + " ");
	           else
	               sb.append(node.getKey() + " ");

	           // Traverse left
	           preorder(node.getLeftChild(), sb);

	           // Traverse right
	           preorder(node.getRightChild(), sb);
	       }

	       return sb;
	   }

	   /**
	   * Performs the preorder traversal of the tree
	   *
	   * @return - Returns a string containing the preorder traversal
	   */
	   public String toString() {
	       StringBuffer sb = new StringBuffer();
	       sb = preorder(this.root, sb);
	       return sb.toString();
	   }
	}
