package Lista;

import java.util.Iterator;

public class ListaD<E> implements PositionList<E>{

	protected int size;
	protected Node<E> head;
	protected Node<E> trailer;
	
	public ListaD(){
		this.size = 0;
		this.head = new Node<E>(null); head.setNext(trailer);
		this.trailer = new Node<E>(null); trailer.setPrev(head);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(isEmpty()) throw new EmptyListException("Lista vacia"); 
		Position<E> p = null;
		try {
			p = next(head);
		}catch(InvalidPositionException | BoundaryViolationException e) {}
		return p;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty()) throw new EmptyListException("Lista vacia"); 
		Position<E> p = null;
		try {
			p = prev(trailer);
		}catch(InvalidPositionException | BoundaryViolationException e) {}
		return p;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> n = checkPosition(p);
		if(n.next == trailer) throw new BoundaryViolationException("estas en la ultima posicion");
		n = n.next();
		return n;
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> n = checkPosition(p);
		if(n.prev == head) throw new BoundaryViolationException("estas en la primera posicion");
		n = n.prev();
		return n;
	}

	@Override
	public void addFirst(E element) {
		Node<E> p = new Node<E>(element);
		p.setNext(head.next());
		head.next().setPrev(p);
		p.setPrev(head);
		head.setNext(p);
		size++;
	}

	@Override
	public void addLast(E element) {
		Node<E> p = new Node<E>(element);
		p.setPrev(trailer.prev);
		trailer.prev.setNext(p);
		p.setNext(trailer);
		trailer.setPrev(p);
		size++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Node<E> pn = checkPosition(p);
		Node<E> n = new Node<E>(element);
		n.setNext(pn.next);
		pn.next.setPrev(n);
		n.setPrev(pn);
		pn.setNext(n);
		size++;
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Node<E> pn = checkPosition(p);
		Node<E> n = new Node<E>(element);
		n.setPrev(pn.prev());
		pn.prev().setNext(n);
		n.setNext(pn);
		pn.setPrev(n);
		size++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		Node<E> pn = checkPosition(p);
		pn.prev().setNext(pn.next());
		pn.next().setPrev(pn.prev());
		size--;
		return p.element();
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Node<E> pn = checkPosition(p);
		E anterior = pn.element();
		pn.setElement(element);
		return anterior;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Node<E> checkPosition(Position<E> p) throws InvalidPositionException, ClassCastException{
		Node<E> n = null;
		if(p == null) throw new InvalidPositionException("posicion invalida");
		if(p == head) throw new InvalidPositionException("la posicion es un centinela");
		if(p == trailer) throw new InvalidPositionException("la posicion es un centinela");
		if(p.element() == null) throw new InvalidPositionException("la posicion no tiene elemento");
		try {
			 n = (Node<E>) p;
		}catch (ClassCastException e) {throw new ClassCastException("El parametro no es de tipo nodo");}
		return n;
	}

}
