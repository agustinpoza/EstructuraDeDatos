package TP6;

import java.util.Iterator;
import TDAMap.MapeoHashAbierto;
import TDAArbol.EmptyTreeException;
import TDAArbol.Tree;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.ListaDE;
import TDALista.PositionList;
import TDAMap.InvalidKeyException;
import TDAMap.Map;

public class Ejercicios<E> {
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
		 postOrden4(s,t,l,t.root());
		 return l;
	}
}
