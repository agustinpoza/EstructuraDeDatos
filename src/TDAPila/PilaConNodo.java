package TDAPila;

import TDALista.Position;

public class PilaConNodo<E> implements Stack<E> {

	protected int size;
	protected Nodo<E> cabeza;
	
	public PilaConNodo() {
		this.size = 0;
	}
	
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		if(cabeza == null) throw new EmptyStackException();
		return cabeza.element();
	}

	@Override
	public void push(E element) {
		if(cabeza == null) {
			cabeza = new Nodo<E>(element);
			size++;
		}
		else {
			Nodo<E> nuevo = new Nodo<E>(element,cabeza);
			cabeza = nuevo;
			size++;
		}
		
	}

	@Override
	public E pop() throws EmptyStackException {
		if(cabeza == null) throw new EmptyStackException();
		E elem = cabeza.element;
		cabeza = cabeza.getNext();
		size--;
		return elem;
	}
	
	private class Nodo<E> implements Position<E>{
		
		protected E element;
		protected Nodo<E> next;
		protected Nodo<E> prev;
		
		public Nodo(E e, Nodo<E> n, Nodo<E> p) {
			this.element = e;
			this.prev = p;
			this.next = n;
		}
		public Nodo(E e) {
			this.element = e;
			this.next = null;
			this.prev = null;
		}
		public Nodo(E e, Nodo<E> sig) {
			this.element = e;
			this.next = sig;
			this.prev = null;
		}
		
		public E element() {
			return element;
		}
		
		public Nodo<E> getNext(){
			return next;
		}
		public Nodo<E> getPrev(){
			return prev;
		}
		public void setNext(Nodo<E> n) {
			next = n;
		}
		public void setPrev(Nodo<E> p) {
			prev = p;
		}
		public void setElement(E elem) {
			this.element = elem;
		}
		
	}
}


