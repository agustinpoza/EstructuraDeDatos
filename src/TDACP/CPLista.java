package TDACP;

import java.util.Comparator;

import TDALista.*;

public class CPLista<K, V> implements PriorityQueue<K,V> {

	//atributos
	protected PositionList<Entry<K,V>> lista;
	protected Comparator<K> comp;
	
	//Constructor 
	public CPLista(Comparator<K> comparador) {
		this.comp = comparador;
		lista = new ListaDE<>();
	}

	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("Cola vacia");
		Entry<K,V> out = null;
		try {
			out = lista.first().element();
		}catch(EmptyListException e) {}
		return out;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("clave invalida");
		Entry<K,V> entrada = new Entrada<>(key,value);
		try {
			if(isEmpty())
				lista.addLast(entrada);
			else {
				Position<Entry<K,V>> pp = lista.first();
				while(pp != lista.last() && comp.compare(pp.element().getKey(), key) < 0) {
					pp = lista.next(pp);
				}
				if(pp == lista.last() && comp.compare(pp.element().getKey(), key) < 0)
					lista.addLast(entrada);
				else
					lista.addBefore(pp, entrada);
			}
		}catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {}
		return entrada;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException(" CP vacia");
		 Entry<K,V> out = null;
		 try {
			 out = lista.first().element();
			 lista.remove(lista.first());
		 }catch(EmptyListException | InvalidPositionException e) {}
		return out;
	}
	
}
