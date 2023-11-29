package TP6;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import TDAMap.MapeoHashAbierto;
import TDAArbol.EmptyTreeException;
import TDAArbol.Tree;
import TDAArbolBinario.BinaryTree;
import TDACola.*;
import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.ListaDE;
import TDALista.PositionList;
import TDAMap.InvalidKeyException;
import TDAMap.Map;

public class Ejercicios<E> {
	
	public Ejercicios() {} 
	
	
	
	public Map<Character,Integer> profundidadNodos(Tree<Character> t) throws InvalidKeyException, EmptyTreeException, InvalidPositionException, BoundaryViolationException{
		Map<Character,Integer> m = new MapeoHashAbierto<>();
		for(Position<Character> n : t.positions()) {
			m.put(n.element(),profundidad(n,t));
		}
		return m;
	}
	
	public int profundidad(Position<Character> n, Tree<Character> t) throws EmptyTreeException, InvalidPositionException, BoundaryViolationException {
		if(n == t.root())
			return 0;
		else 
			return profundidad(t.parent(n),t) + 1;
	}
	
	
	public Map<Character, Integer> cantidadRepeticiones(Tree<Character> t) throws InvalidKeyException{
		Iterator<Character> i = t.iterator();
		Map<Character, Integer> m = new MapeoHashAbierto<>();
		Character c;
		Integer cont;
		while(i.hasNext()) {
			c = i.next();
			cont = m.get(c);
			if(cont == null) {
				m.put(c, 1);
			}
			else {
				m.put(c,cont+1);
			}
		}
		/*otra forma de hacerlo
		for(Character c1 : t) {
			cont = m.get(c1);
			if(cont == null) {
				m.put(c1, 1);
			}
			else {
				m.put(c1,cont+1);
			}
		}*/
		return m;
	}
	
	public void postOrden4(String s, Tree<String> t, PositionList<Position<String>> l, Position<String> raiz) throws InvalidPositionException, EmptyTreeException {
		for(Position<String> p : t.children(raiz)) {
			postOrden4(s,t,l,p);
		}
		if(s.equals(raiz.element()))
		l.addLast(raiz);
	}
	
	public Iterable<Position<String>> ej4(Tree<String> t, String s) throws InvalidPositionException, EmptyTreeException{
		 PositionList<Position<String>> l = new ListaDE<>();
		 if(!t.isEmpty())
			 postOrden4(s,t,l,t.root());
		 return l;
	}
	
	/*
	 * En el 5 hice un chanchullo, utilice AtomicInteger  que es una clase que contiene enteros pero que es mutable
	 * ya que Integer e Int son inmutables (Cuando haces algo como Integer x = 5;, no puedes cambiar el valor de x 
	 * directamente. Si intentas hacer x = 10;, en realidad estás creando un nuevo objeto Integer con el valor 10 y
	 *  haciendo que x apunte a ese nuevo objeto, pero el objeto original con el valor 5 no se modifica. Esto es lo
	 *   que quiero decir con "inmutable".) y por lo tanto en el metodo recursivo no funcionan porque son inmutables.
	 *   
	 *   podria hacerse mucho mas facil con un iterador y contando los elementos uno por uno? si, seria mucho mas sencillo
	 *   estupido e ineficiente pero si.
	 * */
	public Integer ej5(Tree<E> t, E e) {
		AtomicInteger i = new AtomicInteger(0);
		try {
			postOrden5(t,e,t.root(),i);
		}catch (InvalidPositionException | EmptyTreeException e1) {}
		return i.get();
	}
	
	public void postOrden5(Tree<E> t, E e, Position<E> raiz, AtomicInteger i) throws InvalidPositionException {
		for(Position<E> p : t.children(raiz)) {
			 postOrden5(t,e,p,i);
		}
		if(raiz.element().equals(e)) {
			t.removeNode(raiz);
			i.incrementAndGet();
		}
	}
	
	public int ej5_estupido(Tree<E> t , E e) {
		int i = 0;
		Queue<Position<E>> q = new ColaEnlazada<>();
		for(Position<E> p : t.positions()) {
			if(p.element().equals(e)) {
				i++;
				q.enqueue(p);
			}
		}
		while(!q.isEmpty()) {
			try {
				t.removeNode(q.dequeue());
			} catch (InvalidPositionException | EmptyQueueException e1) {e1.printStackTrace();}
		}

		
		return i;
	}
	
	public boolean ej6(Tree<Integer> t, Integer n) {
		boolean pertenece = false;
		Iterator<Integer> it = t.iterator();
		Integer e;
 		while(it.hasNext() && !pertenece) {
			e = it.next();
			if(e.equals(n))
				pertenece = true;
		}
		return pertenece;
	}
	
	public void removerNodosR(Tree<Character> t, Character c) {
		PositionList<Position<Character>> l = new ListaDE<>();
		try {
		postOrden(t,c,l,t.root());
		for(Position<Character> p : l) {
			t.removeNode(p);
		}
		}catch(EmptyTreeException | InvalidPositionException e) {}
	}
	
	private void postOrden(Tree<Character> t, Character c, PositionList<Position<Character>> l, Position<Character> p){
		try {
		for(Position<Character> hijos : t.children(p)) {
			postOrden(t,c,l,hijos);
		}
		if(p.element().equals(c))
			l.addLast(p);
		}catch(InvalidPositionException e) {}
	}
	
	
	
	
	
}
