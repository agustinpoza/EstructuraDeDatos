package TDAArbolBinario;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.ListaDE;
import TDALista.PositionList;

public class ArbolBinario<E> implements BinaryTree<E> {

	protected int size;
	protected BTNodo<E> root;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> l = new ListaDE<>();
		if(!isEmpty())
			preOrden(root,l);
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDE<>();
		if(!isEmpty())
			preOrdenPositions(root,l);
		return l;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("arbol vacio");
		BTNodo<E> n = checkNode(v);
		E toReturn = n.element();
		n.setElement(e);
		return toReturn;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size>0)
			return root;
		else
			throw new EmptyTreeException("arbol vacio");
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> n = checkNode(v);
		if(n == root) throw new BoundaryViolationException("El nodo es la raiz");
		return n.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkNode(v);
		PositionList<Position<E>> p = new ListaDE<>();
		if(n.getHD()!=null)
			p.addLast(n.getHD());
		if(n.getHI()!=null) {
			p.addLast(n.getHI());
		}
		return p;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkNode(v);
		boolean tieneHijos = (n.getHD()!=null)||(n.getHI()!=null);
		return tieneHijos;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkNode(v);
		boolean noTieneHijos = (n.getHD()==null)&&(n.getHI()==null);
		return noTieneHijos;
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkNode(v);
		return n == root;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if(isEmpty()) throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkNode(v);
		if(n.HI == null) throw new BoundaryViolationException("El nodo no tiene hijo izquierdo");
		return n.getHI();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if(isEmpty()) throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkNode(v);
		if(n.HD == null) throw new BoundaryViolationException("El nodo no tiene hijo derecho");
		return n.getHD();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkNode(v);
		return n.HD!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkNode(v);
		return n.HI!=null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(root == null) {
			this.root = new BTNodo<E>(r);
			size++;
			return root;
		}
		else 
			throw new InvalidOperationException("el arbol ya tiene raiz");
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("El arbol esta vacio");
		BTNodo<E> n = checkNode(v);
		if(n.getHI() == null) {
			BTNodo<E> hi = new BTNodo<>(r,n);
			n.setHI(hi);
			size++;
			return hi;
		}
		else
			throw new InvalidOperationException("El arbol ya tiene un nodo izquierdo");
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("El arbol esta vacio");
		BTNodo<E> n = checkNode(v);
		if(n.getHD() == null) {
			BTNodo<E> hd = new BTNodo<>(r,n);
			n.setHD(hd);
			size++;
			return hd;
		}
		else
			throw new InvalidOperationException("El arbol ya tiene un nodo derecho");
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("arbol vacio");
		BTNodo<E> n = checkNode(v);
		if(!(n == root)) {
			BTNodo<E> padre = checkNode(v).getPadre();
			if(padre.getHD()!=null && padre.getHI()!=null) throw new InvalidOperationException("El nodo tiene mas de un hijo");
			if(padre.getHD() == n) {
				padre.setHD(null);
				n.setPadre(null);
				size--;
			}
			if(padre.getHI() == n){
				padre.setHI(null);
				n.setPadre(null);
				size--;
			}
		}
		else {
			if(n.getHD()!=null && n.getHI()!=null) throw new InvalidOperationException("El nodo tiene mas de un hijo");
			if(n.getHD() != null) {
				root = n.getHD();
				n.getHD().setPadre(null);;
				n.setHD(null);
				size--;
			}
			if(n.getHI() != null) {
				root = n.getHI();
				n.getHI().setPadre(null);;
				n.setHI(null);
				size--;
			}
		}
		return n.element();
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkNode(r);
		if(!(n.getHD()==null && n.getHI()==null)) throw new InvalidPositionException("El nodo pasado como parametro no es hoja");
		try {
			BTNodo<E> subIzquierdo = checkNode(T1.root());
			BTNodo<E> subDerecho = checkNode(T2.root());
			n.setHI(subIzquierdo);
			n.setHD(subDerecho);
			size = T1.size() + T2.size();
		}catch(EmptyTreeException e) {}		
	}

	private BTNodo<E> checkNode(Position<E> p) throws InvalidPositionException {
		try {
            if( p == null )
                throw new InvalidPositionException("Posicion nula.");
			BTNodo<E> n = (BTNodo<E>) p;
			return n;
		}
		catch(ClassCastException e) {
			 throw new InvalidPositionException("Posicion no es de tipo TNodo E");
		}
	}
	
	
	
	private void preOrden(BTNodo<E> n, PositionList<E> l) {
		l.addLast(n.element);
		if(n.getHI()!=null)
			preOrden(n.getHI(),l);
		if(n.getHD()!=null)
			preOrden(n.getHD(),l);
		
	}
	private void preOrdenPositions(BTNodo<E> n, PositionList<Position<E>> l) {
		l.addLast(n);
		if(n.getHI()!=null)
			preOrdenPositions(n.getHI(),l);
		if(n.getHD()!=null)
			preOrdenPositions(n.getHD(),l);
	}
	
}
