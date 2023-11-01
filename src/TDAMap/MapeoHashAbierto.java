package TDAMap;

import java.util.Iterator;

import TDALista.*;

public class MapeoHashAbierto<K,V> implements Map<K,V> {

	protected PositionList<Entrada<K,V>>[] tabla;
	protected int size;
	protected int N; //cantidad de buckets
	protected final float FACTOR_CARGA = 0.9F;

	
	public MapeoHashAbierto() {
		N=13;
		tabla = new ListaDE[N];
		for(int i = 0; i<N; i++) {
			tabla[i] = new ListaDE<Entrada<K,V>>();
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		int hk = hashKey(key);
		V toReturn = null;
		Iterator<Entrada<K,V>> it = tabla[hk].iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			Entrada<K,V> e = it.next();
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
		int hk = hashKey(key);
		V toReturn = null;
		Iterator<Entrada<K,V>> it = tabla[hk].iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			Entrada<K,V> e = it.next();
			if(e.getKey().equals(key)) {
				encontre = true;
				toReturn = e.getValue();
				e.setValue(value);
			}
		}
		if(!encontre) {
			tabla[hk].addLast(new Entrada<K,V>(key, value));
			size++;
			if((float)(size/N) >= FACTOR_CARGA) reHash();
		}
		return toReturn;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		int hk = hashKey(key);
		V toReturn = null;
		Iterator<Position<Entrada<K,V>>> it = tabla[hk].positions().iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			Position<Entrada<K,V>> pe = it.next();
			if(pe.element().getKey().equals(key)) {
				encontre = true;
				toReturn = pe.element().getValue();
				try {
					tabla[hk].remove(pe);
					size--;
				} catch (InvalidPositionException e) {}
			}
		}
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> p = new ListaDE<>();
		for(PositionList<Entrada<K,V>> lista : tabla) { //T(n) donde n es la cantidad de buckets que tiene nuestra tabla hash
			for(Entrada<K,V> e : lista) { // T(m) donde m es la cantidad de entradas que tiene el bucket que estamos recorriendo
				p.addLast(e.getKey());
			}
		}
		return p;
		//T(n*m) = O(n^2)
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> p = new ListaDE<>();
		for(PositionList<Entrada<K,V>> lista : tabla) {
			for(Entrada<K,V> e : lista) {
				p.addLast(e.getValue());
			}
		}
		return p;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> p = new ListaDE<>();
		for(PositionList<Entrada<K,V>> lista : tabla) {
			for(Entrada<K,V> e : lista) {
				p.addLast(e);
			}
		}
		return p;
	} 
	
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("clave nula");
	}
	
	private int hashKey(K key) throws InvalidKeyException{
		checkKey(key);
		return Math.abs(key.hashCode()%N);
	}
	
	private int nextPrime(int num){
		int candidato = num + 1; // Empezamos en el siguiente número después de n
	    while (true) { // Iteramos hasta encontrar el siguiente número primo
	        boolean esPrimo = true;
	        for (int i = 2; i <= Math.sqrt(candidato); i++) {
	            if (candidato % i == 0) { // Si encontramos un divisor, no es primo
	                esPrimo = false;
	                break;
	            }
	        }
	        if (esPrimo) { // Si es primo, lo devolvemos
	            return candidato;
	        }
	        candidato++; // Si no es primo, probamos con el siguiente número
	    }
	}
	
	private void reHash() throws InvalidKeyException {
		Iterable<Entry<K,V>> entries = this.entries();
		N = nextPrime(N*2);
		tabla = new ListaDE[N];
		for(int i=0;i<N;i++) {
			tabla[i] = new ListaDE<Entrada<K,V>>();
		}
		size = 0;
		for(Entry<K,V> e : entries) {
			this.put(e.getKey(), e.getValue());
		}
	}
}
