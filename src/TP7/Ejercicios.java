package TP7;

import Excepciones.InvalidEntryException;
import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDAArbolBinario.BinaryTree;
import TDADiccionario.Dictionary;
import TDADiccionario.DiccionarioHashAbierto;
import TDADiccionario.InvalidKeyException;
import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.ListaDE;

public class Ejercicios<E> {

	public Ejercicios() {}
	
	public Iterable<Character> ej3(BinaryTree<Character> t){
		try {
			PositionList<Character> l = new ListaDE<>();
			inOrdenEj3(t.root(), t, l);
			return l;
			
		}catch(InvalidPositionException | BoundaryViolationException | EmptyTreeException e) {throw new RuntimeException(e);}
	}
	
	public void inOrdenEj3(Position<Character> p, BinaryTree<Character> t, PositionList<Character> l) throws InvalidPositionException, BoundaryViolationException {
		if(t.hasLeft(p)) {
			l.addLast('(');
			inOrdenEj3(t.left(p),t,l);
		}
		l.addLast(p.element());
		if(t.hasRight(p)) {
			inOrdenEj3(t.right(p),t,l);
			l.addLast(')');
		}
	}
	
	public void completarDerechos(E r, BinaryTree<E> t) throws EmptyTreeException{
		if(t.isEmpty()) throw new EmptyTreeException("Arbol vacio");
		PositionList<Position<E>> l = new ListaDE<>();
		for(Position<E> nodo : t.positions()) {
			try {
				if(t.hasLeft(nodo)&&!t.hasRight(nodo)) {
					l.addLast(nodo);
				}
			} catch (InvalidPositionException e) {e.printStackTrace();}
		}
		for(Position<E> n : l) {
			try {
				t.addRight(n, r);
			} catch (InvalidOperationException | InvalidPositionException e) {e.printStackTrace();}
		}
	}
	
	public Dictionary<Integer,Integer> ejd(BinaryTree<Integer> t){
		Dictionary<Integer,Integer> d = new DiccionarioHashAbierto<>();
		if(!t.isEmpty())
			try {
				preorden(t,t.root(),d);
			} catch (EmptyTreeException e) {}
		return d;
	}
	
	private void preorden(BinaryTree<Integer> t, Position<Integer> p, Dictionary<Integer, Integer> d) {
		try {
		if(t.hasLeft(p)) {
			d.insert(p.element(), t.left(p).element());
			preorden(t,t.left(p),d);
		}
		if(t.hasRight(p)) {
			d.insert(p.element(), t.right(p).element());
			preorden(t,t.right(p),d);
		}
		}catch(InvalidPositionException | InvalidKeyException | BoundaryViolationException e) {}
	}
	
	
	
	
}
