package main;

/*
 * this class represents a linked list with the link from each node to the next node
 */
public class SingleLinkedList<E> {
	
	/*
	 * reference to the list head, which is the first node to all linked lists. it is mainly used for the add first method.
	 */
	private Node<E> head = null;
	
	/*
	 * meant to count each node being held within the linked list
	 */
	private int size;
	
	/*
	 * you can see with this add first method, we are adding the data but making the reference null since that is what the head nodes reference is.
	 * you can also see that I am adding a counter to keep track of the size of the linked list
	 * 
	 * @param item which is the data for the Node
	 */
	public void addFirst(E item) {
		this.head = new Node<E>(item, this.head);
		++size;
	}
	
	/*
	 * this method is generally added right after the addfirst method. what is happening is that I am 
	 * 
	 * @param node The node preceding the new item
	 * @param item the item to insert
	 */
	private void addAfter(Node<E> node, E item) {
		node.next = new Node<E>(item, node.next);// i am getting the next nodes (node.next, the node to the right of node within the parameter) reference so that the new node containing item can have reference to its next node
		++size;									//without ruining the order of the list, then node.next is being reassigned to the newly created node.
	}
	
	/*
	 * in this method i am trying to remove the node after a certain node
	 */
	private E removeAfter(Node<E> node) {
		Node<E> temp = node.next;		//I am assigning the node after "node"(the one to the right of it) to a temporary node.
		if(temp != null) {				//I am checking whether the node is empty
			node.next = temp.next;		//if not null get the node after the temp node (== node.next.next) and equal it to node.next(sort of like your pasign by the next node that was originally there)
			--size;
			return temp.data;
		}else {
			return null;
		}
	}
	
	/*
	 * this method removes any node within the iterator and return the node.
	 */
	private E removeFirst() {
		Node<E> temp = this.head;		//the head node is being put into a temporary node
		if(this.head != null) {			//checking if the head node is not empty
			this.head = head.next;		//if it isn't empty then it assigns node to the right of it to the head, "removing the initial head"
		}
		
		if(temp != null) {				//chceking if the temp(==to head) is not empty, if not then it returns its data. else return null
			size--;
			return temp.data;
		}else {
			return null;
		}
	}
	
	
	public String toString() {
		Node<E> nodeRef = this.head;  					//I am assigning the head node to a temporary node
		StringBuilder result = new StringBuilder();		//i am creating a string builder object. this enables me to create a string and modify it 
		while(nodeRef != null) {						//create while loop that ends when we reach the end of the loop, also adding a an arrow to the string
			result.append(nodeRef.data);
			if(nodeRef.next != null) {
				result.append("==>");
			}
			nodeRef = nodeRef.next;						//this is where the value of nodeRef actually changes, it assigns it to the next node
		}
		return result.toString();
	}
	
	
	
	/*
	 * this method will enables us to get the index of the requested node, will return Node which is data and the reference
	 * 
	 * what im doing here is iterating through the nodes until i reached the required index, then return 
	 */
	private Node<E> getNode(int index){
		Node<E> node = this.head;
		for(int i = 0; i < index && node != null; i++) {  //loops to 5 then stops, thats why you have to use node.next to get node 6
			node = node.next;
		}
		
		return node;
		
	}
	
	
	/*
	 * this is the getter for the data within the node leaving out the actual reference
	 */
	public E get(int index) {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index);
		return node.data;
	}
	
	public E set(int index, E newValue) {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		
		Node<E> node = getNode(index);
		E result = node.data;//assigning the old value to return, before changing it
		node.data = newValue;//assigning the new value to the Node 
		return result;
		
	}
	
	/*
	 * this method adds a new node to the linked list and places it in the required spot
	 */
	public void add(int index, E item) {
		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		
		if(index == 0) {
			this.addFirst(item);
		}else {
			Node<E> node = this.getNode(index - 1); //your adding the -1 because you need the node before the requested node so you could get it using .next
			this.addAfter(node, item);
		}
	}
	
	/*
	 * normal add method and adds to the very end of the linked list
	 */
	public boolean add(E item) {
		
		this.add(size, item);
		return true;
	}
	
	
	
	/*
	 * This inner class represents a single node and what it contains, such as the reference which is the (next) and the data that it contains
	 * which is (data)
	 * 
	 * The class is private. which means that the outer class can use it but it can't necessarily leave the parent class.if it does when there will be an error
	 * 
	 * the class is static in the header because will not reference its outer class (called a nested class)
	 * a nested class is a class who's methods are not used in the outer class. or another explanation is that it allows you to access the Node without having to
	 * create a new object under a new variable name to use it.
	 * 
	 * 
	 */
	private static class Node<E>{
		private E data;
		private Node<E> next;//reference to the next node
		
		//constructors
		
		
		/*
		 * This method creates a new Node for the linked list and assigning its reference to null. 
		 * null is the next field.
		 * @param dataItem The data stored
		 */
		private Node(E dataItem) {
			this.data = dataItem;
			this.next = null;
		}
		
		/*
		 * this method creates a node, which is the same as the one above but it contains a reference to another node(which is the reference of the node in front of it).
		 * @param dataItem The data stored
		 * @param nodeRef The node referenced by new node
		 */
		private Node(E dataItem, Node<E> nodeRef) {
			this.data = dataItem;
			this.next = nodeRef;
		}
		
		
		
	}
}
