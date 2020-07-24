package eg.edu.alexu.csd.datastructure.stack.cs34;

/**
 * @author cs34
 * Creating a Stack data representation using SinglyNode
 */
public class Stack implements IStack {
	
	/**
	 * @author cs34
	 * Creating a SinglyNode
	 */
	public class Node {
		/**
		 * The value of the node
		 */
		private Object value;
		/**
		 * The next node
		 */
		private Node next;
		
		/**
		 * setting the conditions for creating the node
		 * @param v is the desired value
		 * @param n is the desired next node
		 */
		public Node (Object v, Node n) {
			value= v;
			next = n;
		}
		/**
		 * @return the value of the node
		 */
		public Object getValue() {
			return value;
		}
		/**
		 * @return the next node in the list
		 */
		public Node getNext() {
			return next;
		}
		/**
		 * @param v the new value to set to the node
		 */
		public void setValue(Object v) {
			value= v;
		}
		/**
		 * @param n the next node to be set for the current node
		 */
		public void setNext(Node n) {
			next = n;
		}
	}
	
	/**
	 * the top node of the stack
	 */
	private Node top;
	private int size;
	
	public Stack() {
		top = null;
		size = 0;
	}
	
	@Override
	public Object pop() {
		/**
		 * can't pop if it is empty
		 */
		if (isEmpty()) {
			return null;
		}
		else {
			Object result = top.getValue();
			top = top.getNext();
			size--;
			return result;
		}
	}

	@Override
	public Object peek(){
		/**
		 * empty stack has no peek
		 */
		if (isEmpty()) {
			return null;
		}
		else {
			return top.getValue();
		}
	}

	@Override
	public void push(Object element) {
		Node input = new Node(element, top);
		top = input;
		size++;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

}
