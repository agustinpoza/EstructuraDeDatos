package Lista;

public class Node<E> implements Position<E>{
	
	protected E element;
	protected Node<E> next;
	protected Node<E> prev;
	
	public Node(E elem, Node<E> n, Node<E> p) {
		element = elem;
		next = n;
		prev = p;
	}
	public Node(E elem) {
		element = elem;
		next = null;
		prev = null;
	}
	
	
	public E element() {
		return element;
	}
	
	public void setElement(E elem) {
		this.element = elem;
	}
	
	public void setNext(Node<E> n) {
		this.next = n;
	}
	
	public void setPrev(Node<E> n) {
		this.prev = n;
	}
	
	public Node<E> next(){
		return this.next;
	}
	
	public Node<E> prev(){
		return this.prev;
	}
	
}
