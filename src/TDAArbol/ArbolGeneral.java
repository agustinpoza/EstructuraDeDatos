package TDAArbol;

import java.util.Iterator;
import TDACola.*;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDAMap.Map;
import TDALista.ListaDE;

public class ArbolGeneral<E> implements Tree<E>{
	
	protected TNodo<E> root;
	protected int size;
	
	public ArbolGeneral() {
		root = null;
		size = 0;
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
	public Iterator<E> iterator() {
		PositionList<E> l = new ListaDE<>();
		TNodo<E> raiz;
		try {
			raiz = checkNode(root);
			preOrden(raiz,l);
		} catch (InvalidPositionException e) {}
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDE<>();
		TNodo<E> raiz;
		try {
			raiz = checkNode(root);
			preOrdenPositions(raiz,l);
		}catch(InvalidPositionException e) {}
		
		return l;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		E toReturn;
		TNodo<E> n = checkNode(v);
		toReturn  = n.element();
		n.setElem(e);
		return toReturn;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size==0) throw new EmptyTreeException("arbol vacio");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> n = checkNode(v);
		if(n.getPadre()==null) throw new BoundaryViolationException("no existe padre de raiz");
		return n.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkNode(v);
		PositionList<Position<E>> p = new ListaDE<>(); 
		for(TNodo<E> nodos : n.getHijos()) {
			p.addLast(nodos);
		}
		return p;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkNode(v);
		return !n.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		checkNode(v);
		return v == root;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(root != null) throw new InvalidOperationException("el arbol ya tiene una raiz");
		TNodo<E> raiz = new TNodo<E>(e);
		this.root = raiz;
		size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("el arbol esta vacio");
		TNodo<E> nodo = checkNode(p);
		TNodo<E> nuevo = new TNodo<E>(e, nodo);
		nodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("el arbol esta vacio");
		TNodo<E> nodo = checkNode(p);
		TNodo<E> nuevo = new TNodo<E>(e, nodo);
		nodo.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("el arbol esta vacio");
		try {
		TNodo<E> nodo = checkNode(p);
		TNodo<E> nodorb = checkNode(rb);
		TNodo<E> nuevo = new TNodo<E>(e, nodo);
		if(nodo != nodorb.getPadre()) throw new InvalidPositionException("el nodo del primer parametro no es padre del segundo");
		PositionList<TNodo<E>> hijos = nodo.getHijos();
		boolean encontre = false;
		if(!hijos.isEmpty()) {
			Position<TNodo<E>> rb_position = hijos.first();
			while(rb_position!=null && !encontre) {
				if(rb_position.element() == nodorb) {
					encontre = true;
					nodo.getHijos().addBefore(rb_position, nuevo);
					size++;
				}
				else 
					if(rb_position != hijos.last())
						rb_position = hijos.next(rb_position);
					else
						rb_position = null;	
			}
		}
		if(!encontre)
			throw new InvalidPositionException("posicion no valida");
		
		return nuevo;
		}
		catch(InvalidPositionException | BoundaryViolationException | EmptyListException er) {throw new InvalidPositionException("");}
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("el arbol esta vacio");
		try {
			TNodo<E> nodo = checkNode(p);
			TNodo<E> nodorb = checkNode(lb);
			TNodo<E> nuevo = new TNodo<E>(e, nodo);
			PositionList<TNodo<E>> hijos = nodo.getHijos();
			boolean encontre = false;
			if(!hijos.isEmpty()) {
				Position<TNodo<E>> rb_position = hijos.first();
				while(rb_position!=null && !encontre) {
					if(rb_position.element() == nodorb) {
						encontre = true;
						nodo.getHijos().addAfter(rb_position, nuevo);
						size++;
					}
					else 
						if(rb_position != hijos.last())
							rb_position = hijos.next(rb_position);
						else
							rb_position = null;	
				}
			}
			if(!encontre)
				throw new InvalidPositionException("posicion no valida");
			
			return nuevo;
			}
			catch(InvalidPositionException | BoundaryViolationException | EmptyListException er) {throw new InvalidPositionException("");}
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("el arbol esta vacio");
		if(isInternal(p)) throw new InvalidPositionException("nodo interno");
		if(p == root) {
			root = null;
			size--;
		}
		else {
			try {
				TNodo<E> nodo = checkNode(p);
				if(!nodo.getHijos().isEmpty()) throw new InvalidPositionException("p no es hoja");
				TNodo<E> padre = nodo.getPadre();
				PositionList<TNodo<E>> hijos = padre.getHijos();
				Position<TNodo<E>> position_nodo = padre.getHijos().first();
				boolean encontre = false;
				while(position_nodo != null && !encontre) {
					if(position_nodo.element() == nodo) {
						encontre = true;
						padre.getHijos().remove(position_nodo);
						size--;
						nodo.setPadre(null);
						nodo.setElem(null);
					}else
						if(position_nodo != hijos.last())
							position_nodo = hijos.next(position_nodo);
						else 
							position_nodo = null;
				}
				if(!encontre) throw new InvalidPositionException("p no aparece en la lista de hijos de su padre");
				}
				catch(InvalidPositionException | BoundaryViolationException | EmptyListException er) {throw new InvalidPositionException("");}
			}
		}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkNode(p);
		if (isExternal(p))
			throw new InvalidPositionException("p no es un nodo interno");
		if (nodo == root)
			if (nodo.getHijos().size() > 1)
				throw new InvalidPositionException("el nodo raiz a eliminar no puede tener mas de 1 hijo");
			else {
				try {
					root = nodo.getHijos().first().element();
					nodo.setElem(null);
					nodo.getHijos().remove(nodo.getHijos().first());
					root.setPadre(null);
				} catch (EmptyListException e) {
					System.out.println(e.getMessage());
				}
			}
		else {
			TNodo<E> pa = nodo.getPadre();
			PositionList<TNodo<E>> hijos = pa.getHijos();
			Iterator<Position<TNodo<E>>> it = hijos.positions().iterator();
			Position<TNodo<E>> me = null;
			boolean encontre = false;
			while (it.hasNext() && !encontre) {
				me = it.next();
				encontre = me.element() == nodo;
			}
			if (!encontre)
				throw new InvalidPositionException("N no figura como hijo de su padre");
			PositionList<TNodo<E>> hijosN = nodo.getHijos();
			it = hijosN.positions().iterator();
			while (it.hasNext()) {
				TNodo<E> nh = it.next().element();
				nh.setPadre(pa);
				hijos.addBefore(me, nh);
			}
			hijos.remove(me);
			/*nodo.setPadre(null);
			nodo.setElem(null);
			it = hijosN.positions().iterator();
			while (it.hasNext())
				hijosN.remove(it.next());
			size--;*/
		}
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		checkNode(p);
		if(isExternal(p))
			removeExternalNode(p);
		else 
			removeInternalNode(p);
		
	}

	
	
	private TNodo<E> checkNode(Position<E> p) throws InvalidPositionException {
		try {
			//if(this.isEmpty()) throw new InvalidPositionException("arbol vacio");
            if( p == null )
                throw new InvalidPositionException("Posicion nula.");
			TNodo<E> n = (TNodo<E>) p;
			return n;
		}
		catch(ClassCastException e) {
			 throw new InvalidPositionException("Posicion no es de tipo TNodo E");
		}
	}
	
	private void preOrden(TNodo<E> nodo, PositionList<E> l) {
		l.addLast(nodo.elem);
		for(TNodo<E> hijos : nodo.getHijos()) {
			preOrden(hijos, l);
		}
	}
	private void preOrdenPositions(TNodo<E> nodo, PositionList<Position<E>> l) {
		l.addLast(nodo);
		for(TNodo<E> hijos : nodo.getHijos()) {
			preOrdenPositions(hijos, l);
		}
	}
	public void eliminarUltimoHijo(Position<E> p) throws InvalidPositionException, InvalidOperationException {
		TNodo<E> nodo = checkNode(p);
		if(nodo == root) throw new InvalidOperationException("es la raiz");
		PositionList<TNodo<E>> hermanos = nodo.getPadre().getHijos();
		try {
			if(nodo == hermanos.last().element()) {
				this.removeNode(p);
			}
		}catch(EmptyListException e) {}
	}
	
	public void preOrdenGraficar(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkNode(p);
		System.out.print(nodo.elem);
		for(TNodo<E> hijos : nodo.getHijos()) {
			preOrdenGraficar(hijos);
		}
	}
	
	public void agregarNivel(E rotulo, int nivel) throws InvalidPositionException {
		if(!isEmpty() && nivel != 0)
			recorrido(root, rotulo, nivel);
	}
	
	private int nivel(TNodo<E> n) {
		if(n == root)
			return 0;
		else
			return nivel(n.getPadre()) + 1;
	}
	
	private void recorrido(TNodo<E> n, E rotulo, int nivel) throws InvalidPositionException {
		for(Position<TNodo<E>> hijos : n.getHijos().positions()) {
			if(nivel(hijos.element())<nivel) 
				recorrido(hijos.element(),rotulo,nivel);
			
			if(nivel(hijos.element()) == nivel) {
				n.getHijos().addAfter(hijos, new TNodo<E>(rotulo, n));;
				size++;
			}
		}
	}
	
	public void graficarNiveles() throws EmptyQueueException {
		Queue<TNodo<E>> q = new ColaEnlazada<>();
		q.enqueue(root);
		q.enqueue(null);
		while(!q.isEmpty()) {
			TNodo<E> v = q.dequeue();
			if(v != null) {
				System.out.print(v.element());
				for(TNodo<E> hijo : v.getHijos()) {
					q.enqueue(hijo);
				}
			}
			else {
				System.out.println();
				if(!q.isEmpty())
					q.enqueue(null);
			}
		}
	}
	 
}
