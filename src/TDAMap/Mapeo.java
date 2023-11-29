package TDAMap;

import java.util.Iterator;

import TDALista.*;


public class Mapeo<K,V> implements Map<K,V> {
	
	protected PositionList<Entrada<K,V>> l;
	
	public Mapeo() {
		l = new ListaDE<>();
	}
	
	public int size() {
		return l.size();
	}

	
	public boolean isEmpty() {
		return l.isEmpty();
	}

	
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		Iterator<Entrada<K,V>> it = l.iterator();
		V toReturn = null;
		Entrada<K,V> e = null;
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			e = it.next();
			if(e.getKey().equals(key)) {
				encontre = true;
				toReturn = e.getValue();
			}
		}
		
		return toReturn;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Iterator<Entrada<K,V>> it = l.iterator();
		Entrada<K,V> e;
		V toReturn = null;
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			e = it.next();
			if(e.getKey().equals(key)) {
				encontre = true;
				toReturn = e.getValue();
				e.setValue(value);
			}
		}
		if(!encontre) {
			e = new Entrada<K,V>(key, value);
			l.addLast(e);
			toReturn = value;
		}
		
		return toReturn;
	}

	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		Iterator<Position<Entrada<K,V>>> it = l.positions().iterator();
		Position<Entrada<K,V>> e;
		V toReturn = null;
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			e = it.next();
			if(e.element().getKey().equals(key)) {
				encontre = true;
				toReturn = e.element().getValue();
				try {
					l.remove(e);
				} catch (InvalidPositionException e1) {}
			}
		}
		return toReturn;
	}

	public Iterable<K> keys() {
		PositionList<K> keyList = new ListaDE<K>();
		for(Entrada<K,V> e : l) {
			keyList.addLast(e.getKey());
		}
		return keyList;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> valueList = new ListaDE<V>();
		for(Entrada<K,V> e : l) {
			valueList.addLast(e.getValue());
		}
		return valueList;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> entriesList = new ListaDE<Entry<K,V>>();
		for(Entrada<K,V> e : l) {
			entriesList.addLast(e);
		}
		return entriesList;
	}
	
	private void checkKey(K key) throws InvalidKeyException{
		if(key==null) throw new InvalidKeyException("key invalida");
	}
	
}
